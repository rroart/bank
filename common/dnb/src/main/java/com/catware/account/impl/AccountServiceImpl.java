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
import java.util.Map;
import java.util.UUID;

import com.catware.account.AccountService;
import com.catware.constants.Constants;
import com.catware.util.http.MyRequest;

public class AccountServiceImpl extends AccountService {
	
	@Override
	public String getAccount(String consentid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		String requestId = UUID.randomUUID().toString();
		Map<String, String> header = new LinkedHashMap<>();
		header.put("Content-Type", "application/json; utf-8");
		header.put("Accept", "application/json");
		header.put("X-Request-ID", requestId);
		header.put("Consent-ID", consentid);
		header.put("TPP-Redirect-URI", "http://0.0.0.0:3083");
		return new MyRequest(Constants.psd2endpoint, "v1/accounts", "GET", header, null).request();
	}
	
	@Override
	public String getAccountDetails(String consentid, String accid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		String requestId = UUID.randomUUID().toString();
		Map<String, String> header = new LinkedHashMap<>();
		header.put("Content-Type", "application/json; utf-8");
		header.put("Accept", "application/json");
		header.put("X-Request-ID", requestId);
		header.put("Consent-ID", consentid);
		header.put("TPP-Redirect-URI", "http://0.0.0.0:3083");
		return new MyRequest(Constants.psd2endpoint, "v1/accounts/" + accid, "GET", header, null).request();
	}
	
	@Override
	public String getBalance(String consentid, String accid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		String requestId = UUID.randomUUID().toString();
		Map<String, String> header = new LinkedHashMap<>();
		header.put("Content-Type", "application/json; utf-8");
		header.put("Accept", "application/json");
		header.put("X-Request-ID", requestId);
		header.put("Consent-ID", consentid);		
		return new MyRequest(Constants.psd2endpoint, "v1/accounts/" + accid + "/balances", "GET", header, null).request();
	}

	@Override
	public String getAccountTransactions(String consentid, String accid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		String requestId = UUID.randomUUID().toString();
		Map<String, String> header = new LinkedHashMap<>();
		header.put("Content-Type", "application/json; utf-8");
		header.put("Accept", "application/json");
		header.put("X-Request-ID", requestId);
		header.put("Consent-ID", consentid);
		header.put("TPP-Redirect-URI", "http://0.0.0.0:3083");
		return new MyRequest(Constants.psd2endpoint, "v1/accounts/" + accid + "/transactions", "GET", header, null).request();
	}
	
}
