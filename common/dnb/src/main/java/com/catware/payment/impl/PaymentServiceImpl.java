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
import com.catware.model.PaymentInitiationNorwayPostRequest;
import com.catware.util.http.MyRequest;
import com.catware.util.json.JsonUtil;

public class PaymentServiceImpl extends PaymentService {

	@Override
	public String getPaymentsToApprove() throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put("PSU-IP-Address", "");
		header.put("TPP-Redirect-URI", "http://0.0.0.0:3083");
		return new MyRequest(Constants.psd2endpoint, "v1/payments/approval", "GET", header, null).request();
	}

	@Override
	public String initiateNorwegianDomesticCreditTransfer(String ssn, String debtorAccount, String creditorAccount,
			String creditorName, String instructedAmount) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put("PSU-ID", ssn);
		header.put("TPP-Redirect-URI", "http://0.0.0.0:3083");
		PaymentInitiationNorwayPostRequest paymentInitiation = new PaymentInitiationNorwayPostRequest();
		String body = JsonUtil.convert(paymentInitiation);
		return new MyRequest(Constants.psd2endpoint, "https://sandboxapi.psd.dnb.no/v1/payments/norwegian-domestic-credit-transfers", "POST", header, body).request();
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
