package com.app.mutants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MutantsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantsApplication.class, args);
	}

}
