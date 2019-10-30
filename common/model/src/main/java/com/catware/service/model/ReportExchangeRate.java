package com.catware.service.model;

import java.time.LocalDate;

public class ReportExchangeRate {
	private String contractIdentification = null;

	private String exchangeRate = null;

	private LocalDate quotationDate = null;

	private String sourceCurrency = null;

	private String targetCurrency = null;

	private String unitCurrency = null;

	public ReportExchangeRate() {
		super();
	}

	public String getContractIdentification() {
		return contractIdentification;
	}

	public void setContractIdentification(String contractIdentification) {
		this.contractIdentification = contractIdentification;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public LocalDate getQuotationDate() {
		return quotationDate;
	}

	public void setQuotationDate(LocalDate quotationDate) {
		this.quotationDate = quotationDate;
	}

	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public String getUnitCurrency() {
		return unitCurrency;
	}

	public void setUnitCurrency(String unitCurrency) {
		this.unitCurrency = unitCurrency;
	}

}
