package com.catware.service;

import com.catware.http.Customer;
import com.catware.factory.ServiceFactory;
import com.catware.account.AccountService;
import com.catware.consent.ConsentService;
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

	@PostMapping(path = "/accounts/pay")
    public String addMemberV1(@RequestBody Customer c) {
		System.out.println("strx");
        return "helloworld";
    }
	
	public ResponseEntity aResponse(MyResponse response) {
		System.out.println(response);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("content-type", "application/json");

		return new ResponseEntity<String>(response.getBody(), responseHeaders, HttpStatus.valueOf(response.getCode()));
	}
}
