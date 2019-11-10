package com.example.services;

import java.util.Optional;

import com.example.model.DBItem;
import com.example.requests.CreateItemRequest;
import com.example.requests.UpdateItemRequest;

public class RequestsGenerator {

    public UpdateItemRequest generateUpdateItemRequest(DBItem item) {
        return new UpdateItemRequest(
                item.getBrand(),
                item.getType(),
                item.getEmail(),
                Optional.of(item.getAuthenticationCode()),
                item.getPrice(),
                item.getDescription(),
                item.getFuelType(),
                item.getSpeedometerCondition(),
                item.getProductionYear(),
                item.getMobileNumber()
        );
    }

    public CreateItemRequest generateCreateItemRequest() {
        return new CreateItemRequest(
                "brand",
                "type",
                "email",
                "authenticationCode",
                "1500",
                "description",
                "Diesel",
                "10000",
                2015L,
                "421907397135"
        );
    }

}
