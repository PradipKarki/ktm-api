package com.ktm.youtube.mapper;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.ktm.utils.DateUtility;
import com.ktm.youtube.model.YouTubePo;
import com.ktm.youtube.service.YouTubeService;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", imports =
    {DateUtility.class, YouTubeService.class})
@SuppressWarnings("squid:S1214")
public interface VideoMapper {

  @Mapping(expression = "java(result.getId().getVideoId())", target = "videoId")
  @Mapping(expression = "java(result.getSnippet().getTitle())", target = "title")
  @Mapping(expression = "java(result.getSnippet().getDescription())", target = "description")
  @Mapping(expression = "java(DateUtility.convertToLocalDateTime(result.getSnippet()" +
      ".getPublishedAt().getValue()))"
      , target = "publishedDate")
  @Mapping(target = "thumbnailUrl", ignore = true)
  @Mapping(expression = "java(YouTubeService.buildVideoUrl(result.getId().getVideoId()))", target
      = "url")
  YouTubePo toYouTubePo(SearchResult result);

  List<YouTubePo> toYouTubePo(List<SearchResult> results);

  @Mapping(expression = "java(video.getId())", target = "videoId")
  @Mapping(expression = "java(video.getSnippet().getTitle())", target = "title")
  @Mapping(expression = "java(video.getSnippet().getDescription())", target = "description")
  @Mapping(expression = "java(DateUtility.convertToLocalDateTime(video.getSnippet()" +
      ".getPublishedAt().getValue()))"
      , target = "publishedDate")
  @Mapping(target = "thumbnailUrl", ignore = true)
  @Mapping(expression = "java(YouTubeService.buildVideoUrl(video.getId()))", target = "url")
  YouTubePo toYouTubePo(Video video);

  @AfterMapping
  default void afterYouTubePoMapping(@MappingTarget YouTubePo youTubePo, SearchResult result) {
    if (null != result.getSnippet().getThumbnails().getHigh()) {
      youTubePo.setThumbnailUrl(result.getSnippet().getThumbnails().getHigh().getUrl());
    } else {
      youTubePo.setThumbnailUrl(result.getSnippet().getThumbnails().getDefault().getUrl());
    }
  }

  @AfterMapping
  default void afterYouTubePoMapping(@MappingTarget YouTubePo youTubePo, Video video) {
    if (null != video.getSnippet().getThumbnails().getHigh()) {
      youTubePo.setThumbnailUrl(video.getSnippet().getThumbnails().getHigh().getUrl());
    } else {
      youTubePo.setThumbnailUrl(video.getSnippet().getThumbnails().getDefault().getUrl());
    }
  }

}