package com.example.services;

import com.example.config.ServerProperties;
import com.example.service.DirectoryService;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DirectoryServiceTest extends AbstractTest {

    @InjectMocks
    private DirectoryService directoryService;

    @Mock
    private ServerProperties serverProperties;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    private String fileName = "testImageName.jpg";
    private String guid = "testGuid";

    @Test
    public void test11_getAllFilesFromDirectory() {
        String uploadPath = "C:/javaprojects/Heroku/cecky/src/test/resources/";

        Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);

        List<String> response = directoryService.getAllFilesFromDirectory("imageForTest");

        Assert.assertEquals(1, response.size());
    }

    @Test
    public void test12_saveFileToDirectory() throws IOException {
        String imageForTestPath = "C:/javaprojects/Heroku/cecky/src/test/resources/imageForTest.jpg";
        String newImagePath = "C:/javaprojects/Heroku/cecky/src/test/resources/" + guid + fileName;

        BufferedImage image = null;
        ByteArrayOutputStream baos = null;
        try {
            image = ImageIO.read(new File(imageForTestPath));
            baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            byte[] bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            baos.flush();
            baos.close();
            System.gc();
        }

        MultipartFile file = new MockMultipartFile(guid + fileName, baos.toByteArray());

        directoryService.saveFileToDirecory(file, newImagePath);
    }

    @Test
    public void test13_getAllFilesFromDirectory() {
        String uploadPath = "C:/javaprojects/Heroku/cecky/src/test/resources/";

        Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);

        List<String> response = directoryService.getAllFilesFromDirectory(guid);

        Assert.assertEquals(1, response.size());
    }

    @Test()
    public void test14_getAllFilesFromDirectory_noSuchFileException() {
        String uploadPath = "C:/javaprojects/Heroku/cecky/src/test/resourcesX";

        Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);

        List<String> response = directoryService.getAllFilesFromDirectory(guid);

        Assert.assertEquals(null, response);
    }

    @Test()
    public void test15_removeImageFromDirectory() {
        String imagePath = "C:/javaprojects/Heroku/cecky/src/test/resources/" + guid + fileName;

        directoryService.removeImageFromDirectory(imagePath);
    }

    @Test
    public void test16_getAllFilesFromDirectory() {
        String uploadPath = "C:/javaprojects/Heroku/cecky/src/test/resources/";

        Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);
        List<String> response = directoryService.getAllFilesFromDirectory(guid);

        Assert.assertEquals(0, response.size());
    }

    @Test
    public void test17_prepareAndSaveToDirectory() throws IOException {
        String uploadPath = "C:/javaprojects/Heroku/cecky/src/test/resources";
        String imageForTestPath = "C:/javaprojects/Heroku/cecky/src/test/resources/imageForTest.jpg";
        String newImagePath = "C:/javaprojects/Heroku/cecky/src/test/resources/" + guid + fileName;

        BufferedImage image = ImageIO.read(new File(imageForTestPath));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        byte[] bytes = baos.toByteArray();

        MultipartFile file = new MockMultipartFile(guid + fileName, baos.toByteArray());

        Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);

        String response = directoryService.prepareAndSaveToDirectory(file, guid, fileName);

        Assert.assertEquals(uploadPath + "/UNCOMPRESSED" + guid + fileName, response);
    }

    @Test
    public void test18_compressImg() throws IOException {
        String imagePath = "C:/javaprojects/Heroku/cecky/src/test/resources/UNCOMPRESSED" + guid + fileName;

        directoryService.compressImg(imagePath);
    }

    @Test()
    public void test19_removeImageFromDirectory() {
        String imagePath = "C:/javaprojects/Heroku/cecky/src/test/resources/" + guid + fileName;

        directoryService.removeImageFromDirectory(imagePath);
    }

    @Test
    public void test20_getAllFilesFromDirectory() {
        String uploadPath = "C:/javaprojects/Heroku/cecky/src/test/resources/";

        Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);

        List<String> response = directoryService.getAllFilesFromDirectory(guid);

        Assert.assertEquals(0, response.size());
    }

}
