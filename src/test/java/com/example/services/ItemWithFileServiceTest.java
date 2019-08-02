package com.example.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.example.model.DBItem;
import com.example.payload.FileResponse;
import com.example.payload.ItemResponse;
import com.example.payload.ItemWithFilesResponse;
import com.example.repository.FileRepository;
import com.example.repository.ItemRepository;
import com.example.service.FileService;
import com.example.service.ItemService;
import com.example.service.ItemWithFileService;

public class ItemWithFileServiceTest extends AbstractTest{

	@InjectMocks
	private ItemWithFileService itemWithFileService;
	
	@Mock
	private ItemService itemService;
	
	@Mock
	private FileService fileService;
	
	@Before
	public void setUp() {
		//itemService.evictCache();
	}
	
	@After
	public void tearDown() {
		// Clean up after each test method.
	}
	
	@Test
	public void getItemWithFiles() {
		String guid = "guid";
		DBItem item = new DBItem("brand", "type", "guid", LocalDateTime.now());
		ItemResponse itemResponse = generateItemResponse(item);
		List<FileResponse> fileResponses = new ArrayList<>();
		fileResponses.add(new FileResponse("name", "", "fileType", 1));
		
		ItemWithFilesResponse itemWithFileResponse = new ItemWithFilesResponse(itemResponse, fileResponses);
		    	
    	Mockito.when(itemService.getItem(guid)).thenReturn(itemResponse);
    	Mockito.when(fileService.getFiles(guid)).thenReturn(fileResponses);
		
	    ItemWithFilesResponse response = itemWithFileService.getItemWithFiles(guid);
	    
	    Assert.assertEquals(itemWithFileResponse.getItemResponse().getBrand(), response.getItemResponse().getBrand());
		
	}
	
	@Test
	public void getAllItemsWithFiles() {
		DBItem item = generateItem();		
		ItemResponse itemResponse = generateItemResponse(item);
		List<ItemResponse> itemResponses = new ArrayList<>();
		itemResponses.add(itemResponse);
		
		FileResponse fileResponse = new FileResponse("fileName","fileDownloadUri", "fileType", 1);
		List<FileResponse> fileResponses = new ArrayList<>();
		fileResponses.add(fileResponse);
		
		ItemWithFilesResponse itemWithFilesResponse = new ItemWithFilesResponse(itemResponse, fileResponses);
		
		Mockito.when(itemService.getAll()).thenReturn(itemResponses);
		Mockito.when(fileService.getFiles(itemResponse.getGuid())).thenReturn(fileResponses);
		
		List<ItemWithFilesResponse> response = itemWithFileService.getAllItemsWithFiles();
		
		Assert.assertEquals(itemWithFilesResponse.getItemResponse().getBrand(), response.get(0).getItemResponse().getBrand());
		
	}
	
}
