package com.example.requests;

public class CreateItemRequest {

	private String brand;
	private String type;
	private String email;
	private String authenticationCode;
	private String price;
	private String description;
	private String fuelType;
	private String speedometerCondition;
	private Long productionYear;
	private String mobileNumber;

	public CreateItemRequest(String brand, String type, String email, String authenticationCode, String price,
			String description, String fuelType, String speedometerCondition, Long productionYear,
			String mobileNumber) {
		this.brand = brand;
		this.type = type;
		this.email = email;
		this.authenticationCode = authenticationCode;
		this.price = price;
		this.description = description;
		this.fuelType = fuelType;
		this.speedometerCondition = speedometerCondition;
		this.productionYear = productionYear;
		this.mobileNumber = mobileNumber;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthenticationCode() {
		return authenticationCode;
	}

	public void setAuthenticationCode(String authenticationCode) {
		this.authenticationCode = authenticationCode;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public Long getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(Long productionYear) {
		this.productionYear = productionYear;
	}

	public String getSpeedometerCondition() {
		return speedometerCondition;
	}

	public void setSpeedometerCondition(String speedometerCondition) {
		this.speedometerCondition = speedometerCondition;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "CreateItemRequest [brand=" + brand + ", type=" + type + ", email=" + email + ", authenticationCode="
				+ authenticationCode + ", price=" + price + ", description=" + description + ", fuelType=" + fuelType
				+ ", speedometerCondition=" + speedometerCondition + ", productionYear=" + productionYear
				+ ", mobileNumber=" + mobileNumber + "]";
	}

}
