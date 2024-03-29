package com.ktm.library.core.mapper;

import com.google.api.services.youtube.model.Video;
import com.ktm.library.core.model.YouTubePo;
import com.ktm.library.core.utils.AppUtils;
import com.ktm.library.core.utils.DateUtility;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
    componentModel = "spring",
    imports = {DateUtility.class, AppUtils.class})
@SuppressWarnings("squid:S1214")
public interface YouTubeVideoMapper {

  @Mapping(expression = "java(video.getId())", target = "id")
  @Mapping(expression = "java(video.getSnippet().getTitle())", target = "title")
  @Mapping(expression = "java(video.getSnippet().getDescription())", target = "description")
  @Mapping(
      expression =
          "java(DateUtility.convertToLocalDateTime(video.getSnippet().getPublishedAt().getValue()))",
      target = "publishedDate")
  @Mapping(target = "thumbnailUrl", ignore = true)
  @Mapping(expression = "java(AppUtils.buildYouTubeUrl(video.getId()))", target = "url")
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  YouTubePo toYouTubePo(Video video);

  List<YouTubePo> toYouTubePo(List<Video> video);

  @AfterMapping
  default void afterYouTubePoMapping(@MappingTarget YouTubePo youTubePo, Video video) {
    if (video.getSnippet().getThumbnails().getHigh() != null) {
      youTubePo.setThumbnailUrl(video.getSnippet().getThumbnails().getHigh().getUrl());
    } else {
      youTubePo.setThumbnailUrl(video.getSnippet().getThumbnails().getDefault().getUrl());
    }
  }
}
