package com.example.services;

import java.util.Optional;

import com.example.requests.CreateItemRequest;
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

public class ItemServiceTest extends AbstractTest{

	@InjectMocks
	private ItemService itemService;
	
	@Mock
	private ItemRepository itemRepository;
	
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
		CreateItemRequest createItemRequest = new CreateItemRequest("brand", "type");
		DBItem item = generateDBItem(createItemRequest);
		ItemResponse itemResponse = generateItemResponse(item);
		
		Mockito.when(itemRepository.save(item)).thenReturn(item);
		
		ItemResponse response = itemService.createItem(createItemRequest);
		
		Assert.assertEquals(itemResponse.getBrand(), response.getBrand());
		Assert.assertEquals(itemResponse.getGuid(), response.getGuid());
		Assert.assertEquals(itemResponse.getType(), response.getType());
	}
	
	@Test
	public void getItem() {
		String guid = "guid";
		
		DBItem item = generateItem();
		
		Mockito.when(itemRepository.findById(guid)).thenReturn(Optional.of(item));
		
		ItemResponse response = itemService.getItem(guid);
		
		Assert.assertEquals(response.getBrand(), item.getBrand());
		Assert.assertEquals(response.getType(), item.getType());
		Assert.assertEquals(response.getGuid(), item.getGuid());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getItem_itemNotFound() {
		String guid = "guid";
		
		Mockito.when(itemRepository.findById("blabla")).thenReturn(Optional.empty());
		
		itemService.getItem(guid);
	}
}
