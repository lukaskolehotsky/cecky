package com.example.services;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import com.example.exception.MyFileNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.example.model.DBFile;
import com.example.payload.FileResponse;
import com.example.repository.FileRepository;
import com.example.service.FileService;
import com.example.service.ItemService;

public class FileServiceTest extends AbstractTest {

	@InjectMocks
	private FileService fileService;

	@Mock
	private FileRepository fileRepository;

	@Mock
	private ItemService itemService;

	@Mock
	private ServletContext context;

	@Before
	public void setUp() {
		// itemService.evictCache();
	}

	@After
	public void tearDown() {
		// Clean up after each test method.
	}

	@Test
	public void findById() throws UnsupportedEncodingException {
		String guid = "guid";

		DBFile file = generateFile();

		Mockito.when(fileRepository.findById(guid)).thenReturn(Optional.of(file));

		FileResponse response = fileService.findById(guid);

		Assert.assertEquals(response.getFileName(), file.getFileName());
		Assert.assertEquals(response.getFileType(), file.getFileType());
	}

	@Test(expected = IllegalArgumentException.class)
	public void findById_fileNotFound() throws UnsupportedEncodingException {
		String guid = "guid";

		Mockito.when(fileRepository.findById(guid)).thenReturn(Optional.empty());

		fileService.findById(guid);
	}

	@Test
	public void getFile() {
		String fileId = "fileId";
		DBFile file = generateFile();

		Mockito.when(fileRepository.findById(fileId)).thenReturn(Optional.of(file));

		DBFile response = fileService.getFile(fileId);

		Assert.assertEquals(file.getFileName(), response.getFileName());
		Assert.assertEquals(file.getFileType(), response.getFileType());
		Assert.assertEquals(file.getGuid(), response.getGuid());
	}

	@Test(expected = MyFileNotFoundException.class)
	public void getFile_fileNotFound() {
		String fileId = "fileId";

		Mockito.when(fileRepository.findById(fileId)).thenReturn(Optional.empty());

		fileService.getFile(fileId);
	}

	@Test
	public void findAll() throws UnsupportedEncodingException {
		List<DBFile> files = generateFiles();
		List<FileResponse> uploadFileResponses = generateUploadFileResponses(files);

		Mockito.when(fileRepository.findAll()).thenReturn(files);

		List<FileResponse> response = fileService.findAll();

		Assert.assertEquals(uploadFileResponses.size(), response.size());
		Assert.assertEquals(uploadFileResponses.get(0).getFileName(), response.get(0).getFileName());
		Assert.assertEquals(uploadFileResponses.get(0).getFileType(), response.get(0).getFileType());
		Assert.assertEquals(uploadFileResponses.get(1).getFileName(), response.get(1).getFileName());
		Assert.assertEquals(uploadFileResponses.get(1).getFileType(), response.get(1).getFileType());
	}

//	@Test
//	public void saveImageToDirectory() throws IOException {
//		String path = "C:/javaprojects/Heroku/cecky/src/main/webapp/";
//		
//		Mockito.when(context.getRealPath("/")).thenReturn(path);
//		
//		MultipartFile file = new MockMultipartFile("fileName.jpg", new byte[1]);
//		
//		String response = fileService.saveImageToDirectory(file,"guid","fileName.jpg");
//		
//		Assert.assertEquals(path + "guid" + "fileName" + "COMPRESSED",response);
//		
//	}

//	@Test
//	public void getFiles() throws UnsupportedEncodingException {
//		String guid = "guid";
//		List<DBFile> files = generateFiles();
//		files.add(generateFile("guid2"));
//		List<FileResponse> fileResponses = generateFileResponsesFromFiles(files);
//		
//		Mockito.when(fileRepository.findByGuid(guid)).thenReturn(files);
//		
//		List<FileResponse> response = fileService.getFiles(guid);
//		
//		Assert.assertEquals(fileResponses.size(), 2);	
//		Assert.assertEquals(response.size(), 1);
//		
//		Assert.assertEquals(fileResponses.get(0).getFileName(), response.get(0).getFileName());
//		Assert.assertEquals(fileResponses.get(0).getFileType(), response.get(0).getFileType());
//		Assert.assertEquals(fileResponses.get(0).getImgPath(), response.get(0).getImgPath());
//		
//	}

}
