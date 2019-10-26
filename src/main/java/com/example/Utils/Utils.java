package com.example.Utils;

import com.example.exception.FileStorageException;
import com.example.model.DBFile;
import com.example.model.DBItem;
import com.example.payload.ItemResponse;
import com.example.requests.CreateItemRequest;
import com.example.requests.UpdateItemRequest;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class Utils {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }

    public DBItem generateDBItem(CreateItemRequest request) {
        return new DBItem(
                request.getBrand(),
                request.getType(),
                generateRandomUUID(),
                LocalDateTime.now(),
                request.getEmail(),
                request.getAuthenticationCode(),
                request.getPrice(),
                request.getDescription(),
                request.getFuelType(),
                request.getSpeedometerCondition(),
                request.getProductionYear(),
                request.getMobileNumber()
        );
    }

    public DBFile generateDBFile(String imgPath, String fileName, String contentType, String guid) {
        return new DBFile(imgPath, fileName, contentType, guid);
    }

    public ItemResponse generateItemResponse(DBItem item) {
        return new ItemResponse(
                item.getBrand(),
                item.getType(),
                item.getGuid(),
                item.getCreatedDateTime(),
                item.getEmail(),
                item.getAuthenticationCode(),
                item.getPrice(),
                item.getDescription(),
                item.getFuelType(),
                item.getSpeedometerCondition(),
                item.getProductionYear(),
                item.getMobileNumber()
        );
    }

    public DBItem prepareModifiedItem(DBItem item, UpdateItemRequest request) {
    	
        if (request.getBrand() != null) {
            item.setBrand(request.getBrand());
        } else {
            item.setBrand("daj na opt");
        }

        if (request.getType() != null) {
            item.setType(request.getType());
        } else {
            item.setType("daj na opt");
        }

        if (item.getGuid() != null) {
            item.setGuid(item.getGuid());
        } else {
            item.setGuid("guid");
        }

        if (request.getEmail() != null) {
            item.setEmail(request.getEmail());
        } else {
            item.setEmail("email");
        }

        item.setCreatedDateTime(LocalDateTime.now());

        if (request.getDescription() != null) {
            item.setDescription(request.getDescription());
        } else {
            item.setDescription("description");
        }

        if (request.getPrice() != null) {
            item.setPrice(request.getPrice());
        } else {
            item.setPrice(new BigInteger("1"));
        }

        if (request.getFuelType() != null) {
            item.setFuelType(request.getFuelType());
        } else {
            item.setFuelType("Elektrika");
        }

        if (request.getProductionYear() != null) {
            item.setProductionYear(request.getProductionYear());
        } else {
            item.setProductionYear(2200L);
        }

        if (request.getSpeedometerCondition() != null) {
            item.setSpeedometerCondition(request.getSpeedometerCondition());
        } else {
            item.setSpeedometerCondition(180500L);
        }
        
        if (request.getMobileNumber() != null) {
        	item.setMobileNumber(request.getMobileNumber());
        } else {
        	item.setMobileNumber(421907397135L);
        }

        return item;
    }

    public String generateAuthenticationCode(int count){
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
    
    public void validateFileName(String fileName) {
    	// Check if the file's name contains invalid characters
        if(fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }
    }
}
