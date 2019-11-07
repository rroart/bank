package com.catware.service.model;

import java.util.List;

public class AccountDetails {
	private String authorisedBalance;

	private List<Balance> balances;

	private String bban;
	
	private String bic;
	
	private String currency;
	
	private String iban;
	
	private String name;
	
	private String openingBookedBalance;
	
	private String status;
	
	private String usage;

	public AccountDetails() {
		super();
	}

	public AccountDetails(String authorisedBalance, List<Balance> balances, String bban, String currency, String name, String openingBookedBalance) {
		super();
		this.authorisedBalance = authorisedBalance;
		this.balances = balances;
		this.bban = bban;
		this.currency = currency;
		this.name = name;
		this.openingBookedBalance = openingBookedBalance;
	}

	public String getAuthorisedBalance() {
		return authorisedBalance;
	}

	public void setAuthorisedBalance(String authorisedBalance) {
		this.authorisedBalance = authorisedBalance;
	}

	public List<Balance> getBalances() {
		return balances;
	}

	public void setBalances(List<Balance> balances) {
		this.balances = balances;
	}

	public String getBban() {
		return bban;
	}

	public void setBban(String bban) {
		this.bban = bban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpeningBookedBalance() {
		return openingBookedBalance;
	}

	public void setOpeningBookedBalance(String openingBookedBalance) {
		this.openingBookedBalance = openingBookedBalance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

}
