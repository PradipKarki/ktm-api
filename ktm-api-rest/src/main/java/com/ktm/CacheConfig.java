package com.ktm;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

  private static Caffeine<Object, Object> caffeineCacheBuilder() {
    return Caffeine.newBuilder()
        .initialCapacity(100)
        .maximumSize(150L)
        .expireAfterAccess(5L, TimeUnit.MINUTES)
        .weakKeys()
        .recordStats();
  }

  @Bean
  public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager("AIRCRAFTS", "SECOND_CACHE");
    cacheManager.setAllowNullValues(false);
    cacheManager.setCaffeine(caffeineCacheBuilder());
    return cacheManager;
  }
}
