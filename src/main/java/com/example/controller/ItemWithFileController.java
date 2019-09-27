package com.example.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.example.payload.ItemWithFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.payload.ItemWithFilesResponse;
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

	@GetMapping("/getItemWithFileResponses")
	public ModelAndView getItemWithFileResponses(Optional<Integer> page) throws UnsupportedEncodingException {
		logger.info("/getItemWithFileResponses");

		List<ItemWithFileResponse> response = itemWithFileService.getItemWithFileResponses(page.orElse(0));
		return new ModelAndView("main", "itemWithFileResponses", response);
	}

	@GetMapping("/removeItemWithFiles")
	public RedirectView removeItemWithFiles(@RequestParam("guid") String guid) {
		logger.info("removeItemWithFiles");
		itemWithFileService.removeItemWithFiles(guid);
		return new RedirectView("/getItemWithFileResponses");
	}

}
