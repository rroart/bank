package com.catware.service;

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

import java.util.HashMap;
import java.util.HashSet;
import java.io.IOException;
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

}
