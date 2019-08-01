package com.example.service;

import com.example.Utils.Utils;
import com.example.model.DBItem;
import com.example.payload.ItemResponse;
import com.example.repository.ItemRepository;
import com.example.requests.CreateItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService extends Utils {

    @Autowired
    private ItemRepository itemRepository;

    public ItemResponse createItem(CreateItemRequest request) {

        DBItem savedItem = itemRepository.save(generateDBItem(request));

        return new ItemResponse(savedItem.getBrand(), savedItem.getType(), savedItem.getGuid());
    }

    public ItemResponse getItem(String guid) {
        Optional<DBItem> itemOpt = itemRepository.findById(guid);
        if (itemOpt.isPresent()) {
            return new ItemResponse(itemOpt.get().getBrand(), itemOpt.get().getType(), itemOpt.get().getGuid());
        } else {
            throw new IllegalArgumentException("Item not found.");
        }
    }
}
