package com.catware.model;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GetConsentResponse {

	private Map<String, HrefType> _links = null;

	private ConsentAccess access = null;

	private String consentStatus = null;

	private Integer frequencyPerDay = null;

	private OffsetDateTime lastActionDate = null;

	private Boolean recurringIndicator = null;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date validUntil = null;

	public GetConsentResponse() {
		super();
	}

	public Map<String, HrefType> get_links() {
		return _links;
	}

	public void set_links(Map<String, HrefType> _links) {
		this._links = _links;
	}

	public ConsentAccess getAccess() {
		return access;
	}

	public void setAccess(ConsentAccess access) {
		this.access = access;
	}

	public String getConsentStatus() {
		return consentStatus;
	}

	public void setConsentStatus(String consentStatus) {
		this.consentStatus = consentStatus;
	}

	public Integer getFrequencyPerDay() {
		return frequencyPerDay;
	}

	public void setFrequencyPerDay(Integer frequencyPerDay) {
		this.frequencyPerDay = frequencyPerDay;
	}

	public OffsetDateTime getLastActionDate() {
		return lastActionDate;
	}

	public void setLastActionDate(OffsetDateTime lastActionDate) {
		this.lastActionDate = lastActionDate;
	}

	public Boolean getRecurringIndicator() {
		return recurringIndicator;
	}

	public void setRecurringIndicator(Boolean recurringIndicator) {
		this.recurringIndicator = recurringIndicator;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

}
