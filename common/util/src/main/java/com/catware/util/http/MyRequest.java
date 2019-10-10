package com.catware.util.http;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.stream.Collectors;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import com.catware.util.ssl.SSLUtils;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyRequest {
	
	private String baseurl;
	
	private String path;
	
	private String method;
	
	private Map<String, String> header;
	
	private String body;
	
	public MyRequest(String baseurl, String path, String method, Map<String, String> header, String body) {
		this.baseurl = baseurl;
		this.path = path;
		this.method = method;
		this.header = header;
		this.body = body;
	}
	
	public OkHttpClient getOkHttpClient(SSLContext sslContext, X509TrustManager trustManager) {
		OkHttpClient httpClient = new OkHttpClient.Builder()
				.sslSocketFactory(sslContext.getSocketFactory(), trustManager)
				.build();
		return httpClient;
	}

	public String request() throws IOException, KeyStoreException, UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, CertificateException {
		Headers.Builder headerBuilder = new Headers.Builder();
		Map<String, String> normalizedHeaders = header
				.entrySet()
				.stream()
				.collect(Collectors.toMap(entry -> entry.getKey(), Map.Entry::getValue));
		normalizedHeaders.forEach(headerBuilder::add);
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		System.out.println(body);
		RequestBody requestBody = RequestBody.create(JSON, body);

		URL baseUrl = new URL(baseurl);
		URL url = new URL(baseUrl, path);

		Request request = new Request.Builder().url(url)
				.headers(headerBuilder.build())
				.post(requestBody)
				.build();
		
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		SSLContext sslContext = new SSLUtils().getSSLContext(keyStore);
		X509TrustManager trustManager = new SSLUtils().getTrustManager(keyStore);

		OkHttpClient httpClient = getOkHttpClient(sslContext, trustManager);
		
		Response response = httpClient.newCall(request).execute();
		
		System.out.println("---------- Request ----------");
		System.out.println(request.url());
		System.out.println(request.headers());
		System.out.println(request.body());

		System.out.println("---------- Response ----------");
		String body = response.body().string();
		System.out.println(response.code());
		System.out.println(response.headers().toString());
		System.out.println(body);

		System.out.println("\n------------------------------------------\n");

		return body;
	}
}
