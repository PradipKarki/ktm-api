package com.ktm.rest.job.twitter.service;

import com.ktm.rest.job.common.JobStep;
import com.ktm.rest.twitter.model.TwitterPo;
import twitter4j.Status;

public interface TwitterApiService extends JobStep<Status, TwitterPo> {}
