package com.example.payload;

import java.time.LocalDateTime;
import java.util.Optional;

public class ItemResponse {

	private String brand;
	
	private String type;
	
	private String guid;
	
	private LocalDateTime createdDateTime;

	private String email;

	private Optional<String> authenticationCode;
	
	public ItemResponse(String brand, String type, String guid, LocalDateTime createdDateTime, String email, Optional<String> authenticationCode) {
		super();
		this.brand = brand;
		this.type = type;
		this.guid = guid;
		this.createdDateTime = createdDateTime;
		this.email = email;
		this.authenticationCode = authenticationCode;
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

	public Optional<String> getAuthenticationCode() {
		return authenticationCode;
	}

	public void setAuthenticationCode(Optional<String> authenticationCode) {
		this.authenticationCode = authenticationCode;
	}
}
