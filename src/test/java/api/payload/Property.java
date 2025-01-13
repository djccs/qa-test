package api.payload;

public class Property {
	
	String id;
	String alias;
	String countryCode;
	String createdAt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String name) {
		this.alias = name;
	}

	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String zip) {
		this.countryCode = zip;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String rooms) {
		this.createdAt = rooms;
	}


}
