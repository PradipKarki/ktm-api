package com.ktm.rest.youtube.controller;

import com.ktm.rest.ApiConstants;
import com.ktm.rest.youtube.model.YouTubePo;
import com.ktm.rest.youtube.service.YouTubeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/youtube")
@RefreshScope
@Api(tags = ApiConstants.YOUTUBE, description = "Retrieve YouTube Videos from Data Source")
public class YouTubeController {

  @Value("${App.Nepal.SearchQueryKeyWord}")
  private String searchQueryNepal;

  @Autowired private YouTubeService youtubeService;

  @GetMapping("/nepal")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all YouTube Videos Related to Nepal News")
  public List<YouTubePo> getYouTubeNepalVideos() throws IOException {
    return this.youtubeService.fetchVideosByQuery(searchQueryNepal);
  }
}
