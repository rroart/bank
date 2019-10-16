package com.catware.model;

public class AccountList {
	private AccountDetails[] accounts;

	public AccountList(AccountDetails[] accounts) {
		super();
		this.accounts = accounts;
	}

	public AccountList() {
		super();
	}

	public AccountDetails[] getAccounts() {
		return accounts;
	}

	public void setAccounts(AccountDetails[] accounts) {
		this.accounts = accounts;
	}
	
}
