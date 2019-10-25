package com.example.services;

import com.example.model.DBItem;
import com.example.payload.FileResponse;
import com.example.payload.ItemResponse;

import java.util.Optional;

public class ResponsesGenerator extends RequestsGenerator{

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
                item.getProductionYear()
        );
    }

    public FileResponse generateFileResponse() {
        return new FileResponse("fileName", "fileDownloadUri", "fileType", 1, "imgPath");
    }

}
