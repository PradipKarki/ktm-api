package com.ktm.rest.rss.controller;

import com.ktm.rest.ApiConstants;
import com.ktm.rest.rss.model.RssNews;
import com.ktm.rest.rss.service.RssService;
import com.rometools.rome.io.FeedException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@RefreshScope
@Api(tags = ApiConstants.RSS, description = "Retrieve RSS News Feed from Data Source")
public class RssNewsController {

  @Autowired
  @Qualifier("international")
  private RssService internationalRssService;

  @Autowired
  @Qualifier("national")
  private RssService nationalRssService;

  @GetMapping("/international")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all RSS News Feed international media")
  public List<RssNews> fetchInternationalRssFeedByQuery() throws IOException, FeedException {
    return this.internationalRssService.fetchRssFeedByQuery();
  }

  @GetMapping("/national")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all RSS News Feed from national media")
  public List<RssNews> fetchNationalRssFeedByQuery() throws IOException, FeedException {
    return this.nationalRssService.fetchRssFeedByQuery();
  }
}
