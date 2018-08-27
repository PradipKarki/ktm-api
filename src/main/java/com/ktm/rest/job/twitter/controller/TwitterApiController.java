package com.ktm.rest.job.twitter.controller;

import com.ktm.rest.ApiConstants;
import com.ktm.rest.job.common.JobLauncher;
import com.ktm.rest.job.twitter.job.TwitterApiJob;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs/twitter")
@RefreshScope
@Api(tags = ApiConstants.TWITTER, description = "Job - Retrieve Twitter News from Twitter API")
public class TwitterApiController {

  @Value("${App.Nepal.TwitterSearchQueryKeyWord}")
  private String searchNepalQuery;

  @Value("${App.Everest.TwitterSearchQueryKeyWord}")
  private String searchEverestQuery;

  @Value("${App.Kathmandu.TwitterSearchQueryKeyWord}")
  private String searchKathmanduQuery;

  @Autowired private TwitterApiJob twitterApiJob;

  @GetMapping("/nepal")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Load all Tweets Related to Nepal")
  public void getTweetsNepalAndSaveToDB() {
    runTwitterApiJob(searchNepalQuery);
  }

  @GetMapping("/everest")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Load all Tweets Related to Everest")
  public void getTweetsEverestAndSaveToDB() {
    runTwitterApiJob(searchEverestQuery);
  }

  @GetMapping("/kathmandu")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Load all Tweets Related to Kathmandu")
  public void getTweetsKathmanduAndSaveToDB() {
    runTwitterApiJob(searchKathmanduQuery);
  }

  @GetMapping("/search/{tweetKeyWord}")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Load all Tweets Related to Search KeyWord")
  public void getTweetsByKeyWordAndSaveToDB(@PathVariable String tweetKeyWord) {
    runTwitterApiJob(tweetKeyWord);
  }

  private void runTwitterApiJob(String searchNepalQuery) {
    JobLauncher.run(twitterApiJob, new String[] {searchNepalQuery});
  }
}
