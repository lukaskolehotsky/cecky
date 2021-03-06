package com.example.service;

import com.example.Utils.Utils;
import com.example.model.DBFile;
import com.example.payload.FileResponse;
import com.example.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class FileService extends Utils {

//	private static final Logger logger = LoggerFactory.getLogger(FileService.class);

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private DirectoryService directoryService;

	@Transactional
	public void removeFile(String guid) {
		fileRepository.deleteByGuid(guid);
	}

//    @Cacheable(value = "fileResponses", key = "#guid")
	public List<FileResponse> getFiles(String guid) {
		List<FileResponse> fileResponses = new ArrayList<>();
		for (DBFile file : fileRepository.findByGuid(guid)) {
//			logger.info("findByGuid - FROM DATABASE - " + file.toString());
			fileResponses.add(new FileResponse(file.getFileName(), "", file.getFileType(), 1, file.getImgPath()));
		}

		directoryService.getAllFilesFromDirectory(guid);
		return fileResponses;
	}

	@Transactional
//    @CachePut(value = "fileResponses", key = "#guid")
	public List<FileResponse> saveImages(List<MultipartFile> files, String guid) throws IOException {

		List<FileResponse> fileResponses = new ArrayList<>();
		for (MultipartFile file : files) {
			fileResponses.add(saveImage(file, guid));
		}
		return fileResponses;
	}

	public FileResponse saveImage(MultipartFile file, String guid) throws IOException {
		DBFile dbFile = storeFile(file, guid);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(dbFile.getId()).toUriString();

		return new FileResponse(dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize(),
				dbFile.getImgPath());
	}

	public DBFile storeFile(MultipartFile file, String guid) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		validateFileName(fileName);
		String imagePath = directoryService.prepareAndSaveToDirectory(file, guid, fileName);

		DBFile dbFile = generateDBFile(imagePath.replace("UNCOMPRESSED", ""), fileName, file.getContentType(), guid);

		directoryService.compressImg(imagePath);
		directoryService.removeImageFromDirectory(imagePath);

		return fileRepository.save(dbFile);
	}

	@Transactional
//    @CachePut(value = "fileResponses", key = "#guid")
	public List<FileResponse> updateFiles(String guid, List<MultipartFile> files) throws IOException {
		fileRepository.deleteByGuid(guid);

		List<String> images = directoryService.getAllFilesFromDirectory(guid);
		for (String img : images) {
			directoryService.removeImageFromDirectory(img);
		}

		return saveImages(files, guid);
	}

}
