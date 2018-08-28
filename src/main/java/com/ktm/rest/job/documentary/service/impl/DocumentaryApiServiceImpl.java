package com.ktm.rest.job.documentary.service.impl;

import static com.ktm.utils.StringUtility.SEPARATOR;
import static com.ktm.utils.StringUtility.stringToList;

import com.google.api.services.youtube.model.Video;
import com.ktm.exception.JobException;
import com.ktm.rest.documentary.model.Documentary;
import com.ktm.rest.documentary.repository.DocumentaryRepository;
import com.ktm.rest.job.documentary.service.DocumentaryApiService;
import com.ktm.rest.job.youtube.builder.YouTubeApiBuilder;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentaryApiServiceImpl implements DocumentaryApiService {
  private static final Logger logger = LoggerFactory.getLogger(DocumentaryApiServiceImpl.class);

  @Autowired private YouTubeApiBuilder youTubeApiBuilder;
  @Autowired private DocumentaryRepository documentaryRepository;

  @Override
  public List<Video> getDataFromAPI(String queryString) {
    List<String> youTubeDocumentaryIds = stringToList(queryString, SEPARATOR);
    if (logger.isInfoEnabled()) {
      logger.info(Arrays.toString(youTubeDocumentaryIds.toArray()));
    }
    try {
      return Collections.unmodifiableList(youTubeApiBuilder.getVideosByIds(youTubeDocumentaryIds));
    } catch (IOException e) {
      String errorMessage = "Job failed during fetching documentary videos from YouTube API.";
      logger.error(String.format("Error Message %s %s", errorMessage, e.getMessage()), e);
      throw new JobException(errorMessage, e);
    }
  }

  @Override
  public List<Documentary> processData(List<Documentary> documentaries) {
    return Collections.unmodifiableList(documentaries);
  }

  @Override
  public void saveToDB(List<Documentary> documentaries) {
    documentaryRepository.saveAll(documentaries);
  }
}
