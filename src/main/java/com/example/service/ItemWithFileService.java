package com.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.payload.FileResponse;
import com.example.payload.ItemResponse;
import com.example.payload.ItemWithFilesResponse;
import com.example.requests.CreateItemRequest;
import com.example.requests.UpdateItemRequest;

@Service
public class ItemWithFileService {
	
	@Autowired
    private ItemService itemService;
	
	@Autowired
    private FileService fileService;

	@Transactional
    public ItemWithFilesResponse getItemWithFiles(String guid) {
    	
    	ItemResponse itemResponse = itemService.getItem(guid);
    	List<FileResponse> fileResponses = fileService.getFiles(guid);
    	
    	return new ItemWithFilesResponse(itemResponse, fileResponses);
    	
    }
	
	@Transactional
    public List<ItemWithFilesResponse> getAllItemsWithFiles() {
		
		List<ItemWithFilesResponse> itemWithFileResponses = new ArrayList<>();
		
		List<ItemResponse> itemResponses = itemService.getAll();
		
		for(ItemResponse itemResponse: itemResponses) {
			List<FileResponse> fileResponses = fileService.getFiles(itemResponse.getGuid());
			itemWithFileResponses.add(new ItemWithFilesResponse(itemResponse, fileResponses));
		}
    	
    	return itemWithFileResponses;    	
    }
	
	@Transactional
	public void removeItemWithFiles(String guid) {
		itemService.removeItem(guid);
		fileService.removeFile(guid);
	}
	
	public ItemWithFilesResponse updateItemWithFiles(String guid, UpdateItemRequest request, MultipartFile[] files) {
		ItemResponse itemResponse = itemService.updateItem(guid, request);
		List<FileResponse> fileResponses = fileService.updateFiles(guid, files);
		return new ItemWithFilesResponse(itemResponse, fileResponses);
	}
	
	public ItemWithFilesResponse createItemWithFiles(CreateItemRequest request, MultipartFile[] files) {
		ItemResponse itemResponse = itemService.createItem(request);
		List<FileResponse> fileResponses = fileService.saveImages(files, itemResponse.getGuid());
		return new ItemWithFilesResponse(itemResponse, fileResponses);
		
	}
	
}
