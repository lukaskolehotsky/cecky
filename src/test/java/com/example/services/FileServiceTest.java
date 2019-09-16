package com.example.services;

import com.example.config.ServerProperties;
import com.example.model.DBFile;
import com.example.payload.FileResponse;
import com.example.service.DirectoryService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;

import com.example.repository.FileRepository;
import com.example.service.FileService;
import com.example.service.ItemService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestPropertySource(properties = { "upload.path=C:/javaprojects/Heroku/cecky/src/main/webapp/images", })
public class FileServiceTest extends AbstractTest {

	@InjectMocks
	private FileService fileService;

	@Mock
	private FileRepository fileRepository;

	@Mock
	private DirectoryService directoryService;

	@Mock
	private ServerProperties serverProperties;

	@Mock
	private ItemService itemService;

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {

	}

	@Test
	public void removeFile() {
		String guid = "guid";

		doNothing().when(fileRepository).deleteByGuid(guid);

		fileService.removeFile(guid);

		verify(fileRepository).deleteByGuid(guid);
	}

	@Test
	public void getFiles() throws UnsupportedEncodingException {
		String guid = "guid";
		List<DBFile> files = generateFiles();
		List<String> imagePaths = new ArrayList<>();
		imagePaths.add("imgPath");

		Mockito.when(fileRepository.findByGuid(guid)).thenReturn(files);
		Mockito.when(directoryService.getAllFilesFromDirectory(guid)).thenReturn(imagePaths);

		List<FileResponse> response = fileService.getFiles(guid);

		Assert.assertEquals(files.size(), response.size());
	}

	@Test
	public void storeFile() throws IOException {
		String guid = "guid";
		String fileName = "fileName";
		MultipartFile multipartFile = new MockMultipartFile("name", fileName, "contentType", new byte[1]);
		String imagePath = "imagePath";
		String uploadPath = "uploadPath";
		DBFile dbFile = generateDBFile(imagePath, fileName, multipartFile.getContentType(), guid);

		Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);
		Mockito.when(directoryService.prepareAndSaveToDirectory(multipartFile, guid, multipartFile.getOriginalFilename())).thenReturn(imagePath);
		doNothing().when(directoryService).compressImg(imagePath);
		doNothing().when(directoryService).removeImageFromDirectory(imagePath);
		Mockito.when(fileRepository.save(any())).thenReturn(dbFile);

		DBFile response = fileService.storeFile(multipartFile, guid);

		Assert.assertEquals(multipartFile.getOriginalFilename(), response.getFileName());

		verify(directoryService, times(1)).prepareAndSaveToDirectory(multipartFile, guid, multipartFile.getOriginalFilename());
		verify(directoryService, times(1)).compressImg(imagePath);
		verify(directoryService, times(1)).removeImageFromDirectory(imagePath);
		verify(fileRepository, times(1)).save(any());
	}

	@Test
	public void saveImage() throws IOException {
		String guid = "guid";
		String fileName = "fileName";
		MultipartFile multipartFile = new MockMultipartFile("name", fileName, "contentType", new byte[1]);
		String imagePath = "imagePath";
		String uploadPath = "uploadPath";
		DBFile dbFile = generateDBFile(imagePath, fileName, multipartFile.getContentType(), guid);

		Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);
		Mockito.when(directoryService.prepareAndSaveToDirectory(multipartFile, guid, multipartFile.getOriginalFilename())).thenReturn(imagePath);
		doNothing().when(directoryService).compressImg(imagePath);
		doNothing().when(directoryService).removeImageFromDirectory(imagePath);
		Mockito.when(fileRepository.save(any())).thenReturn(dbFile);

		FileResponse response = fileService.saveImage(multipartFile, guid);

		Assert.assertEquals(multipartFile.getOriginalFilename(), response.getFileName());

		verify(directoryService, times(1)).prepareAndSaveToDirectory(multipartFile, guid, multipartFile.getOriginalFilename());
		verify(directoryService, times(1)).compressImg(imagePath);
		verify(directoryService, times(1)).removeImageFromDirectory(imagePath);
		verify(fileRepository, times(1)).save(any());
	}

	@Test
	public void saveImages() throws IOException {
		String guid = "guid";
		String fileName = "fileName";
		MultipartFile multipartFile = new MockMultipartFile("name", fileName, "contentType", new byte[1]);
		List<MultipartFile> multipartFiles = new ArrayList<>();
		multipartFiles.add(multipartFile);
		String imagePath = "imagePath";
		String uploadPath = "uploadPath";
		DBFile dbFile = generateDBFile(imagePath, fileName, multipartFile.getContentType(), guid);

		Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);
		Mockito.when(directoryService.prepareAndSaveToDirectory(multipartFile, guid, multipartFile.getOriginalFilename())).thenReturn(imagePath);
		doNothing().when(directoryService).compressImg(imagePath);
		doNothing().when(directoryService).removeImageFromDirectory(imagePath);
		Mockito.when(fileRepository.save(any())).thenReturn(dbFile);

		List<FileResponse> response = fileService.saveImages(multipartFiles, guid);

		Assert.assertEquals(multipartFiles.size(), response.size());

		verify(directoryService, times(1)).prepareAndSaveToDirectory(multipartFile, guid, multipartFile.getOriginalFilename());
		verify(directoryService, times(1)).compressImg(imagePath);
		verify(directoryService, times(1)).removeImageFromDirectory(imagePath);
		verify(fileRepository, times(1)).save(any());
	}

	@Test
	public void updateFiles() throws IOException {
		String guid = "guid";
		String fileName = "fileName";
		MultipartFile multipartFile = new MockMultipartFile("name", fileName, "contentType", new byte[1]);
		List<MultipartFile> multipartFiles = new ArrayList<>();
		multipartFiles.add(multipartFile);
		String imagePath = "imagePath";
		List<String> imagePaths = new ArrayList<>();
		imagePaths.add(imagePath);

		String uploadPath = "uploadPath";
		DBFile dbFile = generateDBFile(imagePath, fileName, multipartFile.getContentType(), guid);

		doNothing().when(fileRepository).deleteByGuid(guid);
		Mockito.when(directoryService.getAllFilesFromDirectory(guid)).thenReturn(imagePaths);
		doNothing().when(directoryService).removeImageFromDirectory(imagePath);

		Mockito.when(serverProperties.getUploadPath()).thenReturn(uploadPath);
		Mockito.when(directoryService.prepareAndSaveToDirectory(multipartFile, guid, multipartFile.getOriginalFilename())).thenReturn(imagePath);
		doNothing().when(directoryService).compressImg(imagePath);
		doNothing().when(directoryService).removeImageFromDirectory(imagePath);
		Mockito.when(fileRepository.save(any())).thenReturn(dbFile);

		List<FileResponse> response = fileService.updateFiles(guid, multipartFiles);

		Assert.assertEquals(multipartFiles.size(), response.size());
		Assert.assertEquals(multipartFiles.get(0).getOriginalFilename(), response.get(0).getFileName());
	}

}
