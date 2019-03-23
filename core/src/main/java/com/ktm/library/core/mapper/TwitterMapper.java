package com.ktm.library.core.mapper;

import com.ktm.library.core.model.twitter.TwitterDto;
import com.ktm.library.core.model.twitter.TwitterPo;
import com.ktm.library.core.utils.DateUtility;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import twitter4j.MediaEntity;
import twitter4j.Status;
import twitter4j.URLEntity;

@Mapper(imports = DateUtility.class)
public interface TwitterMapper {

  @Mapping(expression = "java(status.getText())", target = "title")
  @Mapping(expression = "java(getMediaUrl(status))", target = "imageUri")
  @Mapping(expression = "java(getArticleUrl(status))", target = "articleUri")
  @Mapping(
      expression = "java(DateUtility.convertToLocalDateTime(status.getCreatedAt()))",
      target = "publishedDate")
  @Mapping(expression = "java(status.getUser().getName())", target = "twitterUser.name")
  @Mapping(expression = "java(status.getUser().getScreenName())", target = "twitterUser.userName")
  @Mapping(
      expression = "java(status.getUser().getMiniProfileImageURL())",
      target = "twitterUser.miniProfileImageURL")
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  TwitterPo toTwitterPo(Status status);

  List<TwitterPo> toTwitterPo(List<Status> statuses);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  TwitterPo toTwitterPo(TwitterDto twitterDto);

  default String getArticleUrl(Status status) {
    return Stream.of(status.getURLEntities())
        .filter(u -> StringUtils.isNotEmpty(u.getURL()))
        .findAny()
        .map(URLEntity::getURL)
        .orElse(StringUtils.EMPTY);
  }

  default String getMediaUrl(Status status) {
    return Stream.of(status.getMediaEntities())
        .filter(m -> StringUtils.isNotEmpty(m.getMediaURL()))
        .findAny()
        .map(MediaEntity::getMediaURL)
        .orElse(StringUtils.EMPTY);
  }
}
