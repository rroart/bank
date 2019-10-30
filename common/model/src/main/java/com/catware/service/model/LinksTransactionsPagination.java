package com.catware.service.model;

public class LinksTransactionsPagination {
	private HrefType next = null;

	public LinksTransactionsPagination() {
		super();
	}

	public LinksTransactionsPagination(HrefType next) {
		super();
		this.next = next;
	}

	public HrefType getNext() {
		return next;
	}

	public void setNext(HrefType next) {
		this.next = next;
	}

}
