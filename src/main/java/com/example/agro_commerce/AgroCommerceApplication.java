package com.example.agro_commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.agro_commerce")
public class AgroCommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgroCommerceApplication.class, args);
	}
}
