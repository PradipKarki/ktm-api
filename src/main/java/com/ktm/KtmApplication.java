package com.ktm;

import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
@EnableJpaRepositories
public class KtmApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(KtmApplication.class);

  public static void main(String[] args) {
    LOGGER.info("Initializing KTM TIMES DOT COM application, current time: {}", Instant.now());
    SpringApplication.run(KtmApplication.class, args);
    LOGGER.info("Starting KTM TIMES DOT COM application, current time: {}", Instant.now());
  }
}
