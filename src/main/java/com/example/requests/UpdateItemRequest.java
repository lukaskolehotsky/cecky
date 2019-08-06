package com.example.requests;

import java.util.Optional;

public class UpdateItemRequest {

    private String brand;
    private String type;
    private String email;
    private Optional<String> authenticationCode;

    public UpdateItemRequest(String brand, String type, String email, Optional<String> authenticationCode) {
        this.brand = brand;
        this.type = type;
        this.email = email;
        this.authenticationCode = authenticationCode;
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

}
