package com.catware.service.model;

public class RemittanceInformationStructured {

	private String reference;

	private String referenceIssuer;

	private String referenceType;

	public RemittanceInformationStructured() {
		super();
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getReferenceIssuer() {
		return referenceIssuer;
	}

	public void setReferenceIssuer(String referenceIssuer) {
		this.referenceIssuer = referenceIssuer;
	}

	public String getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

}
