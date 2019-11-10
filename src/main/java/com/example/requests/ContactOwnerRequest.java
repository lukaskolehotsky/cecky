package com.example.requests;

public class ContactOwnerRequest {

	private String buyerEmail;
	private String buyerMessage;
	private String buyerMobile;

	public ContactOwnerRequest(String buyerEmail, String buyerMessage, String buyerMobile) {
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

	public String getBuyerMobile() {
		return buyerMobile;
	}

	public void setBuyerMobile(String buyerMobile) {
		this.buyerMobile = buyerMobile;
	}

	@Override
	public String toString() {
		return "ContactOwnerRequest [buyerEmail=" + buyerEmail + ", buyerMessage=" + buyerMessage + ", buyerMobile="
				+ buyerMobile + "]";
	}

}
