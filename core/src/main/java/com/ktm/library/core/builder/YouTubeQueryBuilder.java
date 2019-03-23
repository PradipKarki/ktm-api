package com.ktm.library.core.builder;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.ktm.library.core.utils.DateUtility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class YouTubeQueryBuilder {

  @Value("${YouTube.QueryTerm}")
  private String queryTerm;

  @Value("${YouTube.IdSnippet}")
  private String idSnippet;

  @Value("${YouTube.CategoryNewsPolitics}")
  private String categoryNewsPolitics;

  @Value("${YouTube.MediaTypeVideo}")
  private String mediaTypeVideo;

  @Value("${YouTube.OrderBy}")
  private String orderBy;

  @Value("${YouTube.VideoEmbeddableTrue}")
  private String videoEmbeddableTrue;

  @Value("${YouTube.VideoSearchSetFields}")
  private String youtubeVideoSearchSetFields;

  @Value("${YouTube.VideoTypeModerate}")
  private String videoTypeModerate;

  @Value("${YouTube.Language}")
  private String language;

  @Value("${youtube.apikey}")
  private String apiValue;

  @Autowired private ApiBuilder<YouTube> youTubeApiBuilder;

  public Video getVideoById(String videoId) throws IOException {
    YouTube youtube = youTubeApiBuilder.getInstance();
    HashMap<String, String> parameters = new HashMap<>();
    parameters.put("part", "snippet,contentDetails,statistics"); // $NON-NLS-1$//$NON-NLS-2$
    YouTube.Videos.List search = youtube.videos().list(parameters.get("part")); // $NON-NLS-1$
    search.setId(videoId);
    search.setKey(this.apiValue);
    List<Video> videos = search.execute().getItems();
    return Objects.requireNonNull(videos).get(0);
  }

  public List<Video> getVideosByIds(List<String> videoIds) throws IOException {
    List<Video> videos = new ArrayList<>();
    for (String videoId : videoIds) {
      videos.add(getVideoById(videoId));
    }
    return videos;
  }

  /**
   * Returns the first 50 YouTube videos that match the query term
   *
   * @throws java.io.IOException IOException
   */
  public List<SearchResult> getSearchResults() throws IOException {
    Long maxSearchResults = 50L;
    YouTube youtube = youTubeApiBuilder.getInstance();
    YouTube.Search.List search = youtube.search().list(idSnippet);
    search.setKey(apiValue);
    search.setQ(queryTerm);
    search.setType(mediaTypeVideo);
    search.setSafeSearch(videoTypeModerate);
    search.setVideoEmbeddable(videoEmbeddableTrue);
    search.setOrder(orderBy);
    search.setRelevanceLanguage(language);
    search.setVideoCategoryId(categoryNewsPolitics);
    search.setFields(youtubeVideoSearchSetFields);
    search.setMaxResults(maxSearchResults);
    DateTime lastWeek = DateUtility.getDateTimeOfOneWeekAgo();
    search.setPublishedAfter(lastWeek);
    SearchListResponse searchResponse = search.execute();
    return new ArrayList<>(searchResponse.getItems());
  }
}
