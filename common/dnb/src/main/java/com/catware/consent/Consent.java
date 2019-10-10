package com.catware.consent;

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

import com.catware.constants.Constants;
import com.catware.util.http.MyRequest;

public class Consent {

	public String create(String ssn) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		String requestId = UUID.randomUUID().toString();
		Map<String, String> header = new LinkedHashMap<>();
		header.put("Content-Type", "application/json; utf-8");
		header.put("Accept", "application/json");
		header.put("X-Request-ID", requestId);
		header.put("PSU-ID", ssn);
		header.put("TPP-Redirect-URI", "http://0.0.0.0:3083");
		String body = getBody(60);
		return new MyRequest(Constants.psd2endpoint, "v1/consents", "POST", header, body).request();
	}
	
	public String getBody(int validDays) {
		LocalDate date = LocalDate.now();
		date = date.plusDays(validDays);
		String dateString = date.getYear() + "-" + myformat(date.getMonthValue()) + "-" + myformat(date.getDayOfMonth());
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
		return jsonInputString;
	}

	private String myformat(int num) {
		return String.format("%02d", num);
	}
}
