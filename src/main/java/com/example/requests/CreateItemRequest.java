package com.example.requests;

import org.hibernate.annotations.Type;

import javax.persistence.Lob;
import java.math.BigInteger;
import java.util.Optional;

public class CreateItemRequest {

    private String brand;
    private String type;
    private String email;
    private Optional<String> authenticationCode;
    private BigInteger price;
    private String description;

    public CreateItemRequest(String brand, String type, String email, Optional<String> authenticationCode, BigInteger price, String description) {
    	this.brand = brand;
        this.type = type;
        this.email = email;
        this.authenticationCode = authenticationCode;
        this.price = price;
        this.description = description;
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
}
