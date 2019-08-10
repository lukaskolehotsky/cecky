package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.requests.CreateItemRequest;
import com.example.service.EmailSender;
import com.example.service.ItemService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.Assert;

import com.example.model.DBItem;
import com.example.payload.ItemResponse;
import com.example.repository.ItemRepository;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

public class ItemServiceTest extends AbstractTest{

	@InjectMocks
	private ItemService itemService;
	
	@Mock
	private ItemRepository itemRepository;
	
	@Mock
	private EmailSender emailSender;
	
	@Before
	public void setUp() {
		//itemService.evictCache();
	}
	
	@After
	public void tearDown() {
		// Clean up after each test method.
	}
	
	@Test
	public void createItem() {
		CreateItemRequest createItemRequest = generateCreateItemRequest();
		DBItem item = generateDBItem(createItemRequest);
		ItemResponse itemResponse = generateItemResponse(item);
		
		Mockito.when(itemRepository.save(any(DBItem.class))).thenReturn(item);
		doNothing().when(emailSender).sendEmail("ABCDE", createItemRequest.getEmail());
		
		ItemResponse response = itemService.createItem(createItemRequest);
		
		Assert.assertEquals(itemResponse.getBrand(), response.getBrand());
		Assert.assertEquals(itemResponse.getGuid(), response.getGuid());
		Assert.assertEquals(itemResponse.getType(), response.getType());
	}
	
	@Test
	public void getItem() {
		String guid = "guid";
		
		DBItem item = generateItem();
		
		Mockito.when(itemRepository.findByGuid(guid)).thenReturn(item);
		
		ItemResponse response = itemService.getItem(guid);
		
		Assert.assertEquals(response.getBrand(), item.getBrand());
		Assert.assertEquals(response.getType(), item.getType());
		Assert.assertEquals(response.getGuid(), item.getGuid());
	}
	
//	@Test
//	public void getAll() {		
//		List<DBItem> items = generateDBItems();
//		Mockito.when(itemRepository.findAll()).thenReturn(items);
//		
//		List<ItemResponse> response = itemService.getAll(1);
//		
//		Assert.assertEquals(items.size(), response.size());		
//	}

}
