package com.example.services;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.DirectoryService;

@TestPropertySource(properties = { "upload.path=C:/javaprojects/Heroku/cecky/src/main/webapp/images" })
public class DirectoryServiceTest extends AbstractTest {

	@Value("${upload.path}")
	String uploadPath;
	
	@InjectMocks
	private DirectoryService directoryService;

	@Before
	public void setUp() {
	
	}

	@After
	public void tearDown() {

	}
	
	@Test
	public void testUploadPath() {
		Assert.assertEquals("C:/javaprojects/Heroku/cecky/src/main/webapp/images", uploadPath);
	}
	
//	@Test
//	public void saveImageToDirectory() throws IOException {
//		String guid = "guid";
//		String fileName = "fileName";
//		MultipartFile file = new MockMultipartFile("fileName", new byte[1]);
//		
//		directoryService.saveImageToDirectory(file, guid, fileName);
//	}

}
