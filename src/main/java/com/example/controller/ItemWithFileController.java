package com.example.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Optional;

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

	@GetMapping("/getAll_v2")
	public ModelAndView getAll_v2(Optional<Integer> page) throws UnsupportedEncodingException {
		logger.info("/getAll_v2");

		HashMap<String, String> response = itemWithFileService.getAll_v2((page.isPresent()) ? page.get() : 0);
		return new ModelAndView("main", "guidFirstImageMap", response);
	}

	@GetMapping("/removeItemWithFilesTyKurva")
	public RedirectView removeItemWithFiles(@RequestParam("guid") String guid) {
		logger.info("removeItemWithFiles");
		itemWithFileService.removeItemWithFiles(guid);
		return new RedirectView("/getAll_v2");
	}

}
