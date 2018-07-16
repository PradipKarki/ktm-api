package com.ktm.twitter.mapper;

import com.ktm.twitter.model.TwitterPo;
import com.ktm.twitter.service.TwitterService;
import com.ktm.utils.DateUtility;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import twitter4j.Status;

@Mapper(imports = {TwitterService.class, DateUtility.class})
public interface TwitterMapper {

  @Mapping(expression = "java(status.getText())", target = "title")
  @Mapping(expression = "java(TwitterService.getMediaUrl(status))", target = "imageUri")
  @Mapping(expression = "java(TwitterService.getArticleUrl(status))", target = "articleUri")
  @Mapping(expression = "java(DateUtility.convertToLocalDateTime(status.getCreatedAt()))",
      target = "publishedDate")
  @Mapping(expression = "java(status.getUser().getName())", target = "twitterUser.name")
  @Mapping(expression = "java(status.getUser().getScreenName())",
      target = "twitterUser.userName")
  @Mapping(expression = "java(status.getUser().getMiniProfileImageURL())",
      target = "twitterUser.miniProfileImageURL")
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  TwitterPo toTwitterPo(Status status);

  List<TwitterPo> toTwitterPo(List<Status> statuses);

}
