package com.catware.service.model;

public class ConsentAccount {

	private String bban = null;

	private String iban = null;

	public ConsentAccount() {
		super();
	}

	public String getBban() {
		return bban;
	}

	public void setBban(String bban) {
		this.bban = bban;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
}
