package com.ktm.library.core.builder;

import com.ktm.library.core.exception.ApiException;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Component
@RefreshScope
public class TwitterApiResponse implements ApiResponse<Status> {
  private static final Logger LOGGER = LoggerFactory.getLogger(TwitterApiResponse.class);

  @Autowired private ApiBuilder<Twitter> twitterApiBuilder;

  @Value("${Twitter.Language}")
  private String language;

  @Value("${Twitter.Query.Nepal}")
  private String queryNepal;

  @Value("${Twitter.Query.Everest}")
  private String queryEverest;

  @Value("${Twitter.Query.Kathmandu}")
  private String queryKathmandu;

  @Value("${Twitter.Query.Custom}")
  private String queryCustom;

  @Override
  public List<Status> getApiData(String queryString) {
    Query query = buildQuery();
    try {
      return Collections.unmodifiableList(
          twitterApiBuilder.getInstance().search(query).getTweets());
    } catch (TwitterException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ApiException(e.getMessage(), e);
    }
  }

  private Query buildQuery() {
    Query query = new Query(queryCustom);
    query.lang(language);
    query.setCount(100);
    return query;
  }
}
