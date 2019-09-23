package com.example.service;

import com.example.config.ServerProperties;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DirectoryService {

    private static final Logger logger = LoggerFactory.getLogger(DirectoryService.class);

    @Autowired
    private ServerProperties serverProperties;

    public List<String> getAllFilesFromDirectory(String guid) {
        String imagesPath = serverProperties.getUploadPath();

        logger.info("getAllFilesFromDirectory - FROM DIRECTORY -" + imagesPath);

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

    private void createDirectory(String path) {
        File fff = new File(path);
        if (!fff.exists()) {
            if (fff.mkdir()) {
                logger.info("Directory Images was created. " + path);
            } else {
                logger.info("Failed to create Images directory. " + path);
            }
        } else {
            logger.info("Directory Images already exist. " + path);
        }
    }

    public void saveFileToDirecory(MultipartFile file, String imagePath) throws IOException {
        InputStream inputStream = null;
        BufferedImage image = null;
        try {
            inputStream = new ByteArrayInputStream(file.getBytes());
            image = ImageIO.read(inputStream);

            ImageIO.write(image, "jpg", new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            System.gc();
        }
        System.out.println("Done");
    }

    public String prepareAndSaveToDirectory(MultipartFile file, String guid, String fileName) throws IOException {
        String uploadPath = serverProperties.getUploadPath();
        String imagePath = uploadPath + "/UNCOMPRESSED" + guid + fileName;

        createDirectory(uploadPath);
        saveFileToDirecory(file, imagePath);

        return imagePath;
    }

    public void compressImg(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        File compressedImageFile = new File(imagePath.replace("UNCOMPRESSED", ""));

        InputStream is = new FileInputStream(imageFile);
        OutputStream os = new FileOutputStream(compressedImageFile);

        float quality = 0.5f;

        BufferedImage image = ImageIO.read(is);
        BufferedImage scaledImage = Scalr.resize(image, 640, 480);
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

        // appends a complete image stream containing a single image and
        // associated stream and image metadata and thumbnails to the output
        writer.write(null, new IIOImage(scaledImage, null, null), param);

        // close all streams
        is.close();
        os.close();
        ios.close();
        writer.dispose();
        System.gc();

        logger.info("Image should be compressed.");
        removeImageFromDirectory(imagePath);
    }

    public void removeImageFromDirectory(String path) {
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (NoSuchFileException e) {
            logger.info("No such file/directory exists");
        } catch (DirectoryNotEmptyException e) {
            logger.info("Directory is not empty.");
        } catch (IOException e) {
            logger.info("Invalid permissions.");
        }
        logger.info("Deletion successful.");
    }

}
