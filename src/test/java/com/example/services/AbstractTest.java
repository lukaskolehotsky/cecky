package com.example.services;

import com.example.model.DBFile;
import com.example.payload.ItemResponse;
import com.example.payload.FileResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.requests.CreateItemRequest;
import com.example.requests.UpdateItemRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.DBItem;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class AbstractTest {
	
	public DBItem generateItem() {
		String guid = "guid";
		String brand = "brand";
		String type = "type";
		String email = "email";
		String authenticationCode = "authenticationCode";
		return new DBItem(brand,type,guid, LocalDateTime.now(), email, authenticationCode);
	}

	public DBItem generateDBItem(CreateItemRequest request) {
		return new DBItem(
			request.getBrand(),
			request.getType(),
			generateRandomUUID(),
			LocalDateTime.now(),
			request.getEmail(),
			request.getAuthenticationCode().get()
		);
	}	
    
    public List<DBItem> generateDBItems() {
    	List<DBItem> items = new ArrayList<>();
    	items.add(generateItem());
        return items;
    }
	
	public DBFile generateFile() {
		return new DBFile("imgPath", "name", "type", new byte[1],"guid");
	}
	
	public List<DBFile> generateFiles() {
		List<DBFile> files = new ArrayList<>();
		files.add(new DBFile("imgPath", "name1", "type1", new byte[1],"guid"));
		files.add(new DBFile("imgPath", "name2", "type2", new byte[2],"guid"));
		
		return files;
	}

	public ItemResponse generateItemResponse(DBItem item) {
		return new ItemResponse(item.getBrand(),item.getType(),item.getGuid(),item.getCreatedDateTime(), item.getEmail(), Optional.of(item.getAuthenticationCode()));
	}	
	
	public List<FileResponse> generateUploadFileResponses(List<DBFile> files) throws UnsupportedEncodingException {
		List<FileResponse> uploadFileResponses = new ArrayList<FileResponse>();
		for(DBFile file: files) {
			uploadFileResponses.add(new FileResponse(file.getFileName(), "", file.getFileType(), 1, encodeBytes(file.getData()), file.getImgPath()));
		}
		
		return uploadFileResponses;
	}

	public String generateRandomUUID() {
		return UUID.randomUUID().toString();
	}
	
	public UpdateItemRequest generateUpdateItemRequest(DBItem item) {
		return new UpdateItemRequest(item.getBrand(), item.getType(), item.getEmail(), Optional.of(item.getAuthenticationCode()));
	}
	
	public List<FileResponse> generateFileResponses(List<MultipartFile> files) throws IOException {
		List<FileResponse> fileResponses = new ArrayList<>();
		for(MultipartFile multipartFile: files) {
			fileResponses.add(new FileResponse(multipartFile.getName(), "",multipartFile.getContentType(), 1, encodeBytes(multipartFile.getBytes()), "DOROBIT"));
		}
		return fileResponses;
	}
	
	public CreateItemRequest generateCreateItemRequest() {
		return new CreateItemRequest("brand", "type", "email", Optional.of("authenticationCode"));
	}
	
	public List<MultipartFile> generateMockedMultipartFiles() {
		List<MultipartFile> files = new ArrayList<>();
		MultipartFile file = new MockMultipartFile("name", new byte[1]);
		files.add(file);
		return files;
	}
	
	public String encodeBytes(byte[] bytes) throws UnsupportedEncodingException {
        byte[] encodeBase64 = Base64.encodeBase64(bytes);
        String base64Encoded = new String(encodeBase64, "UTF-8"); 
        return base64Encoded;
    }
	
}
