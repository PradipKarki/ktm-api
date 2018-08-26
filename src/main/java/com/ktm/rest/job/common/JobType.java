package com.ktm.rest.job.common;

import com.ktm.exception.JobException;
import com.ktm.rest.job.twitter.job.TwitterApiJob;
import java.util.Arrays;

public enum JobType {
  TWITTER_API_JOB(TwitterApiJob.class),
  YOUTUBE_API_JOB(TwitterApiJob.class),
  DOCUMENTARY_API_JOB(TwitterApiJob.class),
  RSS_JOB(TwitterApiJob.class),
  MAIL_JOB(TwitterApiJob.class);

  private Class<? extends Job> jobClass;

  JobType(Class<? extends Job> jobClass) {
    this.jobClass = jobClass;
  }

  public static JobType getJobTypeFromClassName(Class<? extends Job> jobClass) {
    return Arrays.stream(JobType.values())
        .filter(j -> j.getJobClass().equals(jobClass))
        .findFirst()
        .orElseThrow(() -> new JobException("can't determine JobType, "));
  }

  public Class<? extends Job> getJobClass() {
    return jobClass;
  }
}
