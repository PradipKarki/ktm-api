package com.ktm.rest.rss.service;

import com.ktm.rest.rss.mapper.RssNewsMapper;
import com.ktm.rest.rss.model.RssNews;
import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.FeedException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.mapstruct.factory.Mappers;

public interface RssService {

  static List<String> getTags(List<SyndCategory> categories) {
    return categories
        .stream()
        .flatMap(category -> Stream.of(category.getName()))
        .collect(Collectors.toList());
  }

  static List<String> getContents(List<SyndContent> contents) {
    return contents
        .stream()
        .flatMap(content -> Stream.of(content.getValue()))
        .collect(Collectors.toList());
  }

  default List<RssNews> toRssNews(List<SyndEntry> entries) {
    return Mappers.getMapper(RssNewsMapper.class).toRssNews(entries);
  }

  List<RssNews> fetchRssFeedByQuery() throws FeedException, IOException;
}
