package com.catware.model;

import java.util.List;

public class AccountList {
	private List<AccountDetails> accounts;

	public AccountList() {
		super();
	}

	public List<AccountDetails> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDetails> accounts) {
		this.accounts = accounts;
	}

}
