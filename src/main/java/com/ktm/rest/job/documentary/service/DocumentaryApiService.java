package com.ktm.rest.job.documentary.service;

import com.google.api.services.youtube.model.Video;
import com.ktm.rest.documentary.model.Documentary;
import com.ktm.rest.job.common.JobStep;

public interface DocumentaryApiService extends JobStep<Video, Documentary> {}
