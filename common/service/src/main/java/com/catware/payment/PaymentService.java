package com.catware.payment;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import com.catware.util.http.MyResponse;

public abstract class PaymentService {

	public abstract MyResponse getPaymentsToApprove() throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException;
	
	public abstract MyResponse initiateNorwegianDomesticCreditTransfer(String psuid, String debtorAccount, String creditorAccount, String creditorName, String instructedAmount) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException;
	
	public abstract MyResponse initiateNorwegianDomesticCreditTransferToSelf(String psuid, String debtorAccount, String creditorAccount, String instructedAmount);
	
	public abstract MyResponse getPaymentDetails(String paymentproduct, String paymentid);
	
	public abstract MyResponse getPaymentStatus(String paymentproduct, String paymentid);

	public abstract MyResponse initiateNewSigninngBasket(String psuid);

	public abstract MyResponse getSigninngBasket(String basketid);

	public abstract MyResponse startSigninngBasket(String basketid, String psuid);

	public abstract MyResponse getSigninngBasketStatus(String basketid);
}
