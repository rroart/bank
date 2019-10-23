package com.catware.factory;

import com.catware.account.AccountService;
import com.catware.account.impl.AccountServiceImpl;
import com.catware.consent.ConsentService;
import com.catware.consent.impl.ConsentServiceImpl;
import com.catware.constants.Constants;
import com.catware.payment.PaymentService;
import com.catware.payment.impl.PaymentServiceImpl;

public class ServiceFactory {
	public ConsentService getConsentService(String bank) {
		ConsentService service = null;
		if (Constants.DNB.equals(bank)) {
			service = new ConsentServiceImpl();
		}
		return service;
	}
	
	public AccountService getAccountService(String bank) {
		AccountService service = null;
		if (Constants.DNB.equals(bank)) {
			service = new AccountServiceImpl();
		}
		return service;
	}
	
	public PaymentService getPaymentService(String bank) {
		PaymentService service = null;
		if (Constants.DNB.equals(bank)) {
			service = new PaymentServiceImpl();
		}
		return service;
	}
}
