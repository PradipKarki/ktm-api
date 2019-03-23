package com.ktm.library.core.service;

import static java.util.stream.Collectors.toList;
import static org.mapstruct.factory.Mappers.getMapper;

import com.ktm.library.core.mapper.RssNewsMapper;
import com.ktm.library.core.model.RssNews;
import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.stream.Stream;

public interface RssService {

  static List<String> getTags(List<SyndCategory> categories) {
    return categories.stream().flatMap(category -> Stream.of(category.getName())).collect(toList());
  }

  static List<String> getContents(List<SyndContent> contents) {
    return contents.stream().flatMap(content -> Stream.of(content.getValue())).collect(toList());
  }

  default List<SyndEntry> getEntriesFromFeedUrl(URL feedUrl, String searchQueryNepal)
      throws IOException, FeedException {
    SyndFeedInput input = new SyndFeedInput();
    try (Reader xmlReader = new XmlReader(feedUrl)) {
      SyndFeed feed = input.build(xmlReader);
      return feed.getEntries()
          .stream()
          .filter(item -> contains(item, searchQueryNepal))
          .collect(toList());
    }
  }

  default boolean contains(SyndEntry item, String searchQueryNepal) {
    return item.getTitle().toLowerCase().contains(searchQueryNepal);
  }

  default List<RssNews> toRssNews(List<SyndEntry> entries) {
    return getMapper(RssNewsMapper.class).toRssNews(entries);
  }

  List<RssNews> fetchRssFeedByQuery() throws FeedException, IOException;
}
