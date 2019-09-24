package com.example.payload;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

public class ItemResponse {

    private String brand;
    private String type;
    private String guid;
    private LocalDateTime createdDateTime;
    private String email;
    private Optional<String> authenticationCode;
    private BigInteger price;
    private String description;
    private String fuelType;
    private Long speedometerCondition;
    private Long productionYear;

    public ItemResponse(
            String brand,
            String type,
            String guid,
            LocalDateTime createdDateTime,
            String email,
            Optional<String> authenticationCode,
            BigInteger price,
            String description,
            String fuelType,
            Long speedometerCondition,
            Long productionYear
    ) {
        super();
        this.brand = brand;
        this.type = type;
        this.guid = guid;
        this.createdDateTime = createdDateTime;
        this.email = email;
        this.authenticationCode = authenticationCode;
        this.price = price;
        this.description = description;
        this.fuelType = fuelType;
        this.speedometerCondition = speedometerCondition;
        this.productionYear = productionYear;
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

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
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

    @Override
    public String toString() {
        return "ItemResponse{" +
                "brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", guid='" + guid + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", email='" + email + '\'' +
                ", authenticationCode=" + authenticationCode +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", speedometerCondition=" + speedometerCondition +
                ", productionYear=" + productionYear +
                '}';
    }
}
