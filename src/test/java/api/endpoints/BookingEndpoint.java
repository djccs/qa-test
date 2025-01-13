package api.endpoints;

import static io.restassured.RestAssured.*;
import api.payload.Booking;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class BookingEndpoint {
	
	//Create Booking
	
	public static Response createBooking( Booking bookingPayload)
	{
		Response response = 
		given()
			.auth()
			.preemptive()
			.basic("candidate@hostfully.com", "NaX5k1wFadtkFf")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(bookingPayload)
	        .log().all()  // Log the full request details
		.when()
			.post(Routes.bookingPost_url);
		
		return response;
	}
	
	//Read Booking
	public static Response readBooking(String id)
	{
		Response response = 
		given()
			.auth()
			.preemptive()
			.basic("candidate@hostfully.com", "NaX5k1wFadtkFf")
			.pathParam("id", id)
			.log().all()
		.when()
			.get(Routes.bookingGet_url);
		
		return response;
	}
	
	
	//Update booking
	public static Response updateBooking(String bookingId, Booking bookingPayload)
	{
		Response response = 
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("id", bookingId)
			.body(bookingPayload)
		.when()
			.put(Routes.bookingUpdate_url);
		
		return response;
	}
	
	//Delete booking
	public static Response deleteBooking(String bookingId)
	{
		Response response = 
		given()
			.pathParam("id", bookingId)
		.when()
			.delete(Routes.bookingDelete_url);
		
		return response;
	}
	
	

}
