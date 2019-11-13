package com.catware.model;

public class LinksSigningBasket {
	private HrefType self = null;

	private HrefType startAuthorisation = null;

	private HrefType status = null;

	public HrefType getSelf() {
		return self;
	}

	public void setSelf(HrefType self) {
		this.self = self;
	}

	public HrefType getStartAuthorisation() {
		return startAuthorisation;
	}

	public void setStartAuthorisation(HrefType startAuthorisation) {
		this.startAuthorisation = startAuthorisation;
	}

	public HrefType getStatus() {
		return status;
	}

	public void setStatus(HrefType status) {
		this.status = status;
	}

}
