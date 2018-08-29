package com.ktm.rest.twitter.service;

import com.ktm.rest.CrudCollectionService;
import com.ktm.rest.CrudService;
import com.ktm.rest.twitter.model.TwitterPo;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import twitter4j.MediaEntity;
import twitter4j.Status;
import twitter4j.URLEntity;

public interface TwitterService
    extends CrudService<Long, TwitterPo>, CrudCollectionService<TwitterPo> {

  static String getArticleUrl(Status status) {
    return Stream.of(status.getURLEntities())
        .filter(u -> StringUtils.isNotEmpty(u.getURL()))
        .findAny()
        .map(URLEntity::getURL)
        .orElse(StringUtils.EMPTY);
  }

  static String getMediaUrl(Status status) {
    return Stream.of(status.getMediaEntities())
        .filter(m -> StringUtils.isNotEmpty(m.getMediaURL()))
        .findAny()
        .map(MediaEntity::getMediaURL)
        .orElse(StringUtils.EMPTY);
  }
}
