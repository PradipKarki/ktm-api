package com.ktm.rest.job.youtube.service;

import com.google.api.services.youtube.model.SearchResult;
import com.ktm.rest.job.common.JobStep;
import com.ktm.rest.youtube.model.YouTubePo;

public interface YouTubeApiService extends JobStep<SearchResult, YouTubePo> {}
