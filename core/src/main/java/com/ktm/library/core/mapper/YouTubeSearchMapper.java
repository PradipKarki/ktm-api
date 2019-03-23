package com.ktm.library.core.mapper;

import com.google.api.services.youtube.model.SearchResult;
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
public interface YouTubeSearchMapper {

  @Mapping(expression = "java(result.getId().getVideoId())", target = "id")
  @Mapping(expression = "java(result.getSnippet().getTitle())", target = "title")
  @Mapping(expression = "java(result.getSnippet().getDescription())", target = "description")
  @Mapping(
      expression =
          "java(DateUtility.convertToLocalDateTime(result.getSnippet().getPublishedAt().getValue()))",
      target = "publishedDate")
  @Mapping(target = "thumbnailUrl", ignore = true)
  @Mapping(
      expression = "java(AppUtils.buildYouTubeUrl(result.getId().getVideoId()))",
      target = "url")
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  YouTubePo toYouTubePo(SearchResult result);

  List<YouTubePo> toYouTubePo(List<SearchResult> results);

  @AfterMapping
  default void afterYouTubePoMapping(@MappingTarget YouTubePo youTubePo, SearchResult result) {
    if (result.getSnippet().getThumbnails().getHigh() != null) {
      youTubePo.setThumbnailUrl(result.getSnippet().getThumbnails().getHigh().getUrl());
    } else {
      youTubePo.setThumbnailUrl(result.getSnippet().getThumbnails().getDefault().getUrl());
    }
  }
}
