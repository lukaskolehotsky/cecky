package com.example.services;

import com.example.model.DBFile;
import com.example.payload.ItemResponse;
import com.example.payload.UploadFileResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.requests.CreateItemRequest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.DBItem;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class AbstractTest {
	
	public DBItem generateItem() {
		String guid = "guid";
		String brand = "brand";
		String type = "type";
		return new DBItem(brand,type,guid);
	}
	
	public DBFile generateFile() {
		return new DBFile("name", "type", new byte[1],"guid");
	}


	public ItemResponse generateItemResponse(DBItem item) {
		return new ItemResponse(item.getBrand(),item.getType(),item.getGuid());
	}
	
	public List<DBFile> generateFiles() {
		List<DBFile> files = new ArrayList<>();
		files.add(new DBFile("name1", "type1", new byte[1],"guid"));
		files.add(new DBFile("name2", "type2", new byte[2],"guid"));
		
		return files;
	}
	
	public List<UploadFileResponse> generateUploadFileResponses(List<DBFile> files) {
		List<UploadFileResponse> uploadFileResponses = new ArrayList<UploadFileResponse>();
		for(DBFile file: files) {
			uploadFileResponses.add(new UploadFileResponse(file.getFileName(), "", file.getFileType(), 1));
		}
		
		return uploadFileResponses;
	}

	public String generateRandomUUID() {
		return UUID.randomUUID().toString();
	}

	public DBItem generateDBItem(CreateItemRequest request) {
		return new DBItem(request.getBrand(), request.getType(), generateRandomUUID());
	}
}
