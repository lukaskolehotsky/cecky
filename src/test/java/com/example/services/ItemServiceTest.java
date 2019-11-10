package com.example.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.model.DBItem;
import com.example.payload.ItemResponse;
import com.example.repository.ItemRepository;
import com.example.requests.CreateItemRequest;
import com.example.requests.UpdateItemRequest;
import com.example.service.EmailSender;
import com.example.service.ItemService;

public class ItemServiceTest extends AbstractTest {

	@InjectMocks
	private ItemService itemService;

	@Mock
	private ItemRepository itemRepository;

	@Mock
	private EmailSender emailSender;

	@Test
	public void createItem() {
		CreateItemRequest createItemRequest = generateCreateItemRequest();
		DBItem item = generateItem(createItemRequest);
		ItemResponse expected = generateItemResponse(item);

		Mockito.when(itemRepository.save(any(DBItem.class))).thenReturn(item);
		doNothing().when(emailSender).sendEmail(item.getAuthenticationCode(), createItemRequest.getEmail(), createItemRequest.getBrand(), createItemRequest.getType());

		ItemResponse actual = itemService.createItem(createItemRequest);

		verifyItemResponse(expected, actual);
		
//		Mockito.verify(itemRepository, Mockito.times(1)).save(any(DBItem.class));
//		Mockito.verify(emailSender, Mockito.times(1)).sendEmail(item.getAuthenticationCode(), createItemRequest.getEmail(), createItemRequest.getBrand(), createItemRequest.getType());
	}

	@Test
	public void getItem() {
		String guid = "guid";
		DBItem item = generateItem();
		ItemResponse expected = generateItemResponse(item);

		Mockito.when(itemRepository.findByGuid(guid)).thenReturn(item);

		ItemResponse response = itemService.getItem(guid);

		verifyItemResponse(expected, response);
		
		Mockito.verify(itemRepository, Mockito.times(1)).findByGuid(guid);
	}

	@Test
	public void removeItem() {
		String guid = "guid";

		doNothing().when(itemRepository).deleteByGuid(guid);

		itemService.removeItem(guid);
		
		Mockito.verify(itemRepository, Mockito.times(1)).deleteByGuid(guid);
	}

	@Test
	public void getAll() {
		List<DBItem> items = new ArrayList<>();
		items.add(generateItem());
		Page<DBItem> pagedResponse = new PageImpl(items);

		Pageable sortedByCreateDateTimeAsc = PageRequest.of(0, 500, Sort.by("createdDateTime").descending());
		Mockito.when(itemRepository.findAll(sortedByCreateDateTimeAsc)).thenReturn(pagedResponse);

		List<ItemResponse> response = itemService.getAll(0);

		Assert.assertEquals(pagedResponse.getTotalElements(), response.size());
		
		Mockito.verify(itemRepository, Mockito.times(1)).findAll(sortedByCreateDateTimeAsc);
	}

	@Test
	public void updateItem() {
		String guid = "guid";
		DBItem item = generateItem();
		DBItem updatedItem = new DBItem("brand2", "type2", "guid2", LocalDateTime.now().plusDays(1), "email2",
				"authenticationCode", "2000", "description", "Diesel", "15000", 2015L, "421907397135");
		UpdateItemRequest updateItemRequest = generateUpdateItemRequest(updatedItem);
		ItemResponse updatedItemResponse = generateItemResponse(updatedItem);

		Mockito.when(itemRepository.findByGuid(guid)).thenReturn(item);
		Mockito.when(itemRepository.save(any())).thenReturn(updatedItem);

		ItemResponse response = itemService.updateItem(guid, updateItemRequest);

		verifyItemResponse(updatedItemResponse, response);
		
		Mockito.verify(itemRepository, Mockito.times(1)).findByGuid(guid);
		Mockito.verify(itemRepository, Mockito.times(1)).save(any());
	}

	@Test
	public void changeAuthenticationCode() {
		String guid = "guid";
		String email = "email";
		String authenticationCode = "ABCDEFGHIJ";
		DBItem item = generateItem();
		DBItem updatedItem = item;
		updatedItem.setAuthenticationCode(authenticationCode);

		Mockito.when(itemRepository.findByGuidAndEmail(guid, email)).thenReturn(item);
		Mockito.when(itemRepository.save(updatedItem)).thenReturn(updatedItem);
		doNothing().when(emailSender).sendEmail(authenticationCode, email, item.getBrand(), item.getType());

		String response = itemService.changeAuthenticationCode(guid, email);

		Assert.assertEquals(updatedItem.getAuthenticationCode(), response);
		
		Mockito.verify(itemRepository, Mockito.times(1)).findByGuidAndEmail(guid, email);
		Mockito.verify(itemRepository, Mockito.times(1)).save(updatedItem);
		Mockito.verify(emailSender, Mockito.times(1)).sendEmail(updatedItem.getAuthenticationCode(), email, item.getBrand(), item.getType());
	}

	private void verifyItemResponse(ItemResponse expected, ItemResponse actual) {
		Assert.assertEquals(expected.getBrand(), actual.getBrand());
		Assert.assertEquals(expected.getType(), actual.getType());
		Assert.assertEquals(expected.getGuid(), actual.getGuid());
		Assert.assertEquals(expected.getCreatedDateTime(), actual.getCreatedDateTime());
		Assert.assertEquals(expected.getEmail(), actual.getEmail());
		Assert.assertEquals(expected.getAuthenticationCode(), actual.getAuthenticationCode());
		Assert.assertEquals(expected.getPrice(), actual.getPrice());
		Assert.assertEquals(expected.getDescription(), actual.getDescription());
		Assert.assertEquals(expected.getFuelType(), actual.getFuelType());
		Assert.assertEquals(expected.getSpeedometerCondition(), actual.getSpeedometerCondition());
		Assert.assertEquals(expected.getProductionYear(), actual.getProductionYear());
		Assert.assertEquals(expected.getMobileNumber(), actual.getMobileNumber());
	}

}
