package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.DBItem;

@Repository
public interface ItemRepository extends JpaRepository<DBItem, String>{
	
	public DBItem findByGuid(String guid);
	
}
