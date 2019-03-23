package com.ktm.library.core.builder;

import static java.lang.String.format;

import com.google.api.services.youtube.model.SearchResult;
import com.ktm.library.core.exception.JobException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class YouTubeApiResponse implements ApiResponse<SearchResult> {
  private static final Logger LOGGER = LoggerFactory.getLogger(TwitterApiResponse.class);

  @Autowired private YouTubeQueryBuilder youTubeQueryBuilder;

  @Override
  public List<SearchResult> getApiData(String queryString) {
    try {
      return Collections.unmodifiableList(youTubeQueryBuilder.getSearchResults());
    } catch (IOException e) {
      String errorMessage = "Job failed during fetching videos from YouTube API.";
      LOGGER.error(format("Error Message %s %s", errorMessage, e.getMessage()), e);
      throw new JobException(errorMessage, e);
    }
  }
}
