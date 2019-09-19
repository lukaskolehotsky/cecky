package com.example.services;

import com.example.model.DBItem;
import com.example.payload.ItemResponse;
import com.example.repository.ItemRepository;
import com.example.requests.CreateItemRequest;
import com.example.requests.UpdateItemRequest;
import com.example.service.EmailSender;
import com.example.service.ItemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

public class ItemServiceTest extends AbstractTest {

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

    @Test
    public void removeItem() {
        String guid = "guid";

        doNothing().when(itemRepository).deleteByGuid(guid);

        itemService.removeItem(guid);
    }

    @Test
    public void getAll() {
        List<DBItem> items = new ArrayList<>();
        items.add(generateItem());
        Page<DBItem> pagedResponse = new PageImpl(items);

        Pageable sortedByCreateDateTimeAsc =
                PageRequest.of(0, 500, Sort.by("createdDateTime").descending());
        Mockito.when(itemRepository.findAll(sortedByCreateDateTimeAsc)).thenReturn(pagedResponse);

        List<ItemResponse> response = itemService.getAll(0);

        Assert.assertEquals(pagedResponse.getTotalElements(), response.size());
    }

    @Test
    public void updateItem() {
        String guid = "guid";
        DBItem item = generateItem();
        DBItem updatedItem = new DBItem("brand2", "type2", "guid2", LocalDateTime.now().plusDays(1), "email2", "authenticationCode");
        UpdateItemRequest updateItemRequest = generateUpdateItemRequest(updatedItem);
        ItemResponse updatedItemResponse = generateItemResponse(updatedItem);
        ItemResponse itemResponse = generateItemResponse(item);

        Mockito.when(itemRepository.findByGuid(guid)).thenReturn(item);
        Mockito.when(itemRepository.save(any())).thenReturn(updatedItem);

        ItemResponse response = itemService.updateItem(guid, updateItemRequest);

        Assert.assertEquals(updatedItemResponse.getBrand(), response.getBrand());
        Assert.assertNotEquals(itemResponse.getBrand(), response.getBrand());

        Assert.assertEquals(updatedItemResponse.getType(), response.getType());
        Assert.assertNotEquals(itemResponse.getType(), response.getType());

        Assert.assertEquals(updatedItemResponse.getGuid(), response.getGuid());
        Assert.assertNotEquals(itemResponse.getGuid(), response.getGuid());

        Assert.assertEquals(updatedItemResponse.getEmail(), response.getEmail());
        Assert.assertNotEquals(itemResponse.getEmail(), response.getEmail());

        Assert.assertEquals(updatedItemResponse.getAuthenticationCode().get(), response.getAuthenticationCode().get());

        Assert.assertNotEquals(itemResponse.getCreatedDateTime(), response.getCreatedDateTime());
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
        doNothing().when(emailSender).sendEmail(authenticationCode, email);

        String response = itemService.changeAuthenticationCode(guid, email);

        Assert.assertEquals(updatedItem.getAuthenticationCode(), response);
    }

}
