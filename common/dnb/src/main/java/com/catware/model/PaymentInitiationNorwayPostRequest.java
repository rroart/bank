package com.catware.model;

import java.time.LocalDate;

public class PaymentInitiationNorwayPostRequest {

	private AccountReference creditorAccount;
	
	private Address credtorAddress;
	
	private String creditorAgent;
	
	private String creditorName;
	
	private AccountReference debtorAccount;
	
	private String endToEndIdentification;
	
	private Amount instructedAmount;
	
	private String purposeCode;
	
	private RemittanceInformationStructured remittanceInformationStructured;
	
	private String remittanceInformationUnstructured;
	
	private LocalDate requestedExecutionDate;	
	
	private String ultimateCreditor;
	
	private String ultimateDebtor;
}
