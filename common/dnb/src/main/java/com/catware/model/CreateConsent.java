package com.catware.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateConsent {

	private ConsentAccess access = null;

	private Boolean combinedServiceIndicator = null;

	private Integer frequencyPerDay = null;

	private Boolean recurringIndicator = null;

	private LocalDate validUntil = null;

	public CreateConsent() {
		super();
	}

	public ConsentAccess getAccess() {
		return access;
	}

	public void setAccess(ConsentAccess access) {
		this.access = access;
	}

	public Boolean getCombinedServiceIndicator() {
		return combinedServiceIndicator;
	}

	public void setCombinedServiceIndicator(Boolean combinedServiceIndicator) {
		this.combinedServiceIndicator = combinedServiceIndicator;
	}

	public Integer getFrequencyPerDay() {
		return frequencyPerDay;
	}

	public void setFrequencyPerDay(Integer frequencyPerDay) {
		this.frequencyPerDay = frequencyPerDay;
	}

	public Boolean getRecurringIndicator() {
		return recurringIndicator;
	}

	public void setRecurringIndicator(Boolean recurringIndicator) {
		this.recurringIndicator = recurringIndicator;
	}

	public String getValidUntil() {
		return validUntil.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

	public void setValidUntil(LocalDate validUntil) {
		this.validUntil = validUntil;
	}
}
