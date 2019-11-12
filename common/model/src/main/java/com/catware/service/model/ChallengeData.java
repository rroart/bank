package com.catware.service.model;

public class ChallengeData {

	private String additionalInformation = null;

	private String data = null;

	private byte[] image = null;

	private String imageLink = null;

	private String otpFormat = null;

	public ChallengeData() {
		super();
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getOtpFormat() {
		return otpFormat;
	}

	public void setOtpFormat(String otpFormat) {
		this.otpFormat = otpFormat;
	}
}
