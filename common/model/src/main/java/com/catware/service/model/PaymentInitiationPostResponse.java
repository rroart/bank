package com.catware.service.model;

import java.util.List;

public class PaymentInitiationPostResponse {

	private LinksPaymentInitiation _links = null;

	private ChallengeData challengeData = null;

	private AuthenticationObject chosenScaMethod = null;

	private String paymentId = null;

	private String psuMessage = null;

	private List<AuthenticationObject> scaMethods = null;

	private List<TppMessageGeneric> tppMessages = null;

	private Boolean transactionFeeIndicator = null;

	private Amount transactionFees = null;

	private String transactionStatus;

	public PaymentInitiationPostResponse() {
		super();
	}

	public PaymentInitiationPostResponse(String paymentId, LinksPaymentInitiation _links) {
		this.paymentId = paymentId;
		this._links = _links;
	}

	public LinksPaymentInitiation get_links() {
		return _links;
	}

	public void set_links(LinksPaymentInitiation _links) {
		this._links = _links;
	}

	public ChallengeData getChallengeData() {
		return challengeData;
	}

	public void setChallengeData(ChallengeData challengeData) {
		this.challengeData = challengeData;
	}

	public AuthenticationObject getChosenScaMethod() {
		return chosenScaMethod;
	}

	public void setChosenScaMethod(AuthenticationObject chosenScaMethod) {
		this.chosenScaMethod = chosenScaMethod;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPsuMessage() {
		return psuMessage;
	}

	public void setPsuMessage(String psuMessage) {
		this.psuMessage = psuMessage;
	}

	public List<AuthenticationObject> getScaMethods() {
		return scaMethods;
	}

	public void setScaMethods(List<AuthenticationObject> scaMethods) {
		this.scaMethods = scaMethods;
	}

	public List<TppMessageGeneric> getTppMessages() {
		return tppMessages;
	}

	public void setTppMessages(List<TppMessageGeneric> tppMessages) {
		this.tppMessages = tppMessages;
	}

	public Boolean getTransactionFeeIndicator() {
		return transactionFeeIndicator;
	}

	public void setTransactionFeeIndicator(Boolean transactionFeeIndicator) {
		this.transactionFeeIndicator = transactionFeeIndicator;
	}

	public Amount getTransactionFees() {
		return transactionFees;
	}

	public void setTransactionFees(Amount transactionFees) {
		this.transactionFees = transactionFees;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

}
