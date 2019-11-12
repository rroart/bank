package com.catware.model;

public class LinksPaymentInitiation {

	private HrefType scaOAuth = null;

	private HrefType scaRedirect = null;

	private HrefType scaStatus = null;

	private HrefType self = null;

	private HrefType startAuthorisation = null;

	private HrefType startAuthorisationWithAuthenticationMethodSelection = null;

	private HrefType startAuthorisationWithPsuAuthentication = null;

	private HrefType startAuthorisationWithPsuIdentification = null;

	private HrefType startAuthorisationWithTransactionAuthorisation = null;

	private HrefType status = null;

	public LinksPaymentInitiation() {
		super();
	}

	public HrefType getScaOAuth() {
		return scaOAuth;
	}

	public void setScaOAuth(HrefType scaOAuth) {
		this.scaOAuth = scaOAuth;
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

	public HrefType getStartAuthorisationWithAuthenticationMethodSelection() {
		return startAuthorisationWithAuthenticationMethodSelection;
	}

	public void setStartAuthorisationWithAuthenticationMethodSelection(
			HrefType startAuthorisationWithAuthenticationMethodSelection) {
		this.startAuthorisationWithAuthenticationMethodSelection = startAuthorisationWithAuthenticationMethodSelection;
	}

	public HrefType getStartAuthorisationWithPsuAuthentication() {
		return startAuthorisationWithPsuAuthentication;
	}

	public void setStartAuthorisationWithPsuAuthentication(HrefType startAuthorisationWithPsuAuthentication) {
		this.startAuthorisationWithPsuAuthentication = startAuthorisationWithPsuAuthentication;
	}

	public HrefType getStartAuthorisationWithPsuIdentification() {
		return startAuthorisationWithPsuIdentification;
	}

	public void setStartAuthorisationWithPsuIdentification(HrefType startAuthorisationWithPsuIdentification) {
		this.startAuthorisationWithPsuIdentification = startAuthorisationWithPsuIdentification;
	}

	public HrefType getStartAuthorisationWithTransactionAuthorisation() {
		return startAuthorisationWithTransactionAuthorisation;
	}

	public void setStartAuthorisationWithTransactionAuthorisation(HrefType startAuthorisationWithTransactionAuthorisation) {
		this.startAuthorisationWithTransactionAuthorisation = startAuthorisationWithTransactionAuthorisation;
	}

	public HrefType getStatus() {
		return status;
	}

	public void setStatus(HrefType status) {
		this.status = status;
	}

}
