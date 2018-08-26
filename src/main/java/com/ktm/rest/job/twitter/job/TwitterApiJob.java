package com.ktm.rest.job.twitter.job;

import com.ktm.rest.job.common.JobConfiguration;
import com.ktm.rest.job.common.JobImpl;
import com.ktm.rest.job.twitter.service.TwitterApiService;
import com.ktm.rest.twitter.model.TwitterPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;

@Service
public class TwitterApiJob extends JobImpl<Status, TwitterPo> {

  @Autowired private TwitterApiService twitterApiService;

  @Override
  public JobConfiguration buildJobConfiguration() {
    return JobConfiguration.builder().jobServiceProvider(twitterApiService).build();
  }
}
