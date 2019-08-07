package com.example.service;

import com.example.Utils.Utils;
import com.example.exception.MyFileNotFoundException;
import com.example.exception.FileStorageException;
import com.example.model.DBFile;
import com.example.payload.FileResponse;
import com.example.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class FileService extends Utils{
	
	private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private FileRepository fileRepository;
    
    @Transactional
    public void removeFile(String guid) {
    	fileRepository.deleteByGuid(guid);
    }
    
//    @Cacheable(value = "fileResponses", key = "#guid")
    public List<FileResponse> getFiles(String guid) throws UnsupportedEncodingException {
    	List<FileResponse> fileResponses = new ArrayList<>();
    	for(DBFile file: fileRepository.findByGuid(guid)) {       		                        
    		fileResponses.add(new FileResponse(file.getFileName(), "", file.getFileType(), 1, encodeBytes(file.getData())));
    	}
    	logger.info("!!!!!!!!!!!!!!!!!!FROM DATABASE!!!!!!!!!!!!!!!!!!!!!!!!!");
    	return fileResponses;
    }    

    public List<FileResponse> findAll() throws UnsupportedEncodingException{
    	
    	List<DBFile> files;
    	files = fileRepository.findAll();

        List<FileResponse> responses = new ArrayList<>();
    	for(DBFile file: files) {
    		responses.add(generateFileResponse(file));
    	}

    	return responses;
    }
    
    public FileResponse findById(String id) throws UnsupportedEncodingException {
    	Optional<DBFile> file = fileRepository.findById(id);
    	if(file.isPresent()){
            return generateFileResponse(file.get());
        } else{
    	    throw new IllegalArgumentException("File not found.");
        }
    }
     
    @Transactional
//    @CachePut(value = "fileResponses", key = "#guid")
    public List<FileResponse> saveImages(List<MultipartFile> files, String guid) throws UnsupportedEncodingException {   
    	
    	List<FileResponse> fileResponses = new ArrayList<>();
    	for(MultipartFile file: files) {
    		fileResponses.add(saveImage(file, guid));
    	}
    	return fileResponses;
    }
    
    public FileResponse saveImage(MultipartFile file, String guid) throws UnsupportedEncodingException {
        DBFile dbFile = storeFile(file, guid);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new FileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize(), encodeBytes(dbFile.getData()));
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
    
    @Transactional
//    @CachePut(value = "fileResponses", key = "#guid")
    public List<FileResponse> updateFiles(String guid, List<MultipartFile> files) throws UnsupportedEncodingException {
    	fileRepository.deleteByGuid(guid);
    	return saveImages(files, guid);
    }

}
