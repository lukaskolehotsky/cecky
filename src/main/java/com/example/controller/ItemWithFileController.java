package com.example.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
	public ModelAndView getItemWithFiles(@RequestParam("guid") String guid) throws UnsupportedEncodingException {
		logger.info("getItemWithFiles by guid: " + guid);
		ItemWithFilesResponse response = itemWithFileService.getItemWithFiles(guid);
		return new ModelAndView("viewItem", "itemWithFiles", response);
    }
	
	@GetMapping("/getAllItemsWithFiles")
	public ModelAndView getAllItemsWithFiles(Optional<Integer> page) throws UnsupportedEncodingException {
		logger.info("getAllItemsWithFiles");
		 
		List<ItemWithFilesResponse> response = itemWithFileService.getAllItemsWithFiles((page.isPresent()) ? page.get() : 0);
		return new ModelAndView("main", "itemsWithFiles", response);
    }
	
	@GetMapping("/removeItemWithFiles")
	public RedirectView removeItemWithFiles(@RequestParam("guid") String guid) {
		logger.info("removeItemWithFiles");
		itemWithFileService.removeItemWithFiles(guid);
		return new RedirectView("/getAllItemsWithFiles");
    }
	
	@PutMapping("/updateItemWithFiles")
	public ItemWithFilesResponse updateItemWithFiles(@RequestParam("guid") String guid, UpdateItemRequest request, List<MultipartFile> files) throws UnsupportedEncodingException {
		logger.info("updateItemWithFiles");
		return itemWithFileService.updateItemWithFiles(guid, request, files);
    }
	
	@PostMapping("/createItemWithFiles")
	public ItemWithFilesResponse createItemWithFiles(CreateItemRequest request, List<MultipartFile> files) throws UnsupportedEncodingException {
		logger.info("createItemWithFiles");
		return itemWithFileService.createItemWithFiles(request, files);
    }
	
}
