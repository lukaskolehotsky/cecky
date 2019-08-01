package com.example.Utils;

import com.example.model.DBItem;
import com.example.requests.CreateItemRequest;

import java.time.LocalDateTime;
import java.util.UUID;

public class Utils {

    public String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }

    public DBItem generateDBItem(CreateItemRequest request) {
        return new DBItem(request.getBrand(), request.getType(), generateRandomUUID(), LocalDateTime.now());
    }
}
