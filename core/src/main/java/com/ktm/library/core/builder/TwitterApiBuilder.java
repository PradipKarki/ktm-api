package com.ktm.library.core.builder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

@Component
@RefreshScope
public class TwitterApiBuilder implements ApiBuilder<Twitter> {

  @Value("${twitter4j.debug}")
  private String debug;

  @Value("${twitter4j.oauth.consumerKey}")
  private String consumerKey;

  @Value("${twitter4j.oauth.consumerSecret}")
  private String consumerSecret;

  @Value("${twitter4j.oauth.accessToken}")
  private String accessToken;

  @Value("${twitter4j.oauth.accessTokenSecret}")
  private String accessTokenSecret;

  private Configuration buildConfiguration() {
    ConfigurationBuilder confBuilder = new ConfigurationBuilder();
    confBuilder
        .setDebugEnabled(Boolean.parseBoolean(debug))
        .setOAuthConsumerKey(consumerKey)
        .setOAuthConsumerSecret(consumerSecret)
        .setOAuthAccessToken(accessToken)
        .setOAuthAccessTokenSecret(accessTokenSecret);
    return confBuilder.build();
  }

  @Override
  public Twitter getInstance() {
    TwitterFactory twitterFactory = new TwitterFactory(buildConfiguration());
    return twitterFactory.getInstance();
  }
}
