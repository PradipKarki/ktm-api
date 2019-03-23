package com.ktm.library.core.builder;

import static com.ktm.library.core.utils.CoreApiConstants.SEPARATOR;
import static com.ktm.library.core.utils.StringUtility.stringToList;
import static java.lang.String.format;

import com.google.api.services.youtube.model.Video;
import com.ktm.library.core.exception.JobException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentaryApiResponse implements ApiResponse<Video> {
  private static final Logger LOGGER = LoggerFactory.getLogger(DocumentaryApiResponse.class);

  @Autowired private YouTubeQueryBuilder youTubeQueryBuilder;

  @Override
  public List<Video> getApiData(String queryString) {
    List<String> youTubeDocumentaryIds = stringToList(queryString, SEPARATOR);
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info(Arrays.toString(youTubeDocumentaryIds.toArray()));
    }
    try {
      return Collections.unmodifiableList(
          youTubeQueryBuilder.getVideosByIds(youTubeDocumentaryIds));
    } catch (IOException e) {
      String errorMessage = "Job failed during fetching documentary videos from YouTube API.";
      LOGGER.error(format("Error Message %s %s", errorMessage, e.getMessage()), e);
      throw new JobException(errorMessage, e);
    }
  }
}
