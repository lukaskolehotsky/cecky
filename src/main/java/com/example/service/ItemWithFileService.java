package com.example.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(ItemWithFileService.class);

	@Autowired
    private ItemService itemService;
	
	@Autowired
    private FileService fileService;

	@Transactional
    public ItemWithFilesResponse getItemWithFiles(String guid) throws UnsupportedEncodingException {
    	
    	ItemResponse itemResponse = itemService.getItem(guid);
    	List<FileResponse> fileResponses = fileService.getFiles(guid);
    	
    	return new ItemWithFilesResponse(itemResponse, fileResponses);
    	
    }
	
	@Transactional
    public List<ItemWithFilesResponse> getAllItemsWithFiles(int pageNumber) throws UnsupportedEncodingException {
		
		List<ItemWithFilesResponse> itemsWithFileResponses = new ArrayList<>();
		List<ItemResponse> itemResponses = itemService.getAll(pageNumber);

		for(ItemResponse itemResponse: itemResponses) {
			List<FileResponse> fileResponses = fileService.getFiles(itemResponse.getGuid());
			itemsWithFileResponses.add(new ItemWithFilesResponse(itemResponse, fileResponses));
		}
    	
    	return itemsWithFileResponses;   	
    }
	
	public List<String> getAll_v2(int pageNumber) throws UnsupportedEncodingException {	    	
    	List<ItemResponse> itemResponses = itemService.getAll(pageNumber);    	
    	List<String> firstImages = new ArrayList<>();
    	
    	for(ItemResponse itemResponse: itemResponses) {
    		List<String> imgPaths = fileService.getAllFilesFromDirectory(itemResponse.getGuid());
    				    		
    		if(!imgPaths.isEmpty()) {
    			firstImages.add("http://chcemto.eu"+imgPaths.get(0).replace("/var/webapp", ""));
    		} else {
    			logger.info("PRE ITEM S GUID " + itemResponse.getGuid() + " SME NENASLI ZIADNE OBRAZKY.");
    		}   		
    	}
    	
    	return firstImages;    	   	
    }
	
	@Transactional
	public void removeItemWithFiles(String guid) {
		itemService.removeItem(guid);
		fileService.removeFile(guid);
	}
	
	public ItemWithFilesResponse updateItemWithFiles(String guid, UpdateItemRequest request, List<MultipartFile> files) throws IOException {
		ItemResponse itemResponse = itemService.updateItem(guid, request);
		List<FileResponse> fileResponses = fileService.updateFiles(guid, files);
		return new ItemWithFilesResponse(itemResponse, fileResponses);
	}
	
	public ItemWithFilesResponse createItemWithFiles(CreateItemRequest request, List<MultipartFile> files) throws IOException {
		ItemResponse itemResponse = itemService.createItem(request);
		List<FileResponse> fileResponses = fileService.saveImages(files, itemResponse.getGuid());
		return new ItemWithFilesResponse(itemResponse, fileResponses);
		
	}
	
}
