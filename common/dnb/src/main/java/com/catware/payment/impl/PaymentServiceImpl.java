package com.catware.payment.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.catware.payment.PaymentService;
import com.catware.constants.Constants;
import com.catware.constants.DNBConstants;
import com.catware.model.PaymentInitiationNorwayPostRequest;
import com.catware.util.http.dnb.DNBRequest;
import com.catware.util.json.JsonUtil;

public class PaymentServiceImpl extends PaymentService {

	@Override
	public String getPaymentsToApprove() throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put("PSU-IP-Address", "");
		header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
		return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/payments/approval", Constants.GET, header, null).request();
	}

	@Override
	public String initiateNorwegianDomesticCreditTransfer(String ssn, String debtorAccount, String creditorAccount,
			String creditorName, String instructedAmount) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put(DNBConstants.PSUID, ssn);
		header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
		PaymentInitiationNorwayPostRequest paymentInitiation = new PaymentInitiationNorwayPostRequest();
		String body = JsonUtil.convert(paymentInitiation);
		return new DNBRequest(DNBConstants.PSD2ENDPOINT, "https://sandboxapi.psd.dnb.no/v1/payments/norwegian-domestic-credit-transfers", Constants.POST, header, body).request();
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
