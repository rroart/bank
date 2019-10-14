package com.catware.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AccountBalance {
	private BalanceAmount balanceAmount;
	
	private String balanceType;
	
	private LocalDateTime lastChangeDateTime;
	
	private String lastComittedTransaction;
	
	private LocalDate referenceDate;

	public AccountBalance() {
		super();
	}

	public BalanceAmount getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BalanceAmount balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}

	public LocalDateTime getLastChangeDateTime() {
		return lastChangeDateTime;
	}

	public void setLastChangeDateTime(LocalDateTime lastChangeDateTime) {
		this.lastChangeDateTime = lastChangeDateTime;
	}

	public String getLastComittedTransaction() {
		return lastComittedTransaction;
	}

	public void setLastComittedTransaction(String lastComittedTransaction) {
		this.lastComittedTransaction = lastComittedTransaction;
	}

	public LocalDate getReferenceDate() {
		return referenceDate;
	}

	public void setReferenceDate(LocalDate referenceDate) {
		this.referenceDate = referenceDate;
	}
	
}
