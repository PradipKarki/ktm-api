package com.ktm.rest.job.common;

import static com.ktm.rest.documentary.model.Documentary.toDocumentaries;

import com.ktm.rest.BaseEntity;
import com.ktm.rest.rss.mapper.RssNewsMapper;
import com.ktm.rest.twitter.mapper.TwitterMapper;
import com.ktm.rest.youtube.mapper.YouTubeSearchMapper;
import com.ktm.rest.youtube.mapper.YouTubeVideoMapper;
import java.util.List;
import org.mapstruct.factory.Mappers;

public interface JobStep<T, E extends BaseEntity> {
  /**
   * @param queryString query to search
   * @throws com.ktm.exception.JobException JobException
   * @return list of objects from external API
   */
  List<T> getDataFromAPI(String queryString);

  @SuppressWarnings("unchecked")
  default List<E> mapToDomainObjects(JobType jobType, List apiEntities) {
    switch (jobType) {
      case TWITTER_API_JOB:
        return Mappers.getMapper(TwitterMapper.class).toTwitterPo(apiEntities);
      case YOUTUBE_API_JOB:
        return Mappers.getMapper(YouTubeSearchMapper.class).toYouTubePo(apiEntities);
      case DOCUMENTARY_API_JOB:
        return toDocumentaries(
            Mappers.getMapper(YouTubeVideoMapper.class).toYouTubePo(apiEntities));
      case RSS_JOB:
        return Mappers.getMapper(RssNewsMapper.class).toRssNews(apiEntities);
      case MAIL_JOB:
        return List.of();
      default:
        return List.of();
    }
  }

  List<E> processData(List<E> domainEntities);

  void saveToDB(List<E> filteredEntities);
}
