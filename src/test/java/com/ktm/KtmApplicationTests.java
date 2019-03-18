package com.ktm;

import java.time.Instant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KtmApplicationTests {
  private static final Logger LOGGER = LoggerFactory.getLogger(KtmApplicationTests.class);

  @Test
  public void contextLoads() {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("TESTING KTM TIMES DOT COM application, current time: {}", Instant.now());
    }
  }
}
