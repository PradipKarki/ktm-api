package com.ktm.rest.rss.service.impl;

import com.ktm.rest.rss.model.RssNews;
import com.ktm.rest.rss.service.RssService;
import com.rometools.rome.io.FeedException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Qualifier("international")
public class InternationalRssService implements RssService {

  @Value("${App.Nepal.SearchQueryKeyWord}")
  private String searchQueryNepal;

  @Value("${rss.international.india.tribune-india-2}")
  private String url;

  @Override
  public List<RssNews> fetchRssFeedByQuery() throws FeedException, IOException {
    URL feedUrl = new URL(this.url);
    return toRssNews(getEntriesFromFeedUrl(feedUrl, searchQueryNepal));
  }
}
