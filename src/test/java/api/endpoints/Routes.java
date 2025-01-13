package api.endpoints;

//https://qa-assessment.services.hostfully.com/swagger-ui/index.html 

//https://qa-assessment.services.hostfully.com/properties
//Creation:  https://qa-assessment.services.hostfully.com/properties
//Retrieval: https://qa-assessment.services.hostfully.com/properties
//Booking:   https://qa-assessment.services.hostfully.com/???

public class Routes {
	
	//base url
	//public static String base_url="https://qa-assessment.services.hostfully.com/swagger-ui";
	public static String base_url="https://qa-assessment.svc.hostfully.com";
	
	//Property model route:
	
	//Post: https://qa-assessment.svc.hostfully.com/properties
	public static String propertyPost_url=base_url+"/properties";
	
	//GetAll: https://qa-assessment.svc.hostfully.com/properties
	public static String propertyGetAll_url=base_url+"/properties";
	
	//Get Property by Id
	public static String propertyGet_url=base_url+"/properties/{propertyId}"; 
	
	//Update Property 
	public static String propertyUpdate_url=base_url+"/property/{propertyId}";
	
	//Delete Property 
	public static String propertyDelete_url=base_url+"/property/{propertyId}";
	
	//Booking model routes
	
	public static String bookingPost_url=base_url+"/bookings";
	
	public static String bookingGetAll_url=base_url+"/bookings";

	public static String bookingGet_url=base_url+"/bookings/{id}";
	
	public static String bookingUpdate_url=base_url+"/property/{bookingId}";
	
	public static String bookingDelete_url=base_url+"/property/{bookingId}";

}


