package com.catware.model;

import java.util.Map;

public class CreateConsentResponse {

	private Map<String, HrefType> _links = null;

	private String consentId = null;

	private String consentStatus = null;

	public CreateConsentResponse() {
		super();
	}

	public Map<String, HrefType> get_links() {
		return _links;
	}

	public void set_links(Map<String, HrefType> _links) {
		this._links = _links;
	}

	public String getConsentId() {
		return consentId;
	}

	public void setConsentId(String consentId) {
		this.consentId = consentId;
	}

	public String getConsentStatus() {
		return consentStatus;
	}

	public void setConsentStatus(String consentStatus) {
		this.consentStatus = consentStatus;
	}

}
