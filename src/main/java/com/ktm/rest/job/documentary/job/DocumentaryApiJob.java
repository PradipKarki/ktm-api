package com.ktm.rest.job.documentary.job;

import com.google.api.services.youtube.model.Video;
import com.ktm.rest.documentary.model.Documentary;
import com.ktm.rest.job.common.JobConfiguration;
import com.ktm.rest.job.common.JobImpl;
import com.ktm.rest.job.documentary.service.DocumentaryApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentaryApiJob extends JobImpl<Video, Documentary> {

  @Autowired private DocumentaryApiService documentaryApiService;

  @Override
  public JobConfiguration buildJobConfiguration() {
    return JobConfiguration.builder().jobServiceProvider(documentaryApiService).build();
  }
}
