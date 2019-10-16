package com.catware.model;

public class LinksAccountDetails {
	private Href balances;
	
	private Href transactions;

	public LinksAccountDetails(Href balances, Href transactions) {
		super();
		this.balances = balances;
		this.transactions = transactions;
	}

	public LinksAccountDetails() {
		super();
	}

	public Href getBalances() {
		return balances;
	}

	public void setBalances(Href balances) {
		this.balances = balances;
	}

	public Href getTransactions() {
		return transactions;
	}

	public void setTransactions(Href transactions) {
		this.transactions = transactions;
	}
	
}
