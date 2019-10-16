package com.catware.consent;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public abstract class ConsentService {

	public abstract String create(String ssn) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException;

	public abstract String get(String consentid, String ssn) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException,
	NoSuchAlgorithmException, CertificateException, IOException;

	public abstract String getStatus(String consentid, String ssn) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException,
	NoSuchAlgorithmException, CertificateException, IOException;

}
