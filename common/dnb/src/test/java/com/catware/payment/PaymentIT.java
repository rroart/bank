package com.catware.payment;

import static org.junit.Assert.*;

import org.junit.Test;

import com.catware.consent.impl.ConsentServiceImpl;
import com.catware.payment.impl.PaymentServiceImpl;
import com.catware.service.model.AccountReference;
import com.catware.service.model.Amount;
import com.catware.service.model.PaymentInitiationNorwayPostRequest;
import com.catware.util.http.MyResponse;

public class PaymentIT {

	private static final String psuid = "31125464346";

	@Test
    public void test() throws Exception {
		PaymentInitiationNorwayPostRequest payment = new PaymentInitiationNorwayPostRequest();
		payment.setBank("DNB");
		payment.setPsuid(psuid);
		payment.setCreditorName("Hans Hansen");
		payment.setCreditorAccount(new AccountReference("05404649541", null));
		payment.setDebtorAccount(new AccountReference("12043175449", null));
		Amount instructedAmount = new Amount("42", "NOK");
		payment.setInstructedAmount(instructedAmount);
		MyResponse response = new PaymentServiceImpl().initiateNorwegianDomesticCreditTransfer(payment);
		System.out.println(response);
	}
}
