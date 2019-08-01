package com.example.controller;

import com.example.payload.UploadFileResponse;
import com.example.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    
    @Autowired
    private FileService fileService;

    @GetMapping("/all")
    public List<UploadFileResponse> findAll() {    	
    	logger.info("findAll");
    	return fileService.findAll();
    }
    
    @GetMapping("/byId")
    public UploadFileResponse findById(@RequestParam("id") String id) {  
    	logger.info("findById: " + id);
    	return fileService.findById(id);
    }
    
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
    	logger.info("uploadFile: " + file.toString());
        return fileService.uploadFile(file, "GUID");
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
    	logger.info("uploadMultipleFiles: " + Arrays.toString(files));
        return fileService.uploadMultipleFiles(files);
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
    	logger.info("downloadFile by fileId: " + fileId);
        return fileService.downloadFile(fileId);
    }
   
}
