package com.ktm;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

  @Override
  public Health health() {
    int errorCode = check(); // perform some specific health check
    if (errorCode != 1) {
      return Health.down()
                   .withDetail("Error Code", errorCode).build();
    }
    return Health.up().build();
  }

  public int check() {
    // Our logic to check health
    return 1;
  }
}
