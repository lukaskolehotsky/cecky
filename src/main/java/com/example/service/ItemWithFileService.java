package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.payload.FileResponse;
import com.example.payload.ItemResponse;
import com.example.payload.ItemWithFilesResponse;

@Service
public class ItemWithFileService {
	
	@Autowired
    private ItemService itemService;
	
	@Autowired
    private FileService fileService;

	@Transactional
    public ItemWithFilesResponse getItemWithFiles(String guid) {
    	
    	ItemResponse itemResponse = itemService.getItem(guid);
    	List<FileResponse> fileResponses = fileService.getFiles(guid);
    	
    	return new ItemWithFilesResponse(itemResponse, fileResponses);
    	
    }
	
}
