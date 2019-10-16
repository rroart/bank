package com.catware.payment;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public abstract class PaymentService {

	public abstract String getPaymentsToApprove() throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException;
	
	public abstract String initiateNorwegianDomesticCreditTransfer(String ssn, String debtorAccount, String creditorAccount, String creditorName, String instructedAmount) throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException;
	
	public abstract String initiateNorwegianDomesticCreditTransferToSelf(String ssn, String debtorAccount, String creditorAccount, String instructedAmount);
	
	public abstract String getPaymentDetails(String paymentproduct, String paymentid);
	
	public abstract String getPaymentStatus(String paymentproduct, String paymentid);

	public abstract String initiateNewSigninngBasket(String ssn);

	public abstract String getSigninngBasket(String basketid);

	public abstract String startSigninngBasket(String basketid, String ssn);

	public abstract String getSigninngBasketStatus(String basketid);
}
