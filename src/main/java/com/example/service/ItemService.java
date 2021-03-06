package com.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;
import javax.transaction.Transactional;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Utils.Utils;
import com.example.model.DBItem;
import com.example.payload.ItemResponse;
import com.example.repository.ItemRepository;
import com.example.requests.ContactOwnerRequest;
import com.example.requests.CreateItemRequest;
import com.example.requests.SearchRequest;
import com.example.requests.UpdateItemRequest;

@Service
public class ItemService extends Utils {

    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private EmailSender emailSender;

    public ItemResponse createItem(CreateItemRequest request) {

        String authenticationCode = generateAuthenticationCode(10);
        request.setAuthenticationCode(authenticationCode);

        DBItem savedItem = itemRepository.save(generateDBItem(request));
        
        try {
        	emailSender.sendEmail(authenticationCode, request.getEmail(), request.getBrand(), request.getType());
        } catch (Exception e) {
			throw new IllegalArgumentException("Nepodarilo sa odoslat email.");
		} 
        

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
    
    public List<ItemResponse> search(SearchRequest searchRequest){    	
    	List<DBItem> items = null;
    	
    	if(!searchRequest.getBrand().isEmpty() && !searchRequest.getType().isEmpty()) {
    		items = itemRepository.findByBrandAndType(searchRequest.getBrand(), searchRequest.getType());
    	}
    	
    	if(!searchRequest.getBrand().isEmpty() && searchRequest.getType().isEmpty()) {
    		items = itemRepository.findByBrand(searchRequest.getBrand());
    	}
    	
    	if(!searchRequest.getType().isEmpty() && searchRequest.getBrand().isEmpty()) {
    		items = itemRepository.findByType(searchRequest.getType());
    	}
    	
    	List<ItemResponse> itemResponses = new ArrayList<>();

    	if(items != null) {
    		for(DBItem item: items) {
        		itemResponses.add(generateItemResponse(item));
        	}
    	}
    	
    	return itemResponses;
    }  
    
    public Pair<List<ItemResponse>, Integer> getAll(int pageNumber){
    	Pageable paging = PageRequest.of(pageNumber, 12, Sort.by("createdDateTime").descending());
    	
    	Page<DBItem> items = itemRepository.findAll(paging);
    	List<ItemResponse> itemResponses = new ArrayList<>();

    	if(items != null) {
    		for(DBItem item: items.getContent()) {
        		itemResponses.add(generateItemResponse(item));
        	}
    	}
    	
    	return new Pair<List<ItemResponse>, Integer>(itemResponses, (int) (long) items.getTotalElements());
    }  
    
	public Pair<List<ItemResponse>, Integer> search2(int pageNumber, SearchRequest request) {
		Pageable paging = PageRequest.of(pageNumber, 12, Sort.by("created_Date_Time").descending());

		Page<DBItem> items = itemRepository.search2(paging, request.getBrand(), request.getType());

		List<ItemResponse> itemResponses = new ArrayList<>();

		if (items != null) {
			for (DBItem item : items.getContent()) {
				itemResponses.add(generateItemResponse(item));
			}
		}

		return new Pair<List<ItemResponse>, Integer>(itemResponses, (int) (long) items.getTotalElements());
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
            emailSender.sendEmail(authenticationCode, email, item.getBrand(), item.getType());
            return authenticationCode;
        } else {
            throw new IllegalArgumentException("For your email does not exist any item.");
        }
    }
    
	
	public void contactOwner(String guid, ContactOwnerRequest request) {
		DBItem item = itemRepository.findByGuid(guid);
		
		emailSender.sendEmailToOwner(item.getEmail(), request);
	}

}
