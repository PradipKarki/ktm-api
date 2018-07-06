package com.ktm.youtube.controller;

import com.ktm.ApiConstants;
import com.ktm.youtube.model.YouTubePO;
import com.ktm.youtube.service.YouTubeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:messages.properties")
@RequestMapping("/youtube")
@RefreshScope
@Api(tags = ApiConstants.YOUTUBE, description = "Retrieve YouTube Videos from Data Source")
public class YouTubeController {

  @Autowired
  Environment env;

  @Autowired
  private YouTubeService youtubeService;

  @GetMapping("/nepal")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all YouTube Videos Related to Nepal News")
  public List<YouTubePO> getYouTubeNepalVideos() throws IOException {
    String searchQueryNepal = this.env
      .getProperty("App.Nepal.SearchQueryKeyWord"); //$NON-NLS-1$
    return this.youtubeService.fetchVideosByQuery(searchQueryNepal);
  }

}
