package com.catware.service.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

public class TransactionDetails {

	private String additionalInformation = null;

	private String bankTransactionCode = null;

	private LocalDate bookingDate = null;

	private String checkId = null;

	private AccountReference creditorAccount = null;

	private String creditorId = null;

	private String creditorName = null;

	private List<ReportExchangeRate> currencyExchange = null;

	private AccountReference debtorAccount = null;

	private String debtorName = null;

	private OffsetDateTime dnbTransactionDateTime = null;

	private String dnbTransactionType = null;

	private String endToEndId = null;

	private String entryReference = null;

	private Map<String, HrefType> links = null;

	private String mandateId = null;

	private String proprietaryBankTransactionCode = null;

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

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
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

	public OffsetDateTime getDnbTransactionDateTime() {
		return dnbTransactionDateTime;
	}

	public void setDnbTransactionDateTime(OffsetDateTime dnbTransactionDateTime) {
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

}
