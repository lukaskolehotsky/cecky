package com.example.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.payload.ItemWithFileResponse;
import com.example.payload.ItemWithFilesResponse;
import com.example.requests.ContactOwnerRequest;
import com.example.requests.RemoveItemWithFilesRequest;
import com.example.requests.SearchRequest;
import com.example.service.ItemWithFileService;

@RestController
public class ItemWithFileController {

	@Autowired
	private ItemWithFileService itemWithFileService;

	private static final Logger logger = LoggerFactory.getLogger(ItemWithFileController.class);
	
	@GetMapping("/getItemWithFiles")
	public ModelAndView getItemWithFiles(@RequestParam("guid") String guid) throws UnsupportedEncodingException {
		logger.info("ItemWithFileController - getItemWithFiles by guid: " + guid);
		ItemWithFilesResponse response = itemWithFileService.getItemWithFiles(guid);
		
		ModelAndView view = new ModelAndView("viewItem", "itemWithFiles", response);
		view.getModelMap().addAttribute("removeItemWithFilesRequest", new RemoveItemWithFilesRequest(""));
		view.getModelMap().addAttribute("contactOwnerRequest", new ContactOwnerRequest("","",""));
		return view;
	}
	
	@PostMapping("/search")
	public ModelAndView search(SearchRequest searchRequest) {
		logger.info("ItemWithFileController - search : " + searchRequest.toString());
		
		List<ItemWithFileResponse> response = itemWithFileService.search(searchRequest);
		
		return new ModelAndView("main", "itemWithFileResponses", response);
	}

	@GetMapping("/getItemWithFileResponses")
	public ModelAndView getItemWithFileResponses(Optional<Integer> page) throws UnsupportedEncodingException {
		logger.info("ItemWithFileController - getItemWithFileResponses with page: " + page);

		List<ItemWithFileResponse> response = itemWithFileService.getItemWithFileResponses(page.orElse(0));
		
		ModelAndView view = new ModelAndView("main", "itemWithFileResponses", response);
		view.getModelMap().addAttribute("searchRequest", new SearchRequest("", ""));
		return view;
	}

	@PostMapping("/removeItemWithFiles")
	public RedirectView removeItemWithFiles(@RequestParam("guid") String guid, RemoveItemWithFilesRequest removeItemWithFilesRequest) {
		logger.info("ItemWithFileController - removeItemWithFiles by guid " + guid + "and request " + removeItemWithFilesRequest.toString());
		itemWithFileService.removeItemWithFiles(guid, removeItemWithFilesRequest.getAuthCode());
		return new RedirectView("/getItemWithFileResponses");
	}

}
