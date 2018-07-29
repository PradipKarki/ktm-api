package com.ktm;

import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
public class KtmApplication {
  private static final Logger logger = LoggerFactory.getLogger(KtmApplication.class);

  public static void main(String[] args) {
    logger.info("Starting KTM TIMES DOT COM application, current time: {}", Instant.now());
    SpringApplication.run(KtmApplication.class, args);
    logger.info("Shutting down application, current time: {}", Instant.now());
  }
}
