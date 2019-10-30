package com.catware.model;

import java.util.List;
import java.util.Map;

public class AccountReport {

	private List<TransactionDetails> booked = null;

	private Map<String, HrefType> links = null;

	private List<TransactionDetails> pending = null;

	public AccountReport() {
		super();
	}

	public List<TransactionDetails> getBooked() {
		return booked;
	}

	public void setBooked(List<TransactionDetails> booked) {
		this.booked = booked;
	}

	public Map<String, HrefType> getLinks() {
		return links;
	}

	public void setLinks(Map<String, HrefType> links) {
		this.links = links;
	}

	public List<TransactionDetails> getPending() {
		return pending;
	}

	public void setPending(List<TransactionDetails> pending) {
		this.pending = pending;
	}

}
