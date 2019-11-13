package com.catware.model;

import java.util.List;

public class SigningBasketRequest {
	private List<String> paymentIds = null;

	public List<String> getPaymentIds() {
		return paymentIds;
	}

	public void setPaymentIds(List<String> paymentIds) {
		this.paymentIds = paymentIds;
	}

}
