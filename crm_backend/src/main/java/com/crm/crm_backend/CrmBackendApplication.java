package com.crm.crm_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.crm.crm_backend.repository")
@EntityScan(basePackages = "com.crm.crm_backend.entity")
public class CrmBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(CrmBackendApplication.class, args);
	}
}
