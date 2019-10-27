package com.catware.service.model;

public class Amount {
	private String amount;
	
	private String currency;

	public Amount(String amount, String currency) {
		super();
		this.amount = amount;
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
