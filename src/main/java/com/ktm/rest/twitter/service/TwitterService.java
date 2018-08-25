package com.ktm.rest.twitter.service;

import static com.ktm.rest.ApiConstants.EMPTY_STRING;

import com.ktm.rest.CrudCollectionService;
import com.ktm.rest.CrudService;
import com.ktm.rest.twitter.model.TwitterPo;
import java.util.stream.Stream;
import twitter4j.MediaEntity;
import twitter4j.Status;
import twitter4j.URLEntity;

public interface TwitterService
    extends CrudService<Long, TwitterPo>, CrudCollectionService<TwitterPo> {

  static String getArticleUrl(Status status) {
    return Stream.of(status.getURLEntities())
        .findAny()
        .filter(u -> !u.getURL().isEmpty())
        .map(URLEntity::getURL)
        .orElse(EMPTY_STRING);
  }

  static String getMediaUrl(Status status) {
    return Stream.of(status.getMediaEntities())
        .findAny()
        .filter(m -> !m.getMediaURL().isEmpty())
        .map(MediaEntity::getMediaURL)
        .orElse(EMPTY_STRING);
  }
}
