package com.example.services;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.example.config.ServerProperties;
import com.example.service.DirectoryService;

//public class DirectoryServiceTest extends AbstractTest {
	
//	@InjectMocks
//	private DirectoryService directoryService;
//	
//	@Mock
//	private ServerProperties serverProperties;
//
//	@Before
//	public void setUp() {
//	
//	}
//
//	@After
//	public void tearDown() {
//
//	}
	
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

//}
