package com.example.Utils;

import com.example.model.DBFile;
import com.example.model.DBItem;
import com.example.payload.FileResponse;
import com.example.payload.ItemResponse;
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
    
    public ItemResponse generateItemResponse(DBItem item) {
    	return new ItemResponse(item.getBrand(), item.getType(), item.getGuid(), item.getCreatedDateTime());
    }
    
    public FileResponse generateFileResponse(DBFile file) {
    	return new FileResponse(file.getFileName(), "", file.getFileType(), 1);
    }
}
