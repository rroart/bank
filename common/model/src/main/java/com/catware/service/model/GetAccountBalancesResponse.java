package com.catware.service.model;

import java.util.ArrayList;
import java.util.List;

public class GetAccountBalancesResponse {

	private AccountReference account = null;

	private List<Balance> balances = new ArrayList<Balance>();

	public GetAccountBalancesResponse() {
		super();
	}

	public GetAccountBalancesResponse(AccountReference account, List<Balance> balances) {
		super();
		this.account = account;
		this.balances = balances;
	}

	public AccountReference getAccount() {
		return account;
	}

	public void setAccount(AccountReference account) {
		this.account = account;
	}

	public List<Balance> getBalances() {
		return balances;
	}

	public void setBalances(List<Balance> balances) {
		this.balances = balances;
	}
}
