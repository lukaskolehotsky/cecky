package com.example.service;

import com.example.Utils.Utils;
import com.example.model.DBItem;
import com.example.payload.ItemResponse;
import com.example.repository.ItemRepository;
import com.example.requests.CreateItemRequest;
import com.example.requests.UpdateItemRequest;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemService extends Utils {

    @Autowired
    private ItemRepository itemRepository;

    public ItemResponse createItem(CreateItemRequest request) {

        DBItem savedItem = itemRepository.save(generateDBItem(request));

        return generateItemResponse(savedItem);
    }
    
    public ItemResponse getItem(String guid) {
    	
    	DBItem item = itemRepository.findByGuid(guid);
    	
    	return generateItemResponse(item);
    }   
    
    @Transactional
    public void removeItem(String guid) {
    	
    	itemRepository.deleteByGuid(guid);
    } 
    
    public List<ItemResponse> getAll(){
    	
    	List<DBItem> items = itemRepository.findAll();
    	List<ItemResponse> itemResponses = new ArrayList<>();
    	
    	for(DBItem item: items) {
    		itemResponses.add(generateItemResponse(item));
    	}
    	return itemResponses;
    }    
    
    public ItemResponse updateItem(String guid, UpdateItemRequest request) {    	
    	DBItem item = itemRepository.findByGuid(guid);
    	DBItem updatedItem = itemRepository.save(prepareModifiedItem(item, request));
    	
    	return generateItemResponse(updatedItem);
    }
    
}
