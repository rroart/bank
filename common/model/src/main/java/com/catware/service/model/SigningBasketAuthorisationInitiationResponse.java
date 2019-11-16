package com.catware.service.model;

public class SigningBasketAuthorisationInitiationResponse {
	private LinksConsents _links = null;

	private String authorisationId = null;

	private String scaStatus;

	public SigningBasketAuthorisationInitiationResponse() {
		super();
	}
	
	public SigningBasketAuthorisationInitiationResponse(LinksConsents _links, String authorisationId,
			String scaStatus) {
		super();
		this._links = _links;
		this.authorisationId = authorisationId;
		this.scaStatus = scaStatus;
	}

	public LinksConsents get_links() {
		return _links;
	}

	public void set_links(LinksConsents _links) {
		this._links = _links;
	}

	public String getAuthorisationId() {
		return authorisationId;
	}

	public void setAuthorisationId(String authorisationId) {
		this.authorisationId = authorisationId;
	}

	public String getScaStatus() {
		return scaStatus;
	}

	public void setScaStatus(String scaStatus) {
		this.scaStatus = scaStatus;
	}

}
