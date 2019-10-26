package com.example.requests;

import java.math.BigInteger;
import java.util.Optional;

public class UpdateItemRequest {

    private String brand;
    private String type;
    private String email;
    private Optional<String> authenticationCode;
    private BigInteger price;
    private String description;
    private String fuelType;
    private Long speedometerCondition;
    private Long productionYear;
    private Long mobileNumber;

    public UpdateItemRequest(
            String brand,
            String type,
            String email,
            Optional<String> authenticationCode,
            BigInteger price,
            String description,
            String fuelType,
            Long speedometerCondition,
            Long productionYear,
            Long mobileNumber
    ) {
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

    public Optional<String> getAuthenticationCode() {
        return authenticationCode;
    }

    public void setAuthenticationCode(Optional<String> authenticationCode) {
        this.authenticationCode = authenticationCode;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
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

    public Long getSpeedometerCondition() {
        return speedometerCondition;
    }

    public void setSpeedometerCondition(Long speedometerCondition) {
        this.speedometerCondition = speedometerCondition;
    }

    public Long getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Long productionYear) {
        this.productionYear = productionYear;
    }

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
    
    

}
