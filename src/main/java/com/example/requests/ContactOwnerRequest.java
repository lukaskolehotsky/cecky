package com.example.requests;

public class ContactOwnerRequest {
	
	private String buyerEmail;
	private String buyerMessage;
	private Long buyerMobile;
	
	public ContactOwnerRequest(String buyerEmail, String buyerMessage, Long buyerMobile) {
		super();
		this.buyerEmail = buyerEmail;
		this.buyerMessage = buyerMessage;
		this.buyerMobile = buyerMobile;
	}
	
	public String getBuyerEmail() {
		return buyerEmail;
	}
	
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	
	public String getBuyerMessage() {
		return buyerMessage;
	}
	
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}

	public Long getBuyerMobile() {
		return buyerMobile;
	}

	public void setBuyerMobile(Long buyerMobile) {
		this.buyerMobile = buyerMobile;
	}	

}
