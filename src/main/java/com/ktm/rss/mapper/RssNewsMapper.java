package com.ktm.rss.mapper;

import com.ktm.rss.model.RssNews;
import com.ktm.rss.service.RssService;
import com.ktm.utils.DateUtility;
import com.rometools.rome.feed.synd.SyndEntry;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(imports = {DateUtility.class, RssService.class})
@SuppressWarnings("squid:S1214")
public interface RssNewsMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(expression = "java(RssService.getTags(entry.getCategories()))", target = "tags")
  @Mapping(expression = "java(RssService.getContents(entry.getContents()))", target = "contents")
  @Mapping(expression = "java(entry.getDescription().getValue())", target = "description")
  @Mapping(expression = "java(DateUtility.convertToLocalDateTime(entry.getPublishedDate()))",
      target = "publishedDate")
  @Mapping(expression = "java(DateUtility.convertToLocalDateTime(entry.getUpdatedDate()))",
      target = "updatedDate")
  @Mapping(expression = "java(entry.getSource() == null ? null : entry.getSource().getIcon())",
      target = "icon")
  @Mapping(expression = "java(entry.getSource() == null ? null : entry.getSource().getFeedType())"
      , target = "feedType")
  @Mapping(expression = "java(entry.getSource() == null ? null : entry.getSource().getImage())",
      target = "image")
  RssNews toRssNews(SyndEntry entry);

  List<RssNews> toRssNews(List<SyndEntry> entries);

}
