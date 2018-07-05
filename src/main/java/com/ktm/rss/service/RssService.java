package com.ktm.rss.service;

import com.ktm.rss.model.RssNews;
import com.ktm.utils.DateUtility;
import com.rometools.rome.feed.synd.SyndEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface RssService {

  default List<RssNews> readAndParseRssEntries(List<SyndEntry> entries) {
    List<RssNews> rssNewsList = new ArrayList<>();
    for (SyndEntry entry : entries) {
      RssNews rssNews = new RssNews();
      rssNews.setAuthors(entry.getAuthors());
      rssNews.setContributors(entry.getContributors());
      List<String> tags = entry.getCategories().stream()
                               .flatMap(category -> Stream.of(category.getName()))
                               .collect(Collectors.toList());
      rssNews.setTags(tags);
      rssNews.setComments(entry.getComments());
      List<String> contents = entry.getContents().stream()
                                   .flatMap(content -> Stream.of(content.getValue()))
                                   .collect(Collectors.toList());
      rssNews.setContents(contents);
      rssNews.setTitle(entry.getTitle());
      rssNews.setDescription(entry.getDescription().getValue());
      rssNews.setUri(entry.getUri());
      rssNews.setPublishedDate(DateUtility.convertToLocalDateTime(entry.getPublishedDate()));
      rssNews.setUpdatedDate(DateUtility.convertToLocalDateTime(entry.getUpdatedDate()));
      if (null != entry.getSource()) {
        rssNews.setIcon(entry.getSource().getIcon());
        rssNews.setFeedType(entry.getSource().getFeedType());
        rssNews.setImage(entry.getSource().getImage());
      }
      rssNewsList.add(rssNews);
    }
    return rssNewsList;
  }
}
