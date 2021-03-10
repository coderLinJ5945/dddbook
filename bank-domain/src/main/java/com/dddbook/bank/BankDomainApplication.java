package com.dddbook.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = { "com.dddbook.bank.*" }
)
public class BankDomainApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankDomainApplication.class, args);
	}

}
