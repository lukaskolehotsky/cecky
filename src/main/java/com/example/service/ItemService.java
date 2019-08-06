package com.example.service;

import com.example.Utils.Utils;
import com.example.model.DBItem;
import com.example.payload.ItemResponse;
import com.example.repository.ItemRepository;
import com.example.requests.CreateItemRequest;
import com.example.requests.UpdateItemRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ItemService extends Utils {

    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private EmailSender emailSender;

    public ItemResponse createItem(CreateItemRequest request) {

        String authenticationCode = generateAuthenticationCode(10);
        request.setAuthenticationCode(Optional.of(authenticationCode));

        DBItem savedItem = itemRepository.save(generateDBItem(request));
        emailSender.sendEmail(authenticationCode, request.getEmail());

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
    
    public List<ItemResponse> getAll(int pageNumber){
    	Pageable paging = PageRequest.of(pageNumber, 5, Sort.by("createdDateTime").ascending());
    	
    	Page<DBItem> items = itemRepository.findAll(paging);
    	List<ItemResponse> itemResponses = new ArrayList<>();
    	
    	for(DBItem item: items.getContent()) {
    		itemResponses.add(generateItemResponse(item));
    	}
    	return itemResponses;
    }    
        
    public ItemResponse updateItem(String guid, UpdateItemRequest request) {   
    	
    	DBItem item = itemRepository.findByGuid(guid);

    	if(request.getAuthenticationCode().isPresent()){
    	    if(item.getAuthenticationCode().equals(request.getAuthenticationCode().get())) {
                DBItem updatedItem = itemRepository.save(prepareModifiedItem(item, request));
                return generateItemResponse(updatedItem);
            } else {
                throw new IllegalArgumentException("You have no permissions for modification.");
            }
        }

        return null;
    }

    public String changeAuthenticationCode(String guid, String email){
        DBItem item = itemRepository.findByGuidAndEmail(guid, email);
        String authenticationCode;
        if(item != null){
            authenticationCode = generateAuthenticationCode(10);
            item.setAuthenticationCode(authenticationCode);
            itemRepository.save(item);
            emailSender.sendEmail(authenticationCode, email);
            return authenticationCode;
        } else {
            throw new IllegalArgumentException("For your email does not exist any item.");
        }
    }

}
