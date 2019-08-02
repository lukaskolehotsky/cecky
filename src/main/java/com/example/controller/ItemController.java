package com.example.controller;

import com.example.requests.CreateItemRequest;
import com.example.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.payload.ItemResponse;

@RestController
public class ItemController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemService itemService;

	@GetMapping("/welcome")
	public ModelAndView createItem(@RequestParam("request") CreateItemRequest request) {
		logger.info("createItem: ");

		itemService.createItem(request);
		return new ModelAndView("welcome", "item", request);
	}
	
	@GetMapping("/getItem")
    public ItemResponse getItem(@RequestParam("guid") String guid) {
		logger.info("getItem by guid: " + guid);
		return itemService.getItem(guid);
    }
}
