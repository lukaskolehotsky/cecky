package com.example.services;

import com.example.service.FileService;
import com.example.service.ItemService;
import com.example.service.ItemWithFileService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@TestPropertySource(properties = { 
		"remove.path=C:/javaprojects/Heroku/cecky/src/main/webapp/",
		"server.path=/"})
public class ItemWithFileServiceTest extends AbstractTest {

	@InjectMocks
	private ItemWithFileService itemWithFileService;

	@Mock
	private ItemService itemService;

	@Mock
	private FileService fileService;

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}
	
	@Value("${remove.path}")
	String removePath;
	
	@Value("${server.path}")
	String serverPath;

	@Test
	public void testRemovePath() {
		Assert.assertEquals("C:/javaprojects/Heroku/cecky/src/main/webapp/", removePath);
	}
	
	@Test
	public void testServerPath() {
		Assert.assertEquals("/", serverPath);
	}

//	@Test
//	public void removeItemWithFiles() {
//		String guid = "guid";
//
//		doNothing().when(itemService).removeItem(guid);
//		doNothing().when(fileService).removeFile(guid);
//
//		itemWithFileService.removeItemWithFiles(guid);
//
//		verify(itemService).removeItem(guid);
//		verify(fileService).removeFile(guid);
//	}

}
