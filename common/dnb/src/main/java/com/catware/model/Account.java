package com.catware.model;

public class Account {
	private String iban;
	
	private String bban;
	
	private String currency;
	
	private String name;
	
	private AccountLinks _links;

	public Account(String iban, String bban, String currency, String name, AccountLinks _links) {
		super();
		this.iban = iban;
		this.bban = bban;
		this.currency = currency;
		this.name = name;
		this._links = _links;
	}
	
	public Account() {
		super();
	}



	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBban() {
		return bban;
	}

	public void setBban(String bban) {
		this.bban = bban;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountLinks get_links() {
		return _links;
	}

	public void set_links(AccountLinks _links) {
		this._links = _links;
	}
	
}
