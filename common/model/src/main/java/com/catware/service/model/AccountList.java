package com.catware.service.model;

import java.util.List;

public class AccountList {
	private List<AccountDetails> accounts;

	public AccountList() {
		super();
	}

	public AccountList(List<AccountDetails> accounts) {
		this.accounts = accounts;
	}

	public List<AccountDetails> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDetails> accounts) {
		this.accounts = accounts;
	}

}
