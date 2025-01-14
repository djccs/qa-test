package api.test;

import java.text.SimpleDateFormat;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.BookingEndpoint;
import api.endpoints.PropertyEndpoint;
import api.payload.Booking;
import api.payload.Property;
import io.restassured.response.Response;

public class BookingTests {

    Faker faker;
    Booking bookingPayload;
    SimpleDateFormat dateFormat;

    @BeforeClass
    public void setUpData() {
        faker = new Faker();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Create a new Property to use on booking tests
        Property propertyPayload = new Property();
        propertyPayload.setAlias(faker.pokemon().name());
        propertyPayload.setId(UUID.randomUUID().toString());

        Response propertyResponse = PropertyEndpoint.createProperty(propertyPayload);
        Assert.assertEquals(propertyResponse.statusCode(), 201, "Property creation!");
        String property = propertyResponse.jsonPath().getString("id");

        // Set up the Booking payload
        bookingPayload = new Booking();
        bookingPayload.setPropertyId(property);

        // Generate and set guest details
        Booking.Guest guest = new Booking.Guest();
        guest.setFirstName(faker.name().firstName());
        guest.setLastName(faker.name().lastName());
        guest.setDateOfBirth(dateFormat.format(faker.date().birthday(18, 80)));
        bookingPayload.setGuest(guest);

    }

    @Test
    public void testCreateBooking() {
        bookingPayload.setId(UUID.randomUUID().toString());
        // Set date range
        String startDate = dateFormat.format(faker.date().future(5, java.util.concurrent.TimeUnit.DAYS));
        String endDate = dateFormat.format(faker.date().future(10, java.util.concurrent.TimeUnit.DAYS));
        bookingPayload.setStartDate(startDate);
        bookingPayload.setEndDate(endDate);
        bookingPayload.setStatus("SCHEDULED");

        // Send the POST request to create a new booking
        Response response = BookingEndpoint.createBooking(bookingPayload);
        response.then().log().all();

        // Validate the response
        Assert.assertEquals(response.statusCode(), 201, "Booking creation Failed!");
        Assert.assertEquals(response.jsonPath().getString("propertyId"), bookingPayload.getPropertyId(), "Property ID mismatch!");
        Assert.assertEquals(response.jsonPath().getString("status"), bookingPayload.getStatus(), "Status mismatch!");
    }

    @Test
    public void testOverlappingBooking() {
        // Create the first booking
        bookingPayload.setId(UUID.randomUUID().toString());
        
        String startDate = dateFormat.format(faker.date().future(15, java.util.concurrent.TimeUnit.DAYS));
        String endDate = dateFormat.format(faker.date().future(20, java.util.concurrent.TimeUnit.DAYS));
        bookingPayload.setStartDate(startDate);
        bookingPayload.setEndDate(endDate);
        
        
        Response response1 = BookingEndpoint.createBooking(bookingPayload);
        response1.then().log().all();
        Assert.assertEquals(response1.statusCode(), 201, "First booking creation failed!");

        // Create another booking with overlapping dates
        Booking overlappingBooking = new Booking();
        overlappingBooking.setId(UUID.randomUUID().toString());
        overlappingBooking.setPropertyId(bookingPayload.getPropertyId());
        overlappingBooking.setStartDate(bookingPayload.getStartDate()); // Overlapping start date
        overlappingBooking.setEndDate(bookingPayload.getEndDate());     // Overlapping end date
        overlappingBooking.setStatus("SCHEDULED");

        Booking.Guest guest = new Booking.Guest();
        guest.setFirstName(faker.name().firstName());
        guest.setLastName(faker.name().lastName());
        guest.setDateOfBirth(dateFormat.format(faker.date().birthday(18, 80)));
        overlappingBooking.setGuest(guest);

        Response response2 = BookingEndpoint.createBooking(overlappingBooking);
        response2.then().log().all();

        // Validate that the overlapping booking is rejected
        Assert.assertEquals(response2.statusCode(), 422, "Overlapping booking should fail!");
    }
}
