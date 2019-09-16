package com.example.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

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

	public HashMap<String, String> getAll_v2(int pageNumber) {
		HashMap<String, String> guidFirstImageMap = new HashMap<>();
		for (ItemResponse itemResponse : itemService.getAll(pageNumber)) {
			List<String> imgPaths = directoryService.getAllFilesFromDirectory(itemResponse.getGuid());

			if (!imgPaths.isEmpty()) {
				guidFirstImageMap.put(itemResponse.getGuid(), serverProperties.getServerPath() + imgPaths.get(0).replace(serverProperties.getRemovePath(), ""));
			} else {
				logger.info("PRE ITEM S GUID " + itemResponse.getGuid() + " SME NENASLI ZIADNE OBRAZKY.");
			}
		}
		return guidFirstImageMap;
	}

	@Transactional
	public ItemWithFilesResponse getItemWithFiles(String guid) throws UnsupportedEncodingException {

		ItemResponse itemResponse = itemService.getItem(guid);
		List<FileResponse> fileResponses = fileService.getFiles(guid);

		List<FileResponse> preparedFileResponses = new ArrayList<>();
		for (FileResponse fileResponse : fileResponses) {
			preparedFileResponses.add(new FileResponse(fileResponse.getFileName(), fileResponse.getFileDownloadUri(),
					fileResponse.getFileType(), fileResponse.getSize(),
					serverProperties.getServerPath() + fileResponse.getImgPath().replace(serverProperties.getRemovePath(), "") + "COMPRESSED"));
		}

		return new ItemWithFilesResponse(itemResponse, preparedFileResponses);
	}

	@Transactional
	public void removeItemWithFiles(String guid) {
		itemService.removeItem(guid);
		fileService.removeFile(guid);
		List<String> images = directoryService.getAllFilesFromDirectory(guid);
		for (String img : images) {
			directoryService.removeImageFromDirectory(img);
		}
	}

}
