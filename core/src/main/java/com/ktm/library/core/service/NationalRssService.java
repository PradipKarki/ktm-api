package com.ktm.library.core.service;

import com.ktm.library.core.model.RssNews;
import com.rometools.rome.io.FeedException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Qualifier("national")
public class NationalRssService implements RssService {

  @Value("${App.Query}")
  private String queryString;

  @Value("${rss.national.nepal.himalayan-times-1}")
  private String url;

  @Override
  public List<RssNews> fetchRssFeedByQuery() throws FeedException, IOException {
    URL feedUrl = new URL(this.url);
    return toRssNews(getEntriesFromFeedUrl(feedUrl, queryString));
  }
}
