package com.example.service;

import com.example.Utils.Utils;
import com.example.model.DBItem;
import com.example.payload.ItemResponse;
import com.example.repository.ItemRepository;
import com.example.requests.CreateItemRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemService extends Utils {

    @Autowired
    private ItemRepository itemRepository;

    public ItemResponse createItem(CreateItemRequest request) {

        DBItem savedItem = itemRepository.save(generateDBItem(request));

        return new ItemResponse(savedItem.getBrand(), savedItem.getType(), savedItem.getGuid(), savedItem.getCreatedDateTime());
    }
    
    public ItemResponse getItem(String guid) {
    	DBItem item = itemRepository.findByGuid(guid);
    	return new ItemResponse(item.getBrand(), item.getType(), item.getGuid(), item.getCreatedDateTime());
    }    

}
