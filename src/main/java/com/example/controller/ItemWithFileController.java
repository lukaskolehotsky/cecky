package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.payload.ItemWithFilesResponse;
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
	
}