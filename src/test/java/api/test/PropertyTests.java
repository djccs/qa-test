package api.test;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PropertyEndpoint;
import api.payload.Property;
import io.restassured.response.Response;

public class PropertyTests {
	
	//Faker allows to create fake data 
	Faker faker;
	Property propertyPayload;
	
	@BeforeClass
	public void setUpData() {
		
		faker=new Faker();
		propertyPayload=new Property();
		propertyPayload.setAlias(faker.pokemon().name());
	}
	
	@Test()
	public void testCreateProperty(){
		propertyPayload.setId(UUID.randomUUID().toString());
		Response response=PropertyEndpoint.createProperty(propertyPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 201);
	}
	
	@Test()
	public void testGetPropertyById(){
		propertyPayload.setId("cde7215c-531e-4441-8b0f-3f88204a8ad0");
		Response response=PropertyEndpoint.readProperty(propertyPayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	
	
	
	

}
