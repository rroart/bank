package com.catware.account.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.catware.account.AccountService;
import com.catware.constants.Constants;
import com.catware.constants.DNBConstants;
import com.catware.database.hibernate.Consent;
import com.catware.model.AccountDetails;
import com.catware.model.AccountList;
import com.catware.model.Amount;
import com.catware.model.Balance;
import com.catware.model.GetAccountBalancesResponse;
import com.catware.util.http.MyResponse;
import com.catware.util.http.dnb.DNBRequest;
import com.catware.util.json.JsonUtil;

public class AccountServiceImpl extends AccountService {

	@Override
	public MyResponse getAccount(String psuid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Consent aConsent = Consent.find(psuid);
		if (aConsent != null) {
			String consentid = aConsent.getConsentid();
			Map<String, String> header = new LinkedHashMap<>();
			header.put(DNBConstants.CONSENTID, consentid);
			header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
			MyResponse response = new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/accounts", Constants.GET, header, null).request();
			String json = response.getBody();
			System.out.println(json);
			AccountList accounts = JsonUtil.convert(json, AccountList.class);
			for (AccountDetails account : accounts.getAccounts()) {
				String bban = account.getBban();
				MyResponse details = getBalance(psuid, bban);
				if (details.getCode() == 200) {
					GetAccountBalancesResponse detail = JsonUtil.convert(details.getBody(), GetAccountBalancesResponse.class);
					List<Balance> balances = detail.getBalances();
					for (Balance balance : balances) {
						if ("authorized".equals(balance.getBalanceType())) {
							//account.
						}
					}
				}
			}
			response.setBody(JsonUtil.convert(accounts));
			return response;
		} else {
			return new MyResponse(500, Constants.PSUWITHOUTCONSENT);
		}
	}

	@Override
	public MyResponse getAccountDetails(String psuid, String accid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Consent aConsent = Consent.find(psuid);
		if (aConsent != null) {
			String consentid = aConsent.getConsentid();
			String requestId = UUID.randomUUID().toString();
			Map<String, String> header = new LinkedHashMap<>();
			header.put(DNBConstants.CONTENTTYPE, "application/json; utf-8");
			header.put(DNBConstants.ACCEPT, "application/json");
			header.put(DNBConstants.XREQUESTID, requestId);
			header.put(DNBConstants.CONSENTID, consentid);
			header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
			return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/accounts/" + accid, Constants.GET, header, null).request();
		} else {
			return new MyResponse(500, Constants.PSUWITHOUTCONSENT);
		}
	}

	@Override
	public MyResponse getBalance(String psuid, String accid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Consent aConsent = Consent.find(psuid);
		if (aConsent != null) {
			String consentid = aConsent.getConsentid();
			Map<String, String> header = new LinkedHashMap<>();
			header.put(DNBConstants.CONSENTID, consentid);		
			return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/accounts/" + accid + "/balances", Constants.GET, header, null).request();
		} else {
			return new MyResponse(500, Constants.PSUWITHOUTCONSENT);
		}
	}

	@Override
	public MyResponse getAccountTransactions(String psuid, String accid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Consent aConsent = Consent.find(psuid);
		if (aConsent != null) {
			String consentid = aConsent.getConsentid();
			Map<String, String> header = new LinkedHashMap<>();
			header.put(DNBConstants.CONSENTID, consentid);
			header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
			return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/accounts/" + accid + "/transactions", Constants.GET, header, null).request();
		} else {
			return new MyResponse(500, Constants.PSUWITHOUTCONSENT);
		}
	}

}
