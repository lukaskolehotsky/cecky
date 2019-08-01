package com.example.filedemo.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import com.example.filedemo.exception.FileStorageException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.example.filedemo.model.DBFile;
import com.example.filedemo.payload.UploadFileResponse;
import com.example.filedemo.repository.DBFileRepository;
import com.example.filedemo.service.FileService;
import com.example.filedemo.service.ItemService;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;

public class FileServiceTest extends AbstractTest{

	@InjectMocks
	private FileService fileService;
	
	@Mock
	private DBFileRepository fileRepository;
	
	@Mock
	private ItemService itemService;
	
	@Before
	public void setUp() {
		//itemService.evictCache();
	}
	
	@After
	public void tearDown() {
		// Clean up after each test method.
	}
	
	@Test
	public void findById() {		
		String guid = "guid";
		
		DBFile file = generateFile();
		
		Mockito.when(fileRepository.findById(guid)).thenReturn(Optional.of(file));
		
		UploadFileResponse response = fileService.findById(guid);
		
		Assert.assertEquals(response.getFileName(), file.getFileName());
		Assert.assertEquals(response.getFileType(), file.getFileType());		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void findById_fileNotFound() {		
		String guid = "guid";
		
		Mockito.when(fileRepository.findById(guid)).thenReturn(Optional.empty());
		
		fileService.findById(guid);
	}

//	@Test
//	public void storeFile() throws IOException {
//		String guid = "guid";
//		MockMultipartFile multipartFile = generateMockMultipartFile();
//		DBFile file = generateDBFile(multipartFile, guid);
//
//		Mockito.when(fileRepository.save(file)).thenReturn(file);
//
//		DBFile response = fileService.storeFile(multipartFile, guid);
//
//		Assert.assertEquals(response.getFileName(), file.getFileName());
//		Assert.assertEquals(response.getFileType(), file.getFileType());
//
//	}

	@Test(expected = FileStorageException.class)
	public void storeFile_FileStorageException() throws IOException {
		String guid = "guid";
		MockMultipartFile multipartFile = new MockMultipartFile("file", "filename..txt", "text/plain", "hello".getBytes(StandardCharsets.UTF_8));

		fileService.storeFile(multipartFile, guid);
	}

	private MockMultipartFile generateMockMultipartFile() {
		return new MockMultipartFile("file", "filename.txt", "text/plain", "hello".getBytes(StandardCharsets.UTF_8));
	}

	private DBFile generateDBFile(MockMultipartFile multipartFile, String guid) throws IOException {
		return new DBFile(StringUtils.cleanPath(multipartFile.getOriginalFilename()), multipartFile.getContentType(), multipartFile.getBytes(), guid);
	}
	
}
