package com.ktm.library.core.builder;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class YouTubeApiBuilder implements ApiBuilder<YouTube> {

  @Value("${YouTube.AppName}")
  private String youtubeSpringApp;

  @Override
  public YouTube getInstance() {
    return new YouTube.Builder(
            new NetHttpTransport(),
            new JacksonFactory(),
            (request) -> {
              /* empty block */
            })
        .setApplicationName(youtubeSpringApp)
        .build();
  }
}
