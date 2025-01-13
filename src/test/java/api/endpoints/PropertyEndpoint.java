package api.endpoints;

import static io.restassured.RestAssured.*;
import api.payload.Property;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class PropertyEndpoint {
	
	//Create Property
	
	public static Response createProperty( Property propertyPayload)
	{
		Response response = 
		given()
			.auth()
			.preemptive()
			.basic("candidate@hostfully.com", "NaX5k1wFadtkFf")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(propertyPayload)
	        .log().all()  // Log the full request details
		.when()
			.post(Routes.propertyPost_url);
		
		return response;
	}
	
	//Read Property
	public static Response readProperty(String propertyId)
	{
		Response response = 
		given()
			.auth()
			.preemptive()
			.basic("candidate@hostfully.com", "NaX5k1wFadtkFf")
			.pathParam("propertyId", propertyId)
		.when()
			.get(Routes.propertyGet_url);
		
		return response;
	}
	
	
	//Update property
	public static Response updateProperty(String propertyId, Property propertyPayload)
	{
		Response response = 
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("propertyID", propertyId)
			.body(propertyPayload)
		.when()
			.put(Routes.propertyUpdate_url);
		
		return response;
	}
	
	//Delete Property
	public static Response deleteProperty(String propertyId)
	{
		Response response = 
		given()
			.pathParam("propertyID", propertyId)
		.when()
			.delete(Routes.propertyDelete_url);
		
		return response;
	}
	
	

}
