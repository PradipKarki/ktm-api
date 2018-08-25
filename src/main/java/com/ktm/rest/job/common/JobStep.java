package com.ktm.rest.job.common;

import com.ktm.rest.BaseEntity;
import com.ktm.rest.rss.mapper.RssNewsMapper;
import com.ktm.rest.twitter.mapper.TwitterMapper;
import com.ktm.rest.youtube.mapper.VideoMapper;
import java.util.Collections;
import java.util.List;
import org.mapstruct.factory.Mappers;

public interface JobStep<T, E extends BaseEntity> {

  List<T> getDataFromAPI(String queryString);

  @SuppressWarnings("unchecked")
  default List<E> mapToDomainObjects(JobType jobType, List apiEntities) {
    switch (jobType) {
      case TWITTER_API_JOB:
        return Collections.unmodifiableList(
            Mappers.getMapper(TwitterMapper.class).toTwitterPo(apiEntities));
      case YOUTUBE_API_JOB:
        return Collections.unmodifiableList(
            Mappers.getMapper(VideoMapper.class).toYouTubePo(apiEntities));
      case RSS_JOB:
        return Collections.unmodifiableList(
            Mappers.getMapper(RssNewsMapper.class).toRssNews(apiEntities));
      case MAIL_JOB:
      case DOCUMENTARY_API_JOB:
        return List.of();
    }
    return List.of();
  }

  List<E> processData(List<E> domainEntities);

  void saveToDB(List<E> filteredEntities);
}
