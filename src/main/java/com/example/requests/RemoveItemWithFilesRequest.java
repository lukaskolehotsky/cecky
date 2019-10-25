package com.example.requests;


public class RemoveItemWithFilesRequest {

	private String authCode;

	public RemoveItemWithFilesRequest(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	@Override
	public String toString() {
		return "RemoveItemWithFilesRequest [authCode=" + authCode + "]";
	}	
	
}
