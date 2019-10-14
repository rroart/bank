package com.catware.model;

public class Account {
	private String iban;
	
	private String bban;
	
	private String bic;
	
	private String currency;
	
	private String name;
	
	private String status;
	
	private String usage;
	
	private AccountLinks _links;

	private AccountBalances _balances;

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
