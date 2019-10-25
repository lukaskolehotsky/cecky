package com.example.controller;

import com.example.requests.CreateItemRequest;
import com.example.requests.UpdateItemRequest;
import com.example.service.ItemService;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.payload.ItemResponse;

@RestController
public class ItemController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private ItemService itemService;

	@GetMapping("/createItem1")
	public ModelAndView createItem1() {
		logger.info("createItem1: ");

		ModelAndView modelAndView = new ModelAndView("createItem1", "item",
				new ItemResponse("", "", "",
						LocalDateTime.now(), "", "",
						new BigInteger("0"),
						"", "", 0L, 0L));
		return modelAndView;
	}

	@PostMapping("/createItem2")
	public ModelAndView createItem2(CreateItemRequest request) {
		logger.info("createItem2: ");

		ItemResponse response = itemService.createItem(request);
		return new ModelAndView("createItem2", "item", response);
	}

	@GetMapping("/getItem")
	public ItemResponse getItem(@RequestParam("guid") String guid) {
		logger.info("getItem by guid: " + guid);
		return itemService.getItem(guid);
	}

	@GetMapping("/changeAuthenticationCode")
	public String changeAuthenticationCode(@RequestParam("guid") String guid, @RequestParam("email") String email) {
		logger.info("changeAuthenticationCode by guid: " + guid + " and email: " + email);
		return itemService.changeAuthenticationCode(guid, email);
	}

	@GetMapping("/updateItem1")
	public ModelAndView updateItem1(@RequestParam("guid") String guid) {
		logger.info("updateItem1: ");

		ItemResponse response = itemService.getItem(guid);
		response.setAuthenticationCode("");

		return new ModelAndView("updateItem1", "item", response);
	}

	@PostMapping("/updateItem2")
	public ModelAndView updateItem2(@RequestParam("guid") String guid, UpdateItemRequest request) {
		logger.info("updateItem by guid: " + guid + " with update request: " + request);

		ItemResponse response = itemService.updateItem(guid, request);

		return new ModelAndView("updateItem2", "item", response);
	}

}
