package com.catware.model;

import java.util.List;

public class GetAccountTransactionsResponse {

	private AccountReference account = null;

	private List<Balance> balances = null;

	private LinksTransactionsPagination links = null;

	private AccountReport transactions = null;

	public GetAccountTransactionsResponse() {
		super();
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

	public LinksTransactionsPagination getLinks() {
		return links;
	}

	public void setLinks(LinksTransactionsPagination links) {
		this.links = links;
	}

	public AccountReport getTransactions() {
		return transactions;
	}

	public void setTransactions(AccountReport transactions) {
		this.transactions = transactions;
	}

}
