package com.ktm.rest.job.youtube.job;

import com.google.api.services.youtube.model.SearchResult;
import com.ktm.rest.job.common.JobConfiguration;
import com.ktm.rest.job.common.JobImpl;
import com.ktm.rest.job.youtube.service.YouTubeApiService;
import com.ktm.rest.youtube.model.YouTubePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YouTubeApiJob extends JobImpl<SearchResult, YouTubePo> {

  @Autowired private YouTubeApiService youTubeApiService;

  @Override
  public JobConfiguration buildJobConfiguration() {
    return JobConfiguration.builder().jobServiceProvider(youTubeApiService).build();
  }
}
