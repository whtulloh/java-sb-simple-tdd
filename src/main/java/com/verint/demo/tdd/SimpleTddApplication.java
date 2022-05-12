package com.verint.demo.tdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SimpleTddApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleTddApplication.class, args);
	}

}
