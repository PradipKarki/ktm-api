package com.ktm.twitter.builder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

@Service
@RefreshScope
public class TwitterBuilder {

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
  @Value("${App.English.Language}")
  private String englishLanguage;

  private Configuration getConfiguration() {
    ConfigurationBuilder confBuilder = new ConfigurationBuilder();
    confBuilder.setDebugEnabled(Boolean.parseBoolean(debug))
               .setOAuthConsumerKey(consumerKey)
               .setOAuthConsumerSecret(consumerSecret)
               .setOAuthAccessToken(accessToken)
               .setOAuthAccessTokenSecret(accessTokenSecret);
    return confBuilder.build();
  }

  public Twitter getInstance() {
    TwitterFactory twitterFactory = new TwitterFactory(getConfiguration());
    return twitterFactory.getInstance();
  }

  public QueryResult getQueryResult(String queryString) throws TwitterException {
    Query query = new Query(queryString);
    query.lang(englishLanguage);
    query.setCount(100);
    return getInstance().search(query);
  }

}
