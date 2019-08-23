package com.example.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DirectoryService {
	
	private static final Logger logger = LoggerFactory.getLogger(DirectoryService.class);
	
	@Value("${upload.path}")
	private String uploadPath;

	List<String> getAllFilesFromDirectory(String guid) {
		String imagesPath = uploadPath;

		logger.info("GET ALL ITEMS FROM DIRECTORY - " + imagesPath);

		try (Stream<Path> walk = Files.walk(Paths.get(imagesPath))) {
			List<String> result = walk.map(x -> x.toString()).filter(f -> f.contains(guid))
					.collect(Collectors.toList());
			result.forEach(System.out::println);

			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String saveImageToDirectory(MultipartFile file, String guid, String fileName) throws IOException {
		String imagesPath = this.uploadPath;

		File fff = new File(imagesPath);
		if (!fff.exists()) {
			if (fff.mkdir()) {
				logger.info("DIRECTORY IMAGES IS CREATED!" + imagesPath);
			} else {
				logger.info("FAILED TO CREATE IMAGES DIRECTORY!" + imagesPath);
			}
		}

		InputStream inputStream = null;
		OutputStream outputStream = null;
		String finalPath = imagesPath + "/" + guid + fileName;

		logger.info("FINAL PATH" + finalPath);

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
		} finally {
			outputStream.flush();
			outputStream.close();
			outputStream = null;
			System.gc();
		}

//		compressImg(finalPath);

		System.out.println("IMAGE SHOULD BE CREATED - " + finalPath);
		return finalPath;
	}
	
	void compressImg(String path) throws IOException {
		File imageFile = new File(path);
		File compressedImageFile = new File(path + "COMPRESSED");

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

//        removeImageFromDirectory(path);

		// appends a complete image stream containing a single image and
		// associated stream and image metadata and thumbnails to the output
		writer.write(null, new IIOImage(image, null, null), param);

		// close all streams
		is.close();
		os.close();
		ios.close();
		writer.dispose();
		System.gc();

		logger.info("IMAGE SHOULD BE COMPRESSED");
		removeImageFromDirectory(path);
	}
	
	void removeImageFromDirectory(String path) {
		try {
			File file = new File(path);
			logger.info("YOU ARE TRYING TO DELETE IMAGE FROM - " + path);
			logger.info(getReasonForFileDeletionFailureInPlainEnglish(file));

			if (file.delete()) {
				logger.info("IMAGE WAS DELETED! " + file.getName());
			} else {
				logger.info("DELETE OPERATION IS FAILED.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getReasonForFileDeletionFailureInPlainEnglish(File file) {
		try {
			if (!file.exists())
				return "IT DOESN'T EXIST IN THE FIRST PLACE.";
			else if (file.isDirectory() && file.list().length > 0)
				return "IT'S A DIRECTORY AND IT'S NOT EMPTY.";
			else
				return "SOMEBODY ELSE HAS IT OPEN, WE DON'T HAVE WRITE PERMISSIONS, OR SOMEBODY STOLE MY DISK.";
		} catch (SecurityException e) {
			return "WE'RE SANDBOXED AND DON'T HAVE FILESYSTEM ACCESS.";
		}
	}
	
}
