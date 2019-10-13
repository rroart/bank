package com.catware.model;

public class AccountList {
	private Account[] accounts;

	public AccountList(Account[] accounts) {
		super();
		this.accounts = accounts;
	}

	public AccountList() {
		super();
	}

	public Account[] getAccounts() {
		return accounts;
	}

	public void setAccounts(Account[] accounts) {
		this.accounts = accounts;
	}
	
}
