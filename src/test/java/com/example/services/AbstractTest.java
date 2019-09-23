package com.example.services;

import com.example.model.DBFile;
import com.example.model.DBItem;
import com.example.requests.CreateItemRequest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class AbstractTest extends ResponsesGenerator {

    public DBItem generateItem() {
        String guid = "guid";
        String brand = "brand";
        String type = "type";
        String email = "email";
        String authenticationCode = "authenticationCode";
        BigInteger price = new BigInteger("1500");
        String description = "description";
        String fuelType = "Diesel";
        Long speedoMeterCondition = 100000L;
        Long productionYear = 2015L;
        return new DBItem(
                brand,
                type,
                guid,
                LocalDateTime.now(),
                email,
                authenticationCode,
                price,
                description,
                fuelType,
                speedoMeterCondition,
                productionYear
        );
    }

    public DBItem generateItem(CreateItemRequest request) {
        return new DBItem(
                request.getBrand(),
                request.getType(),
                generateRandomUUID(),
                LocalDateTime.now(),
                request.getEmail(),
                request.getAuthenticationCode().get(),
                request.getPrice(),
                request.getDescription(),
                request.getFuelType(),
                request.getSpeedoMeterCondition(),
                request.getProductionYear()
        );
    }

    public List<DBItem> generateItems() {
        List<DBItem> items = new ArrayList<>();
        items.add(generateItem());
        return items;
    }

    public DBFile generateDBFile(String imgPath, String fileName, String contentType, String guid) {
        return new DBFile(imgPath, fileName, contentType, guid);
    }

    public DBFile generateFile() {
        return new DBFile("imgPath", "name", "type", "guid");
    }

    public DBFile generateFile(String guid) {
        return new DBFile("imgPath2", "name2", "type2", guid);
    }

    public DBFile generateFile(MultipartFile multipartFile, String guid, String imagePath) {
        return new DBFile(imagePath, multipartFile.getOriginalFilename(), multipartFile.getContentType(), guid);
    }

    public List<DBFile> generateFiles() {
        List<DBFile> files = new ArrayList<>();
        files.add(new DBFile("imgPath", "name1", "type1", "guid"));
        files.add(new DBFile("imgPath", "name2", "type2", "guid"));

        return files;
    }

    public String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }

}
