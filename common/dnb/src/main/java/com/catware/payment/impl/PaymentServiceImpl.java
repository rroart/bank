package com.catware.payment.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.catware.payment.PaymentService;
import com.catware.model.LinksConsents;
import com.catware.constants.Constants;
import com.catware.constants.DNBConstants;
import com.catware.model.AccountReference;
import com.catware.model.Amount;
import com.catware.model.HrefType;
import com.catware.model.LinksPaymentInitiation;
import com.catware.model.PaymentInitiationNorwayPostRequest;
import com.catware.model.PaymentInitiationPostResponse;
import com.catware.model.SigningBasketAuthorisationInitiationResponse;
import com.catware.model.SigningBasketRequest;
import com.catware.model.SigningBasketResponse201;
import com.catware.util.http.MyResponse;
import com.catware.util.http.dnb.DNBRequest;
import com.catware.util.http.dnb.ErrorUtil;
import com.catware.util.http.dnb.RedirectUtil;
import com.catware.util.json.JsonUtil;

public class PaymentServiceImpl extends PaymentService {

	@Override
	public MyResponse getPaymentsToApprove() throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put("PSU-IP-Address", "");
		header.put(DNBConstants.TPPREDIRECTURI, RedirectUtil.getUrl(null, DNBConstants.GOMULTIPAY));
		return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/payments/approval", null, Constants.GET, header, null).request();
	}

	@Override
	public MyResponse initiateNorwegianDomesticCreditTransfer(com.catware.service.model.PaymentInitiationNorwayPostRequest payment) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		return aDomesticCreditTransfer(payment, header);
	}

	private MyResponse aDomesticCreditTransfer(com.catware.service.model.PaymentInitiationNorwayPostRequest payment, Map<String, String> header)
			throws IOException, KeyStoreException, UnrecoverableKeyException, KeyManagementException,
			NoSuchAlgorithmException, CertificateException {
		header.put(DNBConstants.PSUID, payment.getPsuid());
		header.put(DNBConstants.TPPREDIRECTURI, RedirectUtil.getUrl(payment.getPsuid(), DNBConstants.GOSINGLEPAY));
		PaymentInitiationNorwayPostRequest paymentInitiation = new PaymentInitiationNorwayPostRequest();
		paymentInitiation.setCreditorAccount(convert(payment.getCreditorAccount()));
		paymentInitiation.setCreditorName(payment.getCreditorName());
		paymentInitiation.setDebtorAccount(convert(payment.getDebtorAccount()));
		paymentInitiation.setInstructedAmount(convert(payment.getInstructedAmount()));
		paymentInitiation.setRequestedExecutionDate(convert(payment.getRequestedExecutionDate()));
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

	private LocalDate convert(Date date) {
		if (date == null) {
			return null;
		}
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	@Override
	public MyResponse initiateNorwegianDomesticCreditTransfers(com.catware.service.model.PaymentInitiationNorwayPostRequests payments) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		List<String> paymentIds = new ArrayList<>();
		for (com.catware.service.model.PaymentInitiationNorwayPostRequest payment : payments.getPayments()) {
			Map<String, String> header = new LinkedHashMap<>();
			// needed for signing basket
			header.put(DNBConstants.TPPEXPLICITAUTHORISATIONPREFERRED, "true");
			MyResponse aPaymentResponse = aDomesticCreditTransfer(payment, header);
			if (aPaymentResponse.getCode() == 201) {
				PaymentInitiationPostResponse response = JsonUtil.convert(aPaymentResponse.getBody(), PaymentInitiationPostResponse.class);
				String paymentId = response.getPaymentId();
				paymentIds.add(paymentId);
			} else {
				return ErrorUtil.getError(aPaymentResponse);
			}
		}
		String psuid = payments.getPayments().get(0).getPsuid();
		MyResponse aResponse = initiateNewSigningBasket(psuid, paymentIds);
		if (aResponse.getCode() == 201) {
			SigningBasketResponse201 response = JsonUtil.convert(aResponse.getBody(), SigningBasketResponse201.class);
			String basketId = response.getBasketId();
			MyResponse aNewResponse = startSigningBasket(basketId, psuid);
			if (aNewResponse.getCode() == 200) {
				SigningBasketAuthorisationInitiationResponse authResponse = JsonUtil.convert(aNewResponse.getBody(), SigningBasketAuthorisationInitiationResponse.class);
				String newJson = JsonUtil.convert(convert(authResponse));
				aNewResponse.setBody(newJson);
				return aNewResponse;
			} else {
				return ErrorUtil.getError(aResponse);
			}
		} else {
			return ErrorUtil.getError(aResponse);
		}
	}

	private com.catware.service.model.SigningBasketAuthorisationInitiationResponse convert(SigningBasketAuthorisationInitiationResponse authResponse) {
		return new com.catware.service.model.SigningBasketAuthorisationInitiationResponse(convert(authResponse.get_links()), authResponse.getAuthorisationId(), authResponse.getScaStatus());
	}

	private com.catware.service.model.LinksConsents convert(LinksConsents links) {
		return new com.catware.service.model.LinksConsents(convert(links.getScaRedirect()), convert(links.getScaStatus()));
	}

	private MyResponse initiateNewSigningBasket(String psuid, List<String> paymentIds) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put("PSU-IP-Address", "");
		header.put(DNBConstants.PSUID, psuid);
		header.put(DNBConstants.TPPREDIRECTURI, RedirectUtil.getUrl(psuid, DNBConstants.GOMULTIPAY));
		header.put(DNBConstants.TPPEXPLICITAUTHORISATIONPREFERRED, "true");
		SigningBasketRequest request = new SigningBasketRequest();
		request.setPaymentIds(paymentIds);
		String body = JsonUtil.convert(request);
		return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/signing-baskets", null, Constants.POST, header, body).request();		
	}
	
	private com.catware.service.model.PaymentInitiationPostResponse convert(PaymentInitiationPostResponse response) {
		return new com.catware.service.model.PaymentInitiationPostResponse(response.getPaymentId(), convert(response.get_links()));
	}

	private com.catware.service.model.LinksPaymentInitiation convert(LinksPaymentInitiation _links) {
		return new com.catware.service.model.LinksPaymentInitiation(convert(_links.getScaRedirect()));
	}

	private com.catware.service.model.HrefType convert(HrefType href) {
		if (href == null) {
			return null;
		}
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
	public MyResponse startSigningBasket(String basketid, String psuid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put("PSU-IP-Address", "");
		header.put(DNBConstants.PSUID, psuid);
		header.put(DNBConstants.TPPREDIRECTURI, RedirectUtil.getUrl(psuid, DNBConstants.GOMULTIPAY));
		return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/signing-baskets/" + basketid + "/authorisations", null, Constants.POST, header, "").request();		
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
