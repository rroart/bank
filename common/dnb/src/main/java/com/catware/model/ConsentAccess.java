package com.catware.model;

import java.util.List;

public class ConsentAccess {

	private List<ConsentAccount> accounts;

	private List<ConsentAccount> balances;

	private List<ConsentAccount> transactions;

	public ConsentAccess() {
		super();
	}

	public List<ConsentAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<ConsentAccount> accounts) {
		this.accounts = accounts;
	}

	public List<ConsentAccount> getBalances() {
		return balances;
	}

	public void setBalances(List<ConsentAccount> balances) {
		this.balances = balances;
	}

	public List<ConsentAccount> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<ConsentAccount> transactions) {
		this.transactions = transactions;
	}

}
