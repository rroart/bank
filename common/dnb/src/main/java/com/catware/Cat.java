package com.catware;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyFactory;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import com.catware.consent.impl.ConsentServiceImpl;
import com.catware.util.resource.ResourceUtil;
import com.catware.util.ssl.SSLUtils;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Cat {
	String psd2endpoint = "https://sandboxapi.psd.dnb.no/";
	String certificateFile = "certificate.pem";
	String keyFile = "private.pkcs8.key";

	void method() throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException, CertificateException, IOException, InvalidKeySpecException {

		//From there you can configure the connection like this (if this is what you're using):


		X509Certificate certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(new ResourceUtil().readResourceFile(certificateFile)));

		PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(new ResourceUtil().readResourceFile(keyFile)));
		System.out.println("Private Key > Format: " + privateKey.getFormat());
		System.out.println("Private Key > Algorithm: " + privateKey.getAlgorithm());

		String encodedCertificate = Base64.getEncoder().encodeToString(certificate.getEncoded());

		//con.setDoOutput(true);


	}

	void methodold() throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException, CertificateException, IOException {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		SSLContext sslContext = new SSLUtils().getSSLContext(keyStore);

		//From there you can configure the connection like this (if this is what you're using):
		URL url = new URL(psd2endpoint + "v1/consents"); 
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		if (connection instanceof HttpsURLConnection) {
			System.out.println("HTTPS");
			((HttpsURLConnection)connection)
			.setSSLSocketFactory(sslContext.getSocketFactory());
		}
		HttpsURLConnection con = connection;
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("X-Request-ID", "22FD6960-46A4-49FB-BD0B-E78A018C8DC0");
		con.setRequestProperty("PSU-ID", "31125464346");
		con.setRequestProperty("TPP-Redirect-URI", "http://0.0.0.0:3083");
		con.setDoOutput(true);
		try(OutputStream os = con.getOutputStream()) {
			byte[] input = new ConsentServiceImpl().getBody(60).getBytes("utf-8");
			os.write(input, 0, input.length);           
		}
		try(BufferedReader br = new BufferedReader(
				new InputStreamReader(con.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println(response.toString());
		}
	}
}
