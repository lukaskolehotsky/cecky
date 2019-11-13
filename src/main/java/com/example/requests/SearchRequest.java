package com.example.requests;

public class SearchRequest {

	private String brand;
	private String type;

	public SearchRequest(String brand, String type) {
		super();
		this.brand = brand;
		this.type = type;
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

	@Override
	public String toString() {
		return "SearchRequest [brand=" + brand + ", type=" + type + "]";
	}

}
