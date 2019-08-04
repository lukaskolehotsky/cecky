package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.payload.ItemWithFilesResponse;
import com.example.requests.CreateItemRequest;
import com.example.requests.UpdateItemRequest;
import com.example.service.ItemWithFileService;

@RestController
public class ItemWithFileController {
	
	@Autowired
	private ItemWithFileService itemWithFileService;

	private static final Logger logger = LoggerFactory.getLogger(ItemWithFileController.class);
	
	@GetMapping("/getItemWithFiles")
	public ItemWithFilesResponse getItemWithFiles(@RequestParam("guid") String guid) {
		logger.info("getItemWithFiles by guid: " + guid);
		return itemWithFileService.getItemWithFiles(guid);
    }
	
	@GetMapping("/getAllItemsWithFiles")
	public List<ItemWithFilesResponse> getAllItemsWithFiles() {
		logger.info("getAllItemsWithFiles");
		return itemWithFileService.getAllItemsWithFiles();
    }
	
	@DeleteMapping("/removeItemWithFiles")
	public void removeItemWithFiles(@RequestParam("guid") String guid) {
		logger.info("removeItemWithFiles");
		itemWithFileService.removeItemWithFiles(guid);
    }
	
	@PutMapping("/updateItemWithFiles")
	public ItemWithFilesResponse updateItemWithFiles(@RequestParam("guid") String guid, UpdateItemRequest request, List<MultipartFile> files) {
		logger.info("updateItemWithFiles");
		return itemWithFileService.updateItemWithFiles(guid, request, files);
    }
	
	@PostMapping("/createItemWithFiles")
	public ItemWithFilesResponse createItemWithFiles(CreateItemRequest request, List<MultipartFile> files) {
		logger.info("createItemWithFiles");
		return itemWithFileService.createItemWithFiles(request, files);
    }
	
}
