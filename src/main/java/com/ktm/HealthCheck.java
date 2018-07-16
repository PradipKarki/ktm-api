package com.ktm;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

  private static boolean isError() {
    // Our logic to check healths
    return Boolean.TRUE;
  }

  @Override
  public Health health() {
    boolean isError = isError(); // perform some specific health check
    if (!isError) {
      //todo need to add database system information here
      return Health.down()
                   .withDetail("Error Code", Boolean.FALSE).build();
    }
    return Health.up().build();
  }
}
