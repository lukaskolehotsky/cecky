package com.example.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.example.config.ServerProperties;
import com.example.service.DirectoryService;

import javax.imageio.ImageIO;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

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
	public void test1_saveFileToDirectory() throws IOException {
		MultipartFile file = new MockMultipartFile(fileName, new byte[1]);
		String imagePath = "C:/javaprojects/Heroku/cecky/src/test/resources/" + guid + fileName;
		
		directoryService.saveFileToDirecory(file,imagePath);
	}

	@Test
    public void test2_getAllFilesFromDirectory() {
        String uploadPath = "C:/javaprojects/Heroku/cecky/src/test/resources/";

	    Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);

        List<String> response = directoryService.getAllFilesFromDirectory(guid);

        Assert.assertEquals(1, response.size());

    }
	
	@Test()
    public void test3_getAllFilesFromDirectory_noSuchFileException() {
        String uploadPath = "C:/javaprojects/Heroku/cecky/src/test/resourcesX";

	    Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);

	    List<String> response = directoryService.getAllFilesFromDirectory(guid);

        Assert.assertEquals(null, response);
    }

	@Test()
	public void test4_removeImageFromDirectory() {
		String imagePath = "C:/javaprojects/Heroku/cecky/src/test/resources/" + guid + fileName;

		directoryService.removeImageFromDirectory(imagePath);
	}

	@Test
	public void test5_getAllFilesFromDirectory_emptyList() {
		String uploadPath = "C:/javaprojects/Heroku/cecky/src/test/resources/";

		Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);

		List<String> response = directoryService.getAllFilesFromDirectory(guid);

		Assert.assertEquals(0, response.size());

	}

	@Test
	public void test6_prepareAndSaveToDirectory() throws IOException {
		String uploadPath = "C:/javaprojects/Heroku/cecky/src/test/resources";
		MultipartFile file = new MockMultipartFile(fileName, new byte[1]);

		Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);

		String response = directoryService.prepareAndSaveToDirectory(file, guid, fileName);

		Assert.assertEquals(uploadPath+"/"+guid+fileName, response);
	}



//	@Test
//	public void compressImg() throws IOException {
//		String imagePath = "C:/javaprojects/Heroku/cecky/src/test/resources/" + guid + fileName;
//
//		directoryService.compressImg(imagePath);
//	}

	@Test()
	public void test7_removeImageFromDirectory() {
		String imagePath = "C:/javaprojects/Heroku/cecky/src/test/resources/" + guid + fileName;

		directoryService.removeImageFromDirectory(imagePath);
	}

	@Test
	public void test8_getAllFilesFromDirectory_emptyList() {
		String uploadPath = "C:/javaprojects/Heroku/cecky/src/test/resources/";

		Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);

		List<String> response = directoryService.getAllFilesFromDirectory(guid);

		Assert.assertEquals(0, response.size());

	}
	
//	File testJpg;
	
//	@Before
//	public void setup() throws IOException {
//	    testJpg = new ClassPathResource("blabla.jpeg").getFile();
//	}
	

//	@Test
//	public void test_image_io() throws IOException {
//	    BufferedImage bufferedImage = ImageIO.read(testJpg);
//	}
	
//	@Test
//	public void saveImageToDirectory() throws IOException {
//		String guid = "guid";
//		String fileName = "fileName";
//		MultipartFile file = new MockMultipartFile("fileName", new byte[1]);		
//		String uploadPath = "C:/javaprojects/Heroku/cecky/src/main/webapp/images";
//		
//		Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);
//		
//		String response = directoryService.prepareAndSaveToDirectory(file, guid, fileName);
//		
//		Assert.assertEquals(uploadPath+"/"+guid+fileName, response);
//	}
	
//	@Test
//	public void compressImg() throws IOException {
//		String guid = "guid";
//		String fileName = "fileName.jpg";
////		String path = "C:/javaprojects/Heroku/cecky/src/main/webapp/images/" +guid+fileName;
//		String path = "C:/javaprojects/Heroku/cecky/src/test/resources/blabla.jpeg";
//		directoryService.compressImg(path);
//	}

}
