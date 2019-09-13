package com.example.services;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

import com.example.repository.FileRepository;
import com.example.service.FileService;
import com.example.service.ItemService;

@TestPropertySource(properties = { "upload.path=C:/javaprojects/Heroku/cecky/src/main/webapp/images", })
public class FileServiceTest extends AbstractTest {

	@InjectMocks
	private FileService fileService;

	@Mock
	private FileRepository fileRepository;

	@Mock
	private ItemService itemService;

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {

	}

	@Value("${upload.path}")
	String uploadPath;

	@Test
	public void testUploadPath() {
		Assert.assertEquals("C:/javaprojects/Heroku/cecky/src/main/webapp/images", uploadPath);
	}

}
