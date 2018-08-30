package com.ktm.rest.job.common;

import com.ktm.exception.JobException;
import com.ktm.rest.job.documentary.job.DocumentaryApiJob;
import com.ktm.rest.job.twitter.job.TwitterApiJob;
import com.ktm.rest.job.youtube.job.YouTubeApiJob;
import java.util.Arrays;
import java.util.Objects;

public enum JobType {
  TWITTER_API_JOB(TwitterApiJob.class),
  YOUTUBE_API_JOB(YouTubeApiJob.class),
  DOCUMENTARY_API_JOB(DocumentaryApiJob.class),
  RSS_JOB(TwitterApiJob.class),
  MAIL_JOB(TwitterApiJob.class);

  private final Class<? extends Job> jobClass;

  JobType(Class<? extends Job> jobClass) {
    this.jobClass = jobClass;
  }

  public static JobType getJobTypeFromClassName(Class<? extends Job> jobClass) {
    return Arrays.stream(JobType.values())
        .filter(j -> Objects.equals(j.getJobClass(), jobClass))
        .findFirst()
        .orElseThrow(() -> new JobException("can't determine JobType, "));
  }

  public Class<? extends Job> getJobClass() {
    return jobClass;
  }
}
