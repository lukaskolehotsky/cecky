package com.example.payload;

import java.util.List;

public class ItemWithFilesResponse {
	
	private ItemResponse itemResponse;
	private List<FileResponse> fileResponses;
	public ItemWithFilesResponse(ItemResponse itemResponse, List<FileResponse> fileResponses) {
		super();
		this.itemResponse = itemResponse;
		this.fileResponses = fileResponses;
	}
	
	public ItemResponse getItemResponse() {
		return itemResponse;
	}
	
	public void setItemResponse(ItemResponse itemResponse) {
		this.itemResponse = itemResponse;
	}
	
	public List<FileResponse> getFileResponses() {
		return fileResponses;
	}
	
	public void setFileResponses(List<FileResponse> fileResponses) {
		this.fileResponses = fileResponses;
	}
	

}
