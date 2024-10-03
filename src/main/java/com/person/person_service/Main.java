package com.person.person_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.person.person_service"})
public class Main {

	public static void main(String[] args) {
		System.out.println("Application Starting...");
		SpringApplication.run(Main.class, args);
	}

}
