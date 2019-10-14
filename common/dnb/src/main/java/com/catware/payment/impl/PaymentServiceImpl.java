package com.catware.payment.impl;

import com.catware.payment.PaymentService;

public class PaymentServiceImpl extends PaymentService {

	@Override
	public String getPaymentsToApprove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String initiateNorwegianDomesticCreditTransfer(String ssn, String debtorAccount, String creditorAccount,
			String creditorName, String instructedAmount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String initiateNorwegianDomesticCreditTransferToSelf(String ssn, String debtorAccount,
			String creditorAccount, String instructedAmount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPaymentDetails(String paymentproduct, String paymentid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPaymentStatus(String paymentproduct, String paymentid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String initiateNewSigninngBasket(String ssn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSigninngBasket(String basketid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String startSigninngBasket(String basketid, String ssn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSigninngBasketStatus(String basketid) {
		// TODO Auto-generated method stub
		return null;
	}

}
