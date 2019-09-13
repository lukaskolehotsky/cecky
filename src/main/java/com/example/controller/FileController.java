package com.example.controller;

import com.example.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    
    @Autowired
    private FileService fileService;
    
    @PostMapping(value = ("/saveImages"), consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RedirectView saveImages(List<MultipartFile> files, String guid) throws IOException {
    	logger.info("uploadMultipleFiles: " + files);
        fileService.saveImages(files, guid);
    	return new RedirectView("/getAll_v2");
    }
    
    @DeleteMapping("/removeFile")
    public void removeFile(@RequestParam("guid") String guid) {
    	logger.info("removeFile");
        fileService.removeFile(guid);
    }
    
    @PutMapping("/updateFiles")
    public RedirectView updateFiles(@RequestParam("guid") String guid, @RequestParam("files") List<MultipartFile> files) throws IOException {
    	logger.info("updateFiles by guid: " + guid);
        fileService.updateFiles(guid, files);
        return new RedirectView("/getAll_v2");
    }
    
    @GetMapping("/clearCache")
    @CacheEvict(value = "fileResponses", allEntries = true)
    public void clearCache() {    	
    	logger.info("clearCache");
    }
   
}
