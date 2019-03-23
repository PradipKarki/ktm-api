package com.ktm.rest.controller;

import static com.ktm.rest.ApiConstants.YOUTUBE;

import com.ktm.library.core.model.YouTubePo;
import com.ktm.library.core.service.YouTubeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/youtube")
@RefreshScope
@Api(tags = YOUTUBE, value = YOUTUBE)
public class YouTubeController {

  @Autowired private YouTubeService youtubeService;

  @GetMapping("/nepal")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all YouTube Videos Related to Nepal News")
  public Iterable<YouTubePo> getYouTubeNepalVideos() {
    return this.youtubeService.findAll();
  }
}
