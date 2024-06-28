package com.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.repo")
@ComponentScan(basePackages = "com.*")
@EntityScan(basePackages = "com.entity")
public class OnlineFoodDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineFoodDeliveryApplication.class, args);
	}

}
