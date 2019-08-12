package com.example.service;

import com.example.Utils.Utils;
import com.example.exception.MyFileNotFoundException;
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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
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
    		fileResponses.add(new FileResponse(file.getFileName(), "", file.getFileType(), 1, file.getImgPath()));
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
    public List<FileResponse> saveImages(List<MultipartFile> files, String guid) throws IOException {   
    	
    	List<FileResponse> fileResponses = new ArrayList<>();
    	for(MultipartFile file: files) {
    		fileResponses.add(saveImage(file, guid));
    	}
    	return fileResponses;
    }
    
    public FileResponse saveImage(MultipartFile file, String guid) throws IOException {
        DBFile dbFile = storeFile(file, guid);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new FileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize(), dbFile.getImgPath());
    }

	public DBFile storeFile(MultipartFile file, String guid) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		validateFileName(fileName);
		String imgPath = saveImageToDirectory(file, guid, fileName);
		DBFile dbFile = new DBFile(imgPath, fileName, file.getContentType(), guid);

		removeImageFromDirectory(imgPath);
		
		return fileRepository.save(dbFile);
	}
	
	public String getReasonForFileDeletionFailureInPlainEnglish(File file) {
	    try {
	        if (!file.exists())
	            return "It doesn't exist in the first place.";
	        else if (file.isDirectory() && file.list().length > 0)
	            return "It's a directory and it's not empty.";
	        else
	            return "Somebody else has it open, we don't have write permissions, or somebody stole my disk.";
	    } catch (SecurityException e) {
	        return "We're sandboxed and don't have filesystem access.";
	    }
	}
	
	private void removeImageFromDirectory(String path) {
		try{
    		String cesta = path.replace("\\", "\\\\");
    		File file = new File(cesta);
    		System.out.println("MAZES Z - " + cesta);
    		
    		System.out.println(getReasonForFileDeletionFailureInPlainEnglish(file));
    		
    		if(file.delete()){
    			System.out.println(file.getName() + " IS DELETED!");
    		}else{
    			System.out.println("DELETE OPERATION IS FAILED.");
    		}
    	   
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		
    	}
	}
   
	private String saveImageToDirectory(MultipartFile file, String guid, String fileName) throws IOException {
		
		File fff = new File(path);
        if (!fff.exists()) {
            if (fff.mkdir()) {
            	System.out.println(path);
                System.out.println("Directory images is created!");
            } else {
            	System.out.println(path);
                System.out.println("Failed to create images directory!");
            }
        }
		
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
			
			compressImg(finalPath);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	finally {
			outputStream.flush();
			outputStream.close();
			outputStream = null;
            System.gc();
		}	
		
		System.out.println("image should be CREATED - " + finalPath);
		return finalPath;
	} 
	
	
	

	
	public void compressImg(String path) throws IOException {
		File imageFile = new File(path);
//        File compressedImageFile = new File("myimage_compressed.jpg");
        File compressedImageFile = new File(path+"COMPRESSED");
 
        InputStream is = new FileInputStream(imageFile);
        OutputStream os = new FileOutputStream(compressedImageFile);
 
        float quality = 0.5f;
 
        // create a BufferedImage as the result of decoding the supplied InputStream
        BufferedImage image = ImageIO.read(is);
 
        // get all image writers for JPG format
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
 
        if (!writers.hasNext())
            throw new IllegalStateException("No writers found");
 
        
        
        ImageWriter writer = (ImageWriter) writers.next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);
 
        ImageWriteParam param = writer.getDefaultWriteParam();
 
        // compress to a given quality
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality);
    
        removeImageFromDirectory(path);
        
        // appends a complete image stream containing a single image and
        //associated stream and image metadata and thumbnails to the output
        writer.write(null, new IIOImage(image, null, null), param);
 
        // close all streams
        is.close();
        os.close();
        ios.close();
        writer.dispose();
        System.gc();
	}
    
	public List<String> getAllFilesFromDirectory(String guid) {
		
		try (Stream<Path> walk = Files.walk(Paths.get(path))) {
			List<String> result = walk.map(x -> x.toString()).map(p -> p.replace(path, ""))
    				.filter(f -> f.contains(guid))
					.collect(Collectors.toList());
			result.forEach(System.out::println);
			
			System.out.println("DAVAS PREC - " + path);

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
    public List<FileResponse> updateFiles(String guid, List<MultipartFile> files) throws IOException {
    	fileRepository.deleteByGuid(guid);
    	return saveImages(files, guid);
    }

}
