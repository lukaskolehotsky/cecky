package com.example.payload;

import java.time.LocalDateTime;

public class ItemResponse {

	private String brand;
	
	private String type;
	
	private String guid;
	
	private LocalDateTime createdDateTime;

	private String email;
	
	public ItemResponse(String brand, String type, String guid, LocalDateTime createdDateTime, String email) {
		super();
		this.brand = brand;
		this.type = type;
		this.guid = guid;
		this.createdDateTime = createdDateTime;
		this.email = email;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
