package com.catware.util.http.dnb;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.UUID;

import com.catware.constants.DNBConstants;
import com.catware.util.http.MyRequest;
import com.catware.util.http.MyResponse;

public class DNBRequest {
	private String baseurl;
	
	private String path;
	
	private String method;
	
	private Map<String, String> header;
	
	private String body;
	
	public DNBRequest(String baseurl, String path, String method, Map<String, String> header, String body) {
		this.baseurl = baseurl;
		this.path = path;
		this.method = method;
		this.header = header;
		this.body = body;
	}

	public MyResponse request() throws IOException, KeyStoreException, UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, CertificateException {
		String requestId = UUID.randomUUID().toString();
		header.put(DNBConstants.CONTENTTYPE, "application/json; utf-8");
		header.put(DNBConstants.ACCEPT, "application/json");
		header.put(DNBConstants.XREQUESTID, requestId);
		return new MyRequest(baseurl, path, method, header, body).request();
	}
	
}
