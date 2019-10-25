package com.example.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import com.example.payload.ItemWithFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.config.ServerProperties;
import com.example.payload.FileResponse;
import com.example.payload.ItemResponse;
import com.example.payload.ItemWithFilesResponse;

@Service
public class ItemWithFileService {

	private static final Logger logger = LoggerFactory.getLogger(ItemWithFileService.class);
	
	@Autowired
    private ServerProperties serverProperties;

	@Autowired
	private ItemService itemService;

	@Autowired
	private FileService fileService;
	
	@Autowired
	private DirectoryService directoryService;

	public List<ItemWithFileResponse> getItemWithFileResponses(int pageNumber) {
		List<ItemWithFileResponse> itemWithFileResponses = new ArrayList<>();

		List<ItemResponse> itemResponses = itemService.getAll(pageNumber);

		if(!itemResponses.isEmpty()) {
			logger.info("!!! itemService.getAll(pageNumber).size() == " + itemService.getAll(pageNumber).size());
		} else {
			logger.info("!!! itemService.getAll(pageNumber).size() == 0");
		}

		for (ItemResponse itemResponse : itemResponses) {

			logger.info("!!! Pre tento itemResponse hladame vsetky files v directory - " + itemResponse.toString());

			List<String> imgPaths = directoryService.getAllFilesFromDirectory(itemResponse.getGuid());

			if(!imgPaths.isEmpty()){
				logger.info("!!! directoryService.getAllFilesFromDirectory(itemResponse.getGuid()).size() == " + imgPaths.size());
			} else {
				logger.info("!!! directoryService.getAllFilesFromDirectory(itemResponse.getGuid()).size() == 0");
			}

			List<FileResponse> fileResponses = fileService.getFiles(itemResponse.getGuid());

			if(!fileResponses.isEmpty()){
				logger.info("!!! fileService.getFiles(itemResponse.getGuid()).size() == " + fileResponses.size());
			} else {
				logger.info("!!! fileService.getFiles(itemResponse.getGuid()).size() == 0");
			}

			for (FileResponse fileResponse : fileResponses) {

				String firstPath = imgPaths.get(0).replace("/", "\\");
				String secondPath = fileResponse.getImgPath().replace("/", "\\");

				logger.info("!!! Pre tento fileResponse - " + fileResponse.toString());
				logger.info("!!! Prvy cesta  - " + firstPath + " CONTAINS " + secondPath);
				logger.info("!!! Porovnanie == " + firstPath.contains(secondPath));

				if (firstPath.contains(secondPath)) {

					String finalPath = serverProperties.getServerPath() + imgPaths.get(0).replace(serverProperties.getRemovePath(), "");
					logger.info("!!! Do fileResponse nasetujem finalnu cestu " + finalPath);

					fileResponse.setImgPath(finalPath);

					ItemWithFileResponse itemWithFileResponse = new ItemWithFileResponse(itemResponse, fileResponse);

					logger.info("!!! itemWithFileResponse - " + itemWithFileResponse.toString());

					itemWithFileResponses.add(itemWithFileResponse);
				}
			}
		}
		return itemWithFileResponses;
	}

	@Transactional
	public ItemWithFilesResponse getItemWithFiles(String guid) throws UnsupportedEncodingException {

		ItemResponse itemResponse = itemService.getItem(guid);
		List<FileResponse> fileResponses = fileService.getFiles(guid);

		List<FileResponse> preparedFileResponses = new ArrayList<>();
		for (FileResponse fileResponse : fileResponses) {
			preparedFileResponses.add(new FileResponse(fileResponse.getFileName(), fileResponse.getFileDownloadUri(),
					fileResponse.getFileType(), fileResponse.getSize(),
					serverProperties.getServerPath() + fileResponse.getImgPath().replace(serverProperties.getRemovePath(), "")));
		}

		return new ItemWithFilesResponse(itemResponse, preparedFileResponses);
	}

	@Transactional
	public void removeItemWithFiles(String guid, String authenticationCode) {
		
		verifyAuthenticationCode(guid, authenticationCode);
		
		itemService.removeItem(guid);
		fileService.removeFile(guid);
		List<String> images = directoryService.getAllFilesFromDirectory(guid);
		for (String img : images) {
			directoryService.removeImageFromDirectory(img);
		}
	}
	
	private void verifyAuthenticationCode(String guid, String authenticationCode) {
		ItemResponse itemResponse = itemService.getItem(guid);
		
		if(!itemResponse.getAuthenticationCode().equals(authenticationCode)) {
			throw new IllegalArgumentException("Nespravny authentifikacny kod.");
		}		
	}

}
