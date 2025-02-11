package com.hassansaleem.ukbiobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.hassansaleem.ukbiobank")
public class UkbiobankApplication {

	public static void main(String[] args) {
		SpringApplication.run(UkbiobankApplication.class, args);
	}

}
