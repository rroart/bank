package com.catware.service.model;

public class LinksConsents {
	private HrefType scaRedirect = null;

	private HrefType scaStatus = null;

	public LinksConsents() {
		super();
	}
	
	public LinksConsents(HrefType scaRedirect, HrefType scaStatus) {
		super();
		this.scaRedirect = scaRedirect;
		this.scaStatus = scaStatus;
	}

	public HrefType getScaRedirect() {
		return scaRedirect;
	}

	public void setScaRedirect(HrefType scaRedirect) {
		this.scaRedirect = scaRedirect;
	}

	public HrefType getScaStatus() {
		return scaStatus;
	}

	public void setScaStatus(HrefType scaStatus) {
		this.scaStatus = scaStatus;
	}

}
