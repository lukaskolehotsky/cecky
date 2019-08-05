package com.example.Utils;

import com.example.controller.ItemWithFileController;
import com.example.model.DBFile;
import com.example.model.DBItem;
import com.example.payload.FileResponse;
import com.example.payload.ItemResponse;
import com.example.requests.CreateItemRequest;
import com.example.requests.UpdateItemRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
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
    
    public DBItem prepareModifiedItem(DBItem item, UpdateItemRequest request) {
    	
        if(request.getBrand() != null){
            item.setBrand(request.getBrand());
        } else {
            item.setBrand("daj na opt");
        }

        if(request.getType() != null){
            item.setType(request.getType());
        } else {
            item.setType("daj na opt");
        }

        if(item.getGuid() != null){
            item.setGuid(item.getGuid());
        } else {
            item.setGuid("guid");
        }

        item.setCreatedDateTime(LocalDateTime.now());

    	return item;
    }
    
    public FileResponse generateFileResponse(DBFile file) throws UnsupportedEncodingException {
    	return new FileResponse(file.getFileName(), "", file.getFileType(), 1, encodeBytes(file.getData()));
    }
    
    public String encodeBytes(byte[] bytes) throws UnsupportedEncodingException {
        byte[] encodeBase64 = Base64.encodeBase64(bytes);
        String base64Encoded = new String(encodeBase64, "UTF-8"); 
        return base64Encoded;
    }
}
