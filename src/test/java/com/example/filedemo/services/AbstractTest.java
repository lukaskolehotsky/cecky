package com.example.filedemo.services;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.filedemo.model.DBFile;
import com.example.filedemo.model.DBItem;

import java.time.LocalDateTime;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class AbstractTest {

	public DBItem generateItem() {
		String guid = "guid";
		String brand = "brand";
		String type = "type";
		LocalDateTime createdDateTime = LocalDateTime.now();
		return new DBItem(brand, type, guid, createdDateTime);
	}
	
	public DBFile generateFile() {
		return new DBFile("name", "type", new byte[1],"guid");
	}
	
}
