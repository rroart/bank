package com.catware.consent.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import com.catware.consent.ConsentService;
import com.catware.constants.Constants;
import com.catware.constants.DNBConstants;
import com.catware.model.ConsentAccess;
import com.catware.model.CreateConsent;
import com.catware.model.CreateConsentResponse;
import com.catware.util.http.dnb.DNBRequest;
import com.catware.util.json.JsonUtil;

public class ConsentServiceImpl extends ConsentService {

	@Override
	public String create(String ssn) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put(DNBConstants.PSUID, ssn);
		header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
		String body = getBody(60);
		String json = new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/consents", Constants.POST, header, body).request();
		CreateConsentResponse createConstentResponse = JsonUtil.convert(json, CreateConsentResponse.class);
		return createConstentResponse.getConsentId();
	}
	
	@Override
	public String get(String consentid, String ssn) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put(DNBConstants.PSUID, ssn);
		header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
		return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/consents/" + consentid, Constants.GET, header, null).request();
	}
	
	@Override
	public String getStatus(String consentid, String ssn) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put(DNBConstants.PSUID, ssn);
		header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
		return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/consents/" + consentid + "/status", Constants.GET, header, null).request();
	}
	
	public String getBody(int validDays) {
		LocalDate date = LocalDate.now();
		date = date.plusDays(validDays);
		String dateString = date.getYear() + "-" + myformat(date.getMonthValue()) + "-" + myformat(date.getDayOfMonth());
		ConsentAccess consentAccess = new ConsentAccess();
		consentAccess.setAccounts(new ArrayList<>());
		consentAccess.setBalances(new ArrayList<>());
		consentAccess.setTransactions(new ArrayList<>());
		CreateConsent createConsent = new CreateConsent();
		createConsent.setAccess(consentAccess);
		createConsent.setCombinedServiceIndicator(false);
		createConsent.setFrequencyPerDay(3);
		createConsent.setRecurringIndicator(true);
		createConsent.setValidUntil(date);
		String jsonInputString = "{\n" + 
				"    \"validUntil\": \"" + dateString + "\",\n" + 
				"    \"frequencyPerDay\": 3,\n" + 
				"    \"access\": {\n" + 
				"        \"balances\": [],\n" + 
				"        \"accounts\": [],\n" + 
				"        \"transactions\": []\n" + 
				"    },\n" + 
				"    \"recurringIndicator\": true,\n" + 
				"    \"combinedServiceIndicator\": false\n" + 
				"}";
		jsonInputString = JsonUtil.convert(createConsent);
		System.out.println(jsonInputString);
		return jsonInputString;
	}

	private String myformat(int num) {
		return String.format("%02d", num);
	}
}
