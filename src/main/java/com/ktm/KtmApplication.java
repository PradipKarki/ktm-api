package com.ktm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KtmApplication {

	public static void main(String[] args) {
		SpringApplication.run(KtmApplication.class, args);
	}

}
