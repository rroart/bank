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
import com.catware.constants.DNBConstants;
import com.catware.util.http.dnb.DNBRequest;

public class AccountServiceImpl extends AccountService {
	
	@Override
	public String getAccount(String consentid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put(DNBConstants.CONSENTID, consentid);
		header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
		return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/accounts", Constants.GET, header, null).request();
	}
	
	@Override
	public String getAccountDetails(String consentid, String accid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		String requestId = UUID.randomUUID().toString();
		Map<String, String> header = new LinkedHashMap<>();
		header.put(DNBConstants.CONTENTTYPE, "application/json; utf-8");
		header.put(DNBConstants.ACCEPT, "application/json");
		header.put(DNBConstants.XREQUESTID, requestId);
		header.put(DNBConstants.CONSENTID, consentid);
		header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
		return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/accounts/" + accid, Constants.GET, header, null).request();
	}
	
	@Override
	public String getBalance(String consentid, String accid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put(DNBConstants.CONSENTID, consentid);		
		return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/accounts/" + accid + "/balances", Constants.GET, header, null).request();
	}

	@Override
	public String getAccountTransactions(String consentid, String accid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put(DNBConstants.CONSENTID, consentid);
		header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
		return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/accounts/" + accid + "/transactions", Constants.GET, header, null).request();
	}
	
}
