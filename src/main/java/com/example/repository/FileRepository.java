package com.example.repository;

import com.example.model.DBFile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<DBFile, String> {

	public List<DBFile> findByGuid(String guid);
	
	public void deleteByGuid(String guid);
	
}
