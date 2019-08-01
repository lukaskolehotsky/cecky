package com.example.payload;

public class ItemResponse {

	private String brand;
	
	private String type;
	
	private String guid;
	
	public ItemResponse(String brand, String type, String guid) {
		super();
		this.brand = brand;
		this.type = type;
		this.guid = guid;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}	
	
}
