package com.catware.account;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public abstract class AccountService {

	public abstract String getAccount(String consentid) throws UnrecoverableKeyException, KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException;

	public abstract String getBalance(String consentid, String accid) throws UnrecoverableKeyException,
			KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException;

	
}

