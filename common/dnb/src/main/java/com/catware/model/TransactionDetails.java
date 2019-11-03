package com.catware.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TransactionDetails {

	private String additionalInformation = null;

	private String bankTransactionCode = null;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date bookingDate = null;

	private String checkId = null;

	private AccountReference creditorAccount = null;

	private String creditorId = null;

	private String creditorName = null;

	private List<ReportExchangeRate> currencyExchange = null;

	private AccountReference debtorAccount = null;

	private String debtorName = null;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date dnbTransactionDateTime = null;

	private String dnbTransactionType = null;

	private String endToEndId = null;

	private String entryReference = null;

	private Map<String, HrefType> links = null;

	private String mandateId = null;

	private String proprietaryBankTransactionCode = null;

	private String purposeCode;

	private String remittanceInformationStructured = null;

	private String remittanceInformationUnstructured = null;

	private Amount transactionAmount = null;

	private String transactionId = null;

	private String ultimateCreditor = null;

	private String ultimateDebtor = null;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date valueDate = null;

	public TransactionDetails() {
		super();
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getBankTransactionCode() {
		return bankTransactionCode;
	}

	public void setBankTransactionCode(String bankTransactionCode) {
		this.bankTransactionCode = bankTransactionCode;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public AccountReference getCreditorAccount() {
		return creditorAccount;
	}

	public void setCreditorAccount(AccountReference creditorAccount) {
		this.creditorAccount = creditorAccount;
	}

	public String getCreditorId() {
		return creditorId;
	}

	public void setCreditorId(String creditorId) {
		this.creditorId = creditorId;
	}

	public String getCreditorName() {
		return creditorName;
	}

	public void setCreditorName(String creditorName) {
		this.creditorName = creditorName;
	}

	public List<ReportExchangeRate> getCurrencyExchange() {
		return currencyExchange;
	}

	public void setCurrencyExchange(List<ReportExchangeRate> currencyExchange) {
		this.currencyExchange = currencyExchange;
	}

	public AccountReference getDebtorAccount() {
		return debtorAccount;
	}

	public void setDebtorAccount(AccountReference debtorAccount) {
		this.debtorAccount = debtorAccount;
	}

	public String getDebtorName() {
		return debtorName;
	}

	public void setDebtorName(String debtorName) {
		this.debtorName = debtorName;
	}

	public Date getDnbTransactionDateTime() {
		return dnbTransactionDateTime;
	}

	public void setDnbTransactionDateTime(Date dnbTransactionDateTime) {
		this.dnbTransactionDateTime = dnbTransactionDateTime;
	}

	public String getDnbTransactionType() {
		return dnbTransactionType;
	}

	public void setDnbTransactionType(String dnbTransactionType) {
		this.dnbTransactionType = dnbTransactionType;
	}

	public String getEndToEndId() {
		return endToEndId;
	}

	public void setEndToEndId(String endToEndId) {
		this.endToEndId = endToEndId;
	}

	public String getEntryReference() {
		return entryReference;
	}

	public void setEntryReference(String entryReference) {
		this.entryReference = entryReference;
	}

	public Map<String, HrefType> getLinks() {
		return links;
	}

	public void setLinks(Map<String, HrefType> links) {
		this.links = links;
	}

	public String getMandateId() {
		return mandateId;
	}

	public void setMandateId(String mandateId) {
		this.mandateId = mandateId;
	}

	public String getProprietaryBankTransactionCode() {
		return proprietaryBankTransactionCode;
	}

	public void setProprietaryBankTransactionCode(String proprietaryBankTransactionCode) {
		this.proprietaryBankTransactionCode = proprietaryBankTransactionCode;
	}

	public String getPurposeCode() {
		return purposeCode;
	}

	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}

	public String getRemittanceInformationStructured() {
		return remittanceInformationStructured;
	}

	public void setRemittanceInformationStructured(String remittanceInformationStructured) {
		this.remittanceInformationStructured = remittanceInformationStructured;
	}

	public String getRemittanceInformationUnstructured() {
		return remittanceInformationUnstructured;
	}

	public void setRemittanceInformationUnstructured(String remittanceInformationUnstructured) {
		this.remittanceInformationUnstructured = remittanceInformationUnstructured;
	}

	public Amount getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Amount transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

}
