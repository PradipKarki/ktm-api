package com.ktm.rest.job.youtube.controller;

import com.ktm.rest.ApiConstants;
import com.ktm.rest.job.common.JobLauncher;
import com.ktm.rest.job.youtube.job.YouTubeApiJob;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs/youtube")
@RefreshScope
@Api(tags = ApiConstants.TWITTER, description = "Job - Retrieve YouTube Videos from YouTube API")
public class YouTubeApiController {
  @Value("${App.Nepal.SearchQueryKeyWord}")
  private String searchQueryNepal;

  @Autowired private YouTubeApiJob youTubeApiJob;

  @GetMapping("/nepal")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all YouTube Videos Related to Nepal News")
  public void getYouTubeNepalVideosAndSaveToDB() {
    JobLauncher.run(youTubeApiJob, new String[] {searchQueryNepal});
  }
}
