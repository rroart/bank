package com.catware.util.ssl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Map;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import com.catware.util.resource.PropertyUtil;
import com.catware.util.resource.ResourceUtil;

public class SSLUtils {

	public SSLContext getSSLContext(KeyStore keyStore) throws KeyStoreException, FileNotFoundException, IOException,
	NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {
		Map<String, Object> map = new PropertyUtil().getProps();
		String password = (String) map.get("password");
		System.out.println("password");
		keyStore.load(null, password.toCharArray());
		//InputStream fis0 = new FileInputStream(/*map.get("keyfile")*/ "/home/roart/src/bank/key/private.p12");
	    //String text = getString(fis0);
	    //System.out.println("text" + text.length());
		byte[] keyfile = (byte[]) map.get("keyfile");
		System.out.println("kf" + keyfile.length);
		//System.out.println(keyfile.equals(text));
		
		FileOutputStream outputStream = new FileOutputStream("/tmp/private0.p12");
	    byte[] strToBytes = keyfile;
	    outputStream.write(strToBytes);	 
	    outputStream.close();
	    outputStream.flush();
	    System.out.println("written" + strToBytes.length);
	    Files.write(Paths.get("/tmp/private.p12"), strToBytes);
	    System.out.println("written" + strToBytes.length);
	    
		FileOutputStream fos = new FileOutputStream("/tmp/private.p12");
		keyStore.store(fos, password.toCharArray());
		//fis0 = new ByteArrayInputStream(keyfile.getBytes());		 
		//text = getString(fis0);
		//System.out.println(keyfile.equals(text));
		InputStream fis = new ByteArrayInputStream(keyfile);		 
		//fis0 = new FileInputStream(/*map.get("keyfile")*/ "/home/roart/src/bank/key/private.p12");
		keyStore.load(fis, password.toCharArray());
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(keyStore, password.toCharArray());
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(kmf.getKeyManagers(), null, null);
		return sslContext;
	}

	private String getString(InputStream fis) throws IOException, UnsupportedEncodingException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	    int nRead;
	    byte[] data = new byte[1024];
	    while ((nRead = fis.read(data, 0, data.length)) != -1) {
	        buffer.write(data, 0, nRead);
	    }
	 
	    buffer.flush();
	    byte[] byteArray = buffer.toByteArray();
	         
	    return  new String(byteArray, StandardCharsets.UTF_8);
	}

	public X509TrustManager getTrustManager(KeyStore keyStore) throws NoSuchAlgorithmException, KeyStoreException {
		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
				TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(keyStore);
		TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
		if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
			throw new IllegalStateException("Unexpected default trust managers:"
					+ Arrays.toString(trustManagers));
		}
		X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
		return trustManager;
	}

}
