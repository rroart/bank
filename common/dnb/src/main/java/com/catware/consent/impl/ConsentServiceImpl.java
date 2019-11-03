package com.catware.consent.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import com.catware.consent.ConsentService;
import com.catware.constants.Constants;
import com.catware.constants.DNBConstants;
import com.catware.database.hibernate.Consent;
import com.catware.model.ConsentAccess;
import com.catware.model.CreateConsent;
import com.catware.model.CreateConsentResponse;
import com.catware.model.GetConsentResponse;
import com.catware.model.HrefType;
import com.catware.util.http.MyResponse;
import com.catware.util.http.dnb.DNBRequest;
import com.catware.util.json.JsonUtil;

public class ConsentServiceImpl extends ConsentService {

	@Override
	public MyResponse create(String psuid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Consent aConsent = Consent.find(psuid);
		if (aConsent != null) {
			MyResponse status = getStatus(aConsent.getConsentid(), aConsent.getPsuid());
			System.out.println("hereq");
			System.out.println(status.getBody());
			System.out.println("hereq");
			MyResponse getConsentResponse = get(aConsent.getConsentid(), aConsent.getPsuid());
			System.out.println("here2");
			System.out.println(getConsentResponse.getBody());
			System.out.println("here2");
			if (getConsentResponse.getCode() == 200) {
				GetConsentResponse consentStatus = JsonUtil.convert(getConsentResponse.getBody(), GetConsentResponse.class);
				if (consentStatus.getValidUntil().before(new Date())) {
					HrefType href = consentStatus.get_links().get(DNBConstants.SCAREDIRECT);
					String json = JsonUtil.convert(new com.catware.service.model.HrefType(href.getHref()));
					getConsentResponse.setBody(json);
					return getConsentResponse;
				}
			}
		}
		
		Map<String, String> header = new LinkedHashMap<>();
		header.put(DNBConstants.PSUID, psuid);
		header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
		String body = getBody(60);
		MyResponse response = new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/consents", null, Constants.POST, header, body).request();
		String json = response.getBody();
		System.out.println(json);
		if (response.getCode() == 201) {
			CreateConsentResponse createConstentResponse = JsonUtil.convert(json, CreateConsentResponse.class);
			String consentId = createConstentResponse.getConsentId();
			Consent consent = new Consent();
			consent.setPsuid(psuid);
			consent.setConsentid(consentId);
			consent.persist();
			String ajson = JsonUtil.convert(createConstentResponse.get_links().get(DNBConstants.SCAREDIRECT));
			response.setBody(ajson);
		}
		return response;
	}
	
	public MyResponse get(String consentid, String psuid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put(DNBConstants.PSUID, psuid);
		header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
		return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/consents/" + consentid, null, Constants.GET, header, null).request();
	}
	
	public MyResponse getStatus(String consentid, String psuid) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Map<String, String> header = new LinkedHashMap<>();
		header.put(DNBConstants.PSUID, psuid);
		header.put(DNBConstants.TPPREDIRECTURI, "http://0.0.0.0:3083");
		return new DNBRequest(DNBConstants.PSD2ENDPOINT, "v1/consents/" + consentid + "/status", null, Constants.GET, header, null).request();
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
				"    \"frequencyPerDay\": 300,\n" + 
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
