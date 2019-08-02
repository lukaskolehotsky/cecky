package com.example.service;

import com.example.Utils.Utils;
import com.example.exception.MyFileNotFoundException;
import com.example.exception.FileStorageException;
import com.example.model.DBFile;
import com.example.payload.ItemResponse;
import com.example.payload.FileResponse;
import com.example.repository.FileRepository;

import com.example.requests.CreateItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileService extends Utils{

    @Autowired
    private FileRepository fileRepository;
    
    @Autowired
    private ItemService itemService;
    
    public List<FileResponse> getFiles(String guid) {
    	List<FileResponse> fileResponses = new ArrayList<>();
    	for(DBFile file: fileRepository.findByGuid(guid)) {
    		fileResponses.add(new FileResponse(file.getFileName(), "", file.getFileType(), 1));
    	}
    	return fileResponses;
    }

    public List<FileResponse> findAll(){
    	
    	List<DBFile> files;
    	files = fileRepository.findAll();

        List<FileResponse> responses = new ArrayList<>();
    	for(DBFile f: files) {
    		responses.add(new FileResponse(f.getFileName(), "", f.getFileType(), 1));
    	}

    	return responses;
    }
    
    public FileResponse findById(String id) {
    	Optional<DBFile> file = fileRepository.findById(id);
    	if(file.isPresent()){
            return new FileResponse(file.get().getFileName(), "", file.get().getFileType(), 1);
        } else{
    	    throw new IllegalArgumentException("File not found.");
        }
    }
    
    public FileResponse uploadFile(MultipartFile file, String guid) {
        DBFile dbFile = storeFile(file, guid);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new FileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    
    public List<FileResponse> uploadMultipleFiles(MultipartFile[] files) {
        // TODO - should be taken from parameter
        CreateItemRequest createItemRequest = new CreateItemRequest("brand", "type");

    	ItemResponse createdItem = itemService.createItem(createItemRequest);
    	
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file, createdItem.getGuid()))
                .collect(Collectors.toList());
    }

    public DBFile storeFile(MultipartFile file, String guid) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes(), guid);

            return fileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getFile(String fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

    // TODO - we don't need in the future
    public ResponseEntity<Resource> downloadFile(String fileId) {
        // Load file from database
        DBFile dbFile = getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
}
