package com.example.services;

import com.example.model.DBItem;
import com.example.requests.CreateItemRequest;
import com.example.requests.UpdateItemRequest;

import java.math.BigInteger;
import java.util.Optional;

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
                item.getProductionYear()
        );
    }

    public CreateItemRequest generateCreateItemRequest() {
        return new CreateItemRequest(
                "brand",
                "type",
                "email",
                "authenticationCode",
                new BigInteger("1500"),
                "description",
                "Diesel",
                100000L,
                2015L
        );
    }

}
