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
import java.nio.file.FileSystems;
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
	
	private Path absolutePath = FileSystems.getDefault().getPath("src").toAbsolutePath();
	private String path = (absolutePath.toString()+ "/main/webapp/WEB-INF/images/").replace("/","\\");

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
    		fileResponses.add(new FileResponse(file.getFileName(), "", file.getFileType(), 1, encodeBytes(file.getData()), file.getImgPath()));
    	}
    	logger.info("!!!!!!!!!!!!!!!!!!FROM DATABASE!!!!!!!!!!!!!!!!!!!!!!!!!");
    	
    	getAllFilesFromDirectory(guid);
    	logger.info("!!!!!!!!!!!!!!!!!!FROM IMAGES FOLDER!!!!!!!!!!!!!!!!!!!!!!!!!");
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
                file.getContentType(), file.getSize(), encodeBytes(dbFile.getData()), dbFile.getImgPath());
    }

	public DBFile storeFile(MultipartFile file, String guid) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			validateFileName(fileName);
			String imgPath = saveImageToDirectory(file, guid, fileName);
			DBFile dbFile = new DBFile(imgPath, fileName, file.getContentType(), file.getBytes(), guid);

			return fileRepository.save(dbFile);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}   
    
	private String saveImageToDirectory(MultipartFile file, String guid, String fileName) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		String finalPath = path + guid + fileName;

		try {
			inputStream = file.getInputStream();

			File newFile = new File(finalPath);
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
			e.printStackTrace();
		}
		return finalPath;
	}    
    
	public List<String> getAllFilesFromDirectory(String guid) {
		
		Path absolutePath2 = FileSystems.getDefault().getPath("src").toAbsolutePath();
		logger.info("1 " + absolutePath2);
		String path2 = (absolutePath2.toString()+ "/main/webapp/WEB-INF/images/").replace("/","\\");
		logger.info("2 " + path2);
		Path absolutePath3 = FileSystems.getDefault().getPath("app").toAbsolutePath();
		logger.info("3 " + absolutePath3);
		String path3 = (absolutePath3.toString()+ "/main/webapp/WEB-INF/images/").replace("/","\\");
		logger.info("4 " + path3);
		Path absolutePath4 = FileSystems.getDefault().getPath("app").toAbsolutePath();
		logger.info("5 " + absolutePath4);
		String path4 = (absolutePath4.toString()+ "/main/webapp/WEB-INF/").replace("/","\\");
		logger.info("6 " + path4);
		
		try (Stream<Path> walk = Files.walk(Paths.get(path))) {
			List<String> result = walk.map(x -> x.toString()).map(p -> p.replace(path, ""))
//    				.filter(f -> f.contains(guid))
					.collect(Collectors.toList());
			result.forEach(System.out::println);

			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
    
//    private void getFileFromDirectory() {
//    	Path path22 = FileSystems.getDefault().getPath("src").toAbsolutePath();
//    	String path = (path22.toString()+ "/main/webapp/WEB-INF/images/").replace("/","\\");
//    	System.out.println("XXXXXXXXXXXX - " + path);
//    	try (Stream<Path> walk = Files.walk(Paths.get(path))) {
//
//    		List<String> result = walk.filter(Files::isRegularFile)
//    				.map(x -> x.toString()).collect(Collectors.toList());
//
//    		result.forEach(System.out::println);    		
//
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    	}
//    }    

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
