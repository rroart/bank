package com.catware.account;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import com.catware.util.http.MyResponse;

public abstract class AccountService {

	public abstract MyResponse getAccount(String psuid, boolean withBalance) throws UnrecoverableKeyException, KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException;

	public abstract MyResponse getBalance(String psuid, String accid) throws UnrecoverableKeyException,
			KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException;

	public abstract MyResponse getAccountTransactions(String psuid, String accid) throws UnrecoverableKeyException,
			KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException;
	
	public abstract MyResponse getAccountDetails(String psuid, String accid, boolean withBalance) throws UnrecoverableKeyException,
			KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException;
	
}

