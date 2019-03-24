package com.ktm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.ktm.library.core.KtmLibConfig;
import java.time.Instant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(KtmLibConfig.class)
public class KtmApplicationTests {
  private static final Logger LOGGER = LoggerFactory.getLogger(KtmApplicationTests.class);
  @Autowired private ApplicationContext context;

  @Test
  public void contextLoads() {
    if (LOGGER.isInfoEnabled())
      LOGGER.info("TESTING KTM TIMES DOT COM application, current time: {}", Instant.now());
    assertThat(context, is(notNullValue()));
  }
}
