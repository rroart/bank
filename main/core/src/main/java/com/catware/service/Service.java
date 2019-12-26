package com.catware.service;

import com.catware.http.Customer;
import com.catware.factory.ServiceFactory;
import com.catware.account.AccountService;
import com.catware.consent.ConsentService;
import com.catware.payment.PaymentService;
import com.catware.service.model.PaymentInitiationNorwayPostRequest;
import com.catware.service.model.PaymentInitiationNorwayPostRequests;
import java.security.UnrecoverableKeyException;
import java.security.KeyManagementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.HashSet;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.catware.util.http.MyResponse;

@CrossOrigin
@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class Service implements CommandLineRunner {
	
	@RequestMapping(value = "/" + "hello",
			method = RequestMethod.POST)
	public String configDb(@RequestBody Object param)
			throws Exception {
		System.out.println("hello");
		return("hello");
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Service.class, args);
	}

	@Override
	public void run(String... args) throws InterruptedException, IOException {        
		System.out.println("Password property " + System.getProperty("password"));
		if (System.getProperty("password") == null) {
			System.out.println("No password property set");
		}
		System.out.println("Keyfile property " + System.getProperty("keyfile"));
		if (System.getProperty("keyfile") == null) {
			System.out.println("No keyfile property set");
		}
	}

	@GetMapping(path = "/")
    public ResponseEntity healthCheck() throws Exception {
		MyResponse response = new MyResponse(200, "Hello");
		return aResponse(response);
	}

	@PostMapping(path = "/consents")
    public ResponseEntity createConsent(@RequestBody Customer customer) throws Exception {
		ConsentService service = new ServiceFactory().getConsentService(customer.getBank());
		MyResponse response = service.create(customer.getPsuid());
		return aResponse(response);
	}

	@GetMapping(path = "/accounts/{bank}/{psuid}")
    public MyResponse getAccount(@PathVariable("bank") String bank, @PathVariable("psuid") String psuid) throws Exception {
		AccountService service = new ServiceFactory().getAccountService(bank);
		MyResponse response = service.getAccount(psuid, true);
		System.out.println(response);
		return response;
    }

	@GetMapping(path = "/accounts/{bank}/{psuid}/{accid}/transactions")
    public MyResponse getAccountTransactions(@PathVariable("bank") String bank, @PathVariable("psuid") String psuid, @PathVariable("accid") String accid) throws Exception {
		AccountService service = new ServiceFactory().getAccountService(bank);
		MyResponse response = service.getAccountTransactions(psuid, accid);
		System.out.println(response);
		return response;
    }

	@PostMapping(path = "/accounts/pay")
    public MyResponse postPay(@RequestBody PaymentInitiationNorwayPostRequest payment) throws Exception {
		PaymentService service = new ServiceFactory().getPaymentService(payment.getBank());
		MyResponse response = service.initiateNorwegianDomesticCreditTransfer(payment);
		System.out.println(response);
		return response;
    }
	
	@PostMapping(path = "/accounts/payments")
    public MyResponse postPay(@RequestBody PaymentInitiationNorwayPostRequests payments) throws Exception {
		PaymentService service = new ServiceFactory().getPaymentService(payments.getPayments().get(0).getBank());
		MyResponse response = service.initiateNorwegianDomesticCreditTransfers(payments);
		System.out.println(response);
		return response;
    }
	
	public ResponseEntity aResponse(MyResponse response) {
		System.out.println(response);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("content-type", "application/json");
		System.out.println("Resp" + HttpStatus.valueOf(response.getCode()) + " " + response.getCode());
		return new ResponseEntity<String>(response.getBody(), responseHeaders, HttpStatus.valueOf(response.getCode()));
	}
}
