package com.ktm.rest.job.youtube.builder;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.ktm.utils.DateUtility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class YouTubeApiBuilder {
  @Value("${YouTube.VideoSearchSetFields}")
  private String youtubeVideoSearchSetFields;

  @Value("${YouTube.CategoryNewsPolitics}")
  private String categoryNewsPolitics;

  @Value("${YouTube.OrderBy}")
  private String orderBy;

  @Value("${YouTube.VideoEmbeddableTrue}")
  private String videoEmbeddableTrue;

  @Value("${YouTube.VideoTypeModerate}")
  private String videoTypeModerate;

  @Value("${YouTube.MediaTypeVideo}")
  private String mediaTypeVideo;

  @Value("${YouTube.IdSnippet}")
  private String idSnippet;

  @Value("${App.English.Language}")
  private String englishLanguage;

  @Value("${youtube.apikey}")
  private String apiValue;

  @Value("${YouTube.AppName}")
  private String youtubeSpringApp;

  public Video getVideoById(String videoId) throws IOException {
    YouTube youtube = getYouTube();
    HashMap<String, String> parameters = new HashMap<>();
    parameters.put("part", "snippet,contentDetails,statistics"); // $NON-NLS-1$//$NON-NLS-2$
    YouTube.Videos.List search = youtube.videos().list(parameters.get("part")); // $NON-NLS-1$
    search.setId(videoId);
    search.setKey(this.apiValue);
    return search.execute().getItems().get(0);
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
   * @throws IOException IOException
   */
  public List<SearchResult> getSearchResults(String queryTerm) throws IOException {
    Long maxSearchResults = 50L;
    YouTube youtube = getYouTube();
    YouTube.Search.List search = youtube.search().list(idSnippet);
    search.setKey(this.apiValue);
    search.setQ(queryTerm);
    search.setType(mediaTypeVideo);
    search.setSafeSearch(videoTypeModerate);
    search.setVideoEmbeddable(videoEmbeddableTrue);
    search.setOrder(orderBy);
    search.setRelevanceLanguage(englishLanguage);
    search.setVideoCategoryId(categoryNewsPolitics);
    search.setFields(youtubeVideoSearchSetFields);
    search.setMaxResults(maxSearchResults);
    DateTime lastWeek = DateUtility.getDateTimeOfOneWeekAgo();
    search.setPublishedAfter(lastWeek);
    SearchListResponse searchResponse = search.execute();
    return searchResponse.getItems();
  }

  /** Instantiates the YouTube object */
  @SuppressWarnings("squid:S1611")
  private YouTube getYouTube() {
    return new YouTube.Builder(
            new NetHttpTransport(),
            new JacksonFactory(),
            (request) -> {
              /* empty block */
            })
        .setApplicationName(youtubeSpringApp)
        .build();
  }
}
