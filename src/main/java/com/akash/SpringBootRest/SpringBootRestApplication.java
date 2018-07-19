package com.akash.SpringBootRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(scanBasePackages = { "com.akash" })
@PropertySources({ @PropertySource("classpath:application.properties") })
public class SpringBootRestApplication {
	
	public static final String API_V1 = "/api/v1";

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApplication.class, args);
	}
}
