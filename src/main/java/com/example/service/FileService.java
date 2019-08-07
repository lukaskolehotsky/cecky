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

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
    
    @Cacheable(value = "fileResponses", key = "#guid")
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
    @CachePut(value = "fileResponses", key = "#guid")
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

        StringJoiner sj = new StringJoiner(" , ");
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes(), guid);

            saveToDirectory(file, guid);
            getFileFromDirectory();
            
            return fileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    
    private void getFileFromDirectory() {
    	try (Stream<Path> walk = Files.walk(Paths.get("C:/javaprojects/Heroku/cecky/src/main/webapp/WEB-INF/images/"))) {

    		List<String> result = walk.filter(Files::isRegularFile)
    				.map(x -> x.toString()).collect(Collectors.toList());

    		result.forEach(System.out::println);

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    private void saveToDirectory(MultipartFile file, String guid) { 	
    	String fileName = file.getOriginalFilename();  
    	InputStream inputStream = null;
    	OutputStream outputStream = null;
    	try {        		
    		   inputStream = file.getInputStream();    
    		   File newFile = new File("C:/javaprojects/Heroku/cecky/src/main/webapp/WEB-INF/images/" + guid +fileName);    
    		   if (!newFile.exists()) {    
    		    newFile.createNewFile();    
    		   }    
    		   outputStream = new FileOutputStream(newFile);    
    		   int read = 0;    
    		   byte[] bytes = new byte[1024];    
    		    
    		   while ((read = inputStream.read(bytes)) != -1) {    
    		    outputStream.write(bytes, 0, read);    
    		   }    
    		  } catch (IOException e) {    
    		   // TODO Auto-generated catch block    
    		   e.printStackTrace();    
    		  }
    }
    

    public DBFile getFile(String fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
    
    @Transactional
    @CachePut(value = "fileResponses", key = "#guid")
    public List<FileResponse> updateFiles(String guid, List<MultipartFile> files) throws UnsupportedEncodingException {
    	fileRepository.deleteByGuid(guid);
    	return saveImages(files, guid);
    }

}
