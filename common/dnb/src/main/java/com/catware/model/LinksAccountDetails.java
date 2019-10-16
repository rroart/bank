package com.catware.model;

public class LinksAccountDetails {
	private HrefType balances;
	
	private HrefType transactions;

	public LinksAccountDetails(HrefType balances, HrefType transactions) {
		super();
		this.balances = balances;
		this.transactions = transactions;
	}

	public LinksAccountDetails() {
		super();
	}

	public HrefType getBalances() {
		return balances;
	}

	public void setBalances(HrefType balances) {
		this.balances = balances;
	}

	public HrefType getTransactions() {
		return transactions;
	}

	public void setTransactions(HrefType transactions) {
		this.transactions = transactions;
	}
	
}
