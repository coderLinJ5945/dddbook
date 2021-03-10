package com.dddbook.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = { "com.dddbook.bank.*" }
)
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

}
