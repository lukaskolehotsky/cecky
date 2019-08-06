package com.example.services;

import com.example.model.DBItem;
import com.example.payload.FileResponse;
import com.example.payload.ItemResponse;
import com.example.payload.ItemWithFilesResponse;
import com.example.requests.CreateItemRequest;
import com.example.requests.UpdateItemRequest;
import com.example.service.FileService;
import com.example.service.ItemService;
import com.example.service.ItemWithFileService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

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
	public void getItemWithFiles() throws UnsupportedEncodingException {
		String guid = "guid";
		DBItem item = generateItem();
		ItemResponse itemResponse = generateItemResponse(item);
		List<FileResponse> fileResponses = new ArrayList<>();
		fileResponses.add(new FileResponse("name", "", "fileType", 1, encodeBytes(new byte[1])));
		
		ItemWithFilesResponse itemWithFileResponse = new ItemWithFilesResponse(itemResponse, fileResponses);
		    	
    	Mockito.when(itemService.getItem(guid)).thenReturn(itemResponse);
    	Mockito.when(fileService.getFiles(guid)).thenReturn(fileResponses);
		
	    ItemWithFilesResponse response = itemWithFileService.getItemWithFiles(guid);
	    
	    Assert.assertEquals(itemWithFileResponse.getItemResponse().getBrand(), response.getItemResponse().getBrand());
		
	}
	
	@Test
	public void getAllItemsWithFiles() throws UnsupportedEncodingException {
		DBItem item = generateItem();		
		ItemResponse itemResponse = generateItemResponse(item);
		List<ItemResponse> itemResponses = new ArrayList<>();
		itemResponses.add(itemResponse);
		
		FileResponse fileResponse = new FileResponse("fileName","fileDownloadUri", "fileType", 1, encodeBytes(new byte[1]));
		List<FileResponse> fileResponses = new ArrayList<>();
		fileResponses.add(fileResponse);
		
		ItemWithFilesResponse itemWithFilesResponse = new ItemWithFilesResponse(itemResponse, fileResponses);
		
		Mockito.when(itemService.getAll()).thenReturn(itemResponses);
		Mockito.when(fileService.getFiles(itemResponse.getGuid())).thenReturn(fileResponses);
		
		List<ItemWithFilesResponse> response = itemWithFileService.getAllItemsWithFiles();
		
		Assert.assertEquals(itemWithFilesResponse.getItemResponse().getBrand(), response.get(0).getItemResponse().getBrand());
		
	}
	
	@Test
	public void removeItemWithFiles() {
		String guid = "guid";
		
		doNothing().when(itemService).removeItem(guid);
		doNothing().when(fileService).removeFile(guid);
		
		itemWithFileService.removeItemWithFiles(guid);	
		
		verify(itemService).removeItem(guid);
		verify(fileService).removeFile(guid);
	}
	
	@Test
	public void createItemWithFiles() throws IOException {		
		CreateItemRequest request = generateCreateItemRequest();
		List<MultipartFile> files = generateMockedMultipartFiles();
		DBItem item = generateDBItem(request);
		ItemResponse itemResponse = generateItemResponse(item);
		List<FileResponse> fileResponses = generateFileResponses(files);
		ItemWithFilesResponse itemWithFileResponse = new ItemWithFilesResponse(itemResponse, fileResponses);
		
		Mockito.when(itemService.createItem(request)).thenReturn(itemResponse);
		Mockito.when(fileService.saveImages(files, itemResponse.getGuid())).thenReturn(fileResponses);
		
		ItemWithFilesResponse response = itemWithFileService.createItemWithFiles(request, files);
		
		Assert.assertEquals(itemWithFileResponse.getItemResponse().getBrand(),response.getItemResponse().getBrand());
		Assert.assertEquals(itemWithFileResponse.getItemResponse().getGuid(),response.getItemResponse().getGuid());
		Assert.assertEquals(itemWithFileResponse.getFileResponses().get(0).getFileName(),response.getFileResponses().get(0).getFileName());
	}
	
	@Test
	public void updateItemWithFiles() throws IOException {
		String guid = "guid";
		DBItem item = generateItem();
		UpdateItemRequest request = generateUpdateItemRequest(item);
		List<MultipartFile> files = generateMockedMultipartFiles();
		ItemResponse itemResponse = generateItemResponse(item);
		List<FileResponse> fileResponses = generateFileResponses(files);
		ItemWithFilesResponse itemWithFileResponse = new ItemWithFilesResponse(itemResponse, fileResponses);
		
		Mockito.when(itemService.updateItem(guid, request)).thenReturn(itemResponse);
		Mockito.when(fileService.updateFiles(guid, files)).thenReturn(fileResponses);
		
		ItemWithFilesResponse response = itemWithFileService.updateItemWithFiles(item.getGuid(), request, files);
		
		Assert.assertEquals(itemWithFileResponse.getItemResponse().getBrand(),response.getItemResponse().getBrand());
		Assert.assertEquals(itemWithFileResponse.getItemResponse().getGuid(),response.getItemResponse().getGuid());
		Assert.assertEquals(itemWithFileResponse.getFileResponses().get(0).getFileName(),response.getFileResponses().get(0).getFileName());
	}
	
}
