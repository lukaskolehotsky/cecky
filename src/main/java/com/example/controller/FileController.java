package com.example.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.example.service.FileService;

@RestController
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileService fileService;

	@PostMapping(value = ("/saveImages"), consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public RedirectView saveImages(List<MultipartFile> files, String guid) throws IOException {
		logger.info("FileController - saveImages by guid: " + guid);
		fileService.saveImages(files, guid);
		return new RedirectView("/getItemWithFileResponses");
	}

	@DeleteMapping("/removeFile")
	public void removeFile(@RequestParam("guid") String guid) {
		logger.info("FileController - removeFile by guid: " + guid);
		fileService.removeFile(guid);
	}

	@PutMapping("/updateFiles")
	public RedirectView updateFiles(@RequestParam("guid") String guid, @RequestParam("files") List<MultipartFile> files)
			throws IOException {
		logger.info("FileController - updateFiles by guid: " + guid);
		fileService.updateFiles(guid, files);
		return new RedirectView("/getItemWithFileResponses");
	}

	@GetMapping("/clearCache")
	@CacheEvict(value = "fileResponses", allEntries = true)
	public void clearCache() {
		logger.info("FileController - clearCache");
	}

}
