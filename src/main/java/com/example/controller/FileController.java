package com.example.controller;

import com.example.payload.FileResponse;
import com.example.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    
    @Autowired
    private FileService fileService;

    @GetMapping("/all")
    public List<FileResponse> findAll() throws UnsupportedEncodingException {    	
    	logger.info("findAll");
    	return fileService.findAll();
    }
    
    @GetMapping("/byId")
    public FileResponse findById(@RequestParam("id") String id) throws UnsupportedEncodingException {  
    	logger.info("findById: " + id);
    	return fileService.findById(id);
    }
    
    @PostMapping("/uploadFile")
    public FileResponse uploadFile(@RequestParam("file") MultipartFile file) throws UnsupportedEncodingException {
    	logger.info("uploadFile: " + file.toString());
        return fileService.uploadFile(file, "GUID");
    }

    @PostMapping("/uploadMultipleFiles")
    public List<FileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws UnsupportedEncodingException {
    	logger.info("uploadMultipleFiles: " + Arrays.toString(files));
        return fileService.uploadMultipleFiles(files);
    }
    
    @PostMapping(value = ("/saveImages"), consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<FileResponse> saveImages(List<MultipartFile> files, String guid) throws UnsupportedEncodingException {
    	logger.info("uploadMultipleFiles: " + files);
        return fileService.saveImages(files, guid);
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
    	logger.info("downloadFile by fileId: " + fileId);
        return fileService.downloadFile(fileId);
    }
    
    @DeleteMapping("/removeFile")
    public void removeFile(@RequestParam("guid") String guid) {
    	logger.info("removeFile");
        fileService.removeFile(guid);
    }
    
    @PutMapping("/updateFiles")
    public void updateFiles(@RequestParam("guid") String guid, @RequestParam("files") List<MultipartFile> files) throws UnsupportedEncodingException {
    	logger.info("updateFiles by guid: " + guid);
        fileService.updateFiles(guid, files);
    }
   
}
