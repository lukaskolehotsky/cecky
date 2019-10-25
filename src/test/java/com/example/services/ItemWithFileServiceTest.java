package com.example.services;

import com.example.config.ServerProperties;
import com.example.model.DBItem;
import com.example.payload.FileResponse;
import com.example.payload.ItemResponse;
import com.example.payload.ItemWithFileResponse;
import com.example.payload.ItemWithFilesResponse;
import com.example.service.DirectoryService;
import com.example.service.FileService;
import com.example.service.ItemService;
import com.example.service.ItemWithFileService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class ItemWithFileServiceTest extends AbstractTest {

	@InjectMocks
	private ItemWithFileService itemWithFileService;

	@Mock
	private ItemService itemService;

	@Mock
	private FileService fileService;

	@Mock
	private DirectoryService directoryService;

	@Mock
	private ServerProperties serverProperties;

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	@Test
	public void getItemWithFileResponses() {
		int pageNumber = 0;
		DBItem item = generateItem();
		ItemResponse itemResponse = generateItemResponse(item);
		List<ItemResponse> itemResponses = new ArrayList<>();
		itemResponses.add(itemResponse);
		List<String> imgPaths = new ArrayList<>();
		imgPaths.add("imgPath");
		FileResponse fileResponse = generateFileResponse();
		List<FileResponse> fileResponses = new ArrayList<>();
		fileResponses.add(fileResponse);

		Mockito.when(itemService.getAll(pageNumber)).thenReturn(itemResponses);
		Mockito.when(directoryService.getAllFilesFromDirectory(itemResponse.getGuid())).thenReturn(imgPaths);
		Mockito.when(fileService.getFiles(itemResponse.getGuid())).thenReturn(fileResponses);
		Mockito.when(serverProperties.getServerPath()).thenReturn("serverPath");
		Mockito.when(serverProperties.getRemovePath()).thenReturn("removePath");

		List<ItemWithFileResponse> response = itemWithFileService.getItemWithFileResponses(pageNumber);

		Assert.assertEquals("guid", response.get(0).getItemResponse().getGuid());
		Assert.assertEquals("serverPathimgPath", response.get(0).getFileResponse().getImgPath());
	}

	@Test
	public void getItemWithFiles() throws UnsupportedEncodingException {
		String guid = "guid";
		DBItem item = generateItem();
		ItemResponse itemResponse = generateItemResponse(item);

		FileResponse fileResponse = generateFileResponse();
		List<FileResponse> fileResponses = new ArrayList<>();
		fileResponses.add(fileResponse);

		ItemWithFilesResponse itemWithFilesResponse = new ItemWithFilesResponse(itemResponse, fileResponses);

		Mockito.when(itemService.getItem(guid)).thenReturn(itemResponse);
		Mockito.when(fileService.getFiles(guid)).thenReturn(fileResponses);
		Mockito.when(serverProperties.getServerPath()).thenReturn("serverPath");
		Mockito.when(serverProperties.getRemovePath()).thenReturn("removePath");

		ItemWithFilesResponse response = itemWithFileService.getItemWithFiles(guid);

		Assert.assertEquals(itemWithFilesResponse.getFileResponses().size(), response.getFileResponses().size());
		Assert.assertEquals(itemWithFilesResponse.getFileResponses().get(0).getFileName(), response.getFileResponses().get(0).getFileName());
		Assert.assertEquals(itemWithFilesResponse.getFileResponses().get(0).getFileType(), response.getFileResponses().get(0).getFileType());
		Assert.assertEquals(itemWithFilesResponse.getFileResponses().get(0).getFileDownloadUri(), response.getFileResponses().get(0).getFileDownloadUri());
		Assert.assertEquals("serverPathimgPath", response.getFileResponses().get(0).getImgPath());

		Assert.assertEquals(itemWithFilesResponse.getItemResponse().getBrand(), response.getItemResponse().getBrand());
		Assert.assertEquals(itemWithFilesResponse.getItemResponse().getType(), response.getItemResponse().getType());
		Assert.assertEquals(itemWithFilesResponse.getItemResponse().getEmail(), response.getItemResponse().getEmail());
		Assert.assertEquals(itemWithFilesResponse.getItemResponse().getCreatedDateTime(), response.getItemResponse().getCreatedDateTime());
		Assert.assertEquals(itemWithFilesResponse.getItemResponse().getAuthenticationCode(), response.getItemResponse().getAuthenticationCode());
		Assert.assertEquals(itemWithFilesResponse.getItemResponse().getGuid(), response.getItemResponse().getGuid());
	}

	@Test
	public void removeItemWithFiles() {
		String guid = "guid";
		String imgPath = "imgPath";
		String authCode = "authenticationCode";
		DBItem item = generateItem();
		ItemResponse itemResponse = generateItemResponse(item);
		List<String> imgPaths = new ArrayList<>();
		imgPaths.add(imgPath);

		doNothing().when(itemService).removeItem(guid);
		doNothing().when(fileService).removeFile(guid);
		Mockito.when(directoryService.getAllFilesFromDirectory(guid)).thenReturn(imgPaths);
		doNothing().when(directoryService).removeImageFromDirectory(imgPath);
		Mockito.when(itemService.getItem(guid)).thenReturn(itemResponse);

		itemWithFileService.removeItemWithFiles(guid, authCode);

		verify(itemService).removeItem(guid);
		verify(fileService).removeFile(guid);
		verify(directoryService).getAllFilesFromDirectory(guid);
		verify(directoryService).removeImageFromDirectory(imgPath);
	}

}
