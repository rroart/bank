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
import com.catware.model.AccountReference;
import com.catware.model.Amount;
import com.catware.model.HrefType;
import com.catware.model.LinksPaymentInitiation;
import com.catware.model.PaymentInitiationNorwayPostRequest;
import com.catware.model.PaymentInitiationPostResponse;
import com.catware.util.http.MyResponse;
import com.catware.util.http.dnb.DNBRequest;
import com.catware.util.json.JsonUtil;

public class PaymentServiceImpl extends PaymentService {

	@Override
	public MyResponse getPaymentsToApprove() throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put("PSU-IP-Address", "");
		header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
		return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/payments/approval", null, Constants.GET, header, null).request();
	}

	@Override
	public MyResponse initiateNorwegianDomesticCreditTransfer(com.catware.service.model.PaymentInitiationNorwayPostRequest payment) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put(DNBConstants.PSUID, payment.getPsuid());
		header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
		PaymentInitiationNorwayPostRequest paymentInitiation = new PaymentInitiationNorwayPostRequest();
		paymentInitiation.setCreditorAccount(convert(payment.getCreditorAccount()));
		paymentInitiation.setCreditorName(payment.getCreditorName());
		paymentInitiation.setDebtorAccount(convert(payment.getDebtorAccount()));
		paymentInitiation.setInstructedAmount(convert(payment.getInstructedAmount()));
		String body = JsonUtil.convert(paymentInitiation);
		System.out.println("body" + body);
		MyResponse paymentResponse = new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/payments/norwegian-domestic-credit-transfers", null, Constants.POST, header, body).request();
		if (paymentResponse.getCode() == 201) {
			PaymentInitiationPostResponse response = JsonUtil.convert(paymentResponse.getBody(), PaymentInitiationPostResponse.class);
			com.catware.service.model.PaymentInitiationPostResponse aResponse = convert(response);
			String newJson = JsonUtil.convert(aResponse);
			paymentResponse.setBody(newJson);
		}
		return paymentResponse;
	}

	private com.catware.service.model.PaymentInitiationPostResponse convert(PaymentInitiationPostResponse response) {
		return new com.catware.service.model.PaymentInitiationPostResponse(response.getPaymentId(), convert(response.get_links()));
	}

	private com.catware.service.model.LinksPaymentInitiation convert(LinksPaymentInitiation _links) {
		return new com.catware.service.model.LinksPaymentInitiation(convert(_links.getScaRedirect()));
	}

	private com.catware.service.model.HrefType convert(HrefType href) {
		return new com.catware.service.model.HrefType(href.getHref());
	}

	@Override
	public MyResponse initiateNorwegianDomesticCreditTransferToSelf(String psuid, String debtorAccount,
			String creditorAccount, String instructedAmount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyResponse getPaymentDetails(String paymentproduct, String paymentid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyResponse getPaymentStatus(String paymentproduct, String paymentid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyResponse initiateNewSigninngBasket(String psuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyResponse getSigninngBasket(String basketid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyResponse startSigninngBasket(String basketid, String psuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyResponse getSigninngBasketStatus(String basketid) {
		// TODO Auto-generated method stub
		return null;
	}

	private AccountReference convert(com.catware.service.model.AccountReference anAccount) {
		AccountReference account = new AccountReference();
		account.setBban(anAccount.getBban());
		account.setCurrency(anAccount.getCurrency());
		return account;
	}

	private Amount convert(com.catware.service.model.Amount instructedAmount) {
		Amount amount = new Amount();
		amount.setAmount(instructedAmount.getAmount());
		amount.setCurrency(instructedAmount.getCurrency());
		return amount;
	}

}
