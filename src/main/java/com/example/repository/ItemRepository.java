package com.example.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.model.DBItem;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<DBItem, String>{	
	
	public DBItem findByGuid(String guid);
	
	public void deleteByGuid(String guid);

	public DBItem findByGuidAndEmail(String guid, String email);
	
	public List<DBItem> findByBrand(String brand);
	
	public List<DBItem> findByType(String type);
	
	public List<DBItem> findByBrandAndType(String brand, String type);
	
}
