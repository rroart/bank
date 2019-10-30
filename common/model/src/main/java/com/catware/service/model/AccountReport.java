package com.catware.service.model;

import java.util.List;
import java.util.Map;

public class AccountReport {

	private List<TransactionDetails> booked = null;

	private List<TransactionDetails> pending = null;

	public AccountReport() {
		super();
	}

	public AccountReport(List<TransactionDetails> booked, List<TransactionDetails> pending) {
		super();
		this.booked = booked;
		this.pending = pending;
	}

	public List<TransactionDetails> getBooked() {
		return booked;
	}

	public void setBooked(List<TransactionDetails> booked) {
		this.booked = booked;
	}

	public List<TransactionDetails> getPending() {
		return pending;
	}

	public void setPending(List<TransactionDetails> pending) {
		this.pending = pending;
	}

}
