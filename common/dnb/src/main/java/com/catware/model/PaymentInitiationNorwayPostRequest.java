package com.catware.model;

import java.time.LocalDate;

public class PaymentInitiationNorwayPostRequest {

	private AccountReference creditorAccount;
	
	private Address creditorAddress;
	
	private String creditorAgent;
	
	private String creditorName;
	
	private AccountReference debtorAccount;
	
	private String endToEndIdentification;
	
	private Amount instructedAmount;
	
	private String purposeCode;
	
	private RemittanceInformationStructured remittanceInformationStructured;
	
	private String remittanceInformationUnstructured;
	
	private LocalDate requestedExecutionDate;	
	
	private String ultimateCreditor;
	
	private String ultimateDebtor;

	public PaymentInitiationNorwayPostRequest() {
		super();
	}

	public AccountReference getCreditorAccount() {
		return creditorAccount;
	}

	public void setCreditorAccount(AccountReference creditorAccount) {
		this.creditorAccount = creditorAccount;
	}

	public Address getCreditorAddress() {
		return creditorAddress;
	}

	public void setCreditorAddress(Address creditorAddress) {
		this.creditorAddress = creditorAddress;
	}

	public String getCreditorAgent() {
		return creditorAgent;
	}

	public void setCreditorAgent(String creditorAgent) {
		this.creditorAgent = creditorAgent;
	}

	public String getCreditorName() {
		return creditorName;
	}

	public void setCreditorName(String creditorName) {
		this.creditorName = creditorName;
	}

	public AccountReference getDebtorAccount() {
		return debtorAccount;
	}

	public void setDebtorAccount(AccountReference debtorAccount) {
		this.debtorAccount = debtorAccount;
	}

	public String getEndToEndIdentification() {
		return endToEndIdentification;
	}

	public void setEndToEndIdentification(String endToEndIdentification) {
		this.endToEndIdentification = endToEndIdentification;
	}

	public Amount getInstructedAmount() {
		return instructedAmount;
	}

	public void setInstructedAmount(Amount instructedAmount) {
		this.instructedAmount = instructedAmount;
	}

	public String getPurposeCode() {
		return purposeCode;
	}

	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}

	public RemittanceInformationStructured getRemittanceInformationStructured() {
		return remittanceInformationStructured;
	}

	public void setRemittanceInformationStructured(RemittanceInformationStructured remittanceInformationStructured) {
		this.remittanceInformationStructured = remittanceInformationStructured;
	}

	public String getRemittanceInformationUnstructured() {
		return remittanceInformationUnstructured;
	}

	public void setRemittanceInformationUnstructured(String remittanceInformationUnstructured) {
		this.remittanceInformationUnstructured = remittanceInformationUnstructured;
	}

	public LocalDate getRequestedExecutionDate() {
		return requestedExecutionDate;
	}

	public void setRequestedExecutionDate(LocalDate requestedExecutionDate) {
		this.requestedExecutionDate = requestedExecutionDate;
	}

	public String getUltimateCreditor() {
		return ultimateCreditor;
	}

	public void setUltimateCreditor(String ultimateCreditor) {
		this.ultimateCreditor = ultimateCreditor;
	}

	public String getUltimateDebtor() {
		return ultimateDebtor;
	}

	public void setUltimateDebtor(String ultimateDebtor) {
		this.ultimateDebtor = ultimateDebtor;
	}
}
