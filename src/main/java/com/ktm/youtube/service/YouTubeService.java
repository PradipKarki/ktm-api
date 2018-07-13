package com.ktm.youtube.service;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.ktm.utils.TextUtility;
import com.ktm.youtube.mapper.VideoMapper;
import com.ktm.youtube.model.YouTubePo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class YouTubeService {
  private static final Logger logger = LoggerFactory.getLogger(YouTubeService.class);
  @Value("${YouTube.VideoUrlPrefix}")
  private static String youtubeVideoUrlPrefix;
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
  @Value("${Twitter.RegexSequenceOfWhiteCharacters}")
  private String regexSequenceOfWhiteCharacters;
  @Value("${YouTube.AppName}")
  private String youtubeSpringApp;

  private static DateTime getDateTimeOfWeekAgo() {
    // set seven days ago
    long dayInMillSeconds = 1000L * 60L * 60L * 24L;
    return new DateTime(System.currentTimeMillis() - (7L * dayInMillSeconds));
  }

  /**
   * Constructs the URL to play the YouTube video
   */
  public static String buildVideoUrl(String videoId) {
    return youtubeVideoUrlPrefix + videoId;
  }

  public Video getVideoByVideoId(String videoId) throws IOException {
    YouTube youtube = getYouTube();
    HashMap<String, String> parameters = new HashMap<>();
    parameters.put("part", "snippet,contentDetails,statistics"); //$NON-NLS-1$//$NON-NLS-2$
    YouTube.Videos.List search = youtube.videos()
                                        .list(parameters.get("part")); //$NON-NLS-1$
    search.setId(videoId);
    search.setKey(this.apiValue);
    if (logger.isInfoEnabled()) {
      logger.info(search.toString());
    }
    return search.execute().getItems().get(0);
  }

  /**
   * Returns the first 50 YouTube videos that match the query term
   *
   * @throws IOException IOException
   */
  public List<YouTubePo> fetchVideosByQuery(String queryTerm) throws IOException {
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
    DateTime lastWeek = getDateTimeOfWeekAgo();
    search.setPublishedAfter(lastWeek);
    return executeYouTubeSearch(search);
  }

  private List<YouTubePo> executeYouTubeSearch(YouTube.Search.List search) throws IOException {
    List<YouTubePo> videos = new ArrayList<>();
    // perform the search and parse the results
    SearchListResponse searchResponse = search.execute();
    List<SearchResult> searchResultList = searchResponse.getItems();
    if (null == searchResultList)
      return videos;
    for (SearchResult result : searchResultList) {
      YouTubePo video = Mappers.getMapper(VideoMapper.class).toYouTubePo(result);
      if (!isYouTubeVideoDuplicate(video.getTitle(), videos)
          && !TextUtility
          .isThisUnicode(video.getTitle(), Character.UnicodeBlock.DEVANAGARI)) {
        videos.add(video);
      }
    }
    videos.sort((a, b) -> b.getPublishedDate().compareTo(a.getPublishedDate()));
    return videos;
  }

  /**
   * Instantiates the YouTube object
   */
  @SuppressWarnings("squid:S1611")
  private YouTube getYouTube() {
    return new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), (request) -> {
      /* empty block */
    }).setApplicationName(youtubeSpringApp).build();
  }

  /**
   * take a piece of the youTube video title check if it is already in the youTube
   * video list if it's there, it's duplicate, ignore it
   */
  private boolean isYouTubeVideoDuplicate(String youTubeTitle, List<YouTubePo> videos) {
    String middleOfTitleString = TextUtility.getMiddleOfText(youTubeTitle);
    List<String> youTubeTitles = videos.stream().map(YouTubePo::getTitle)
                                       .collect(Collectors.toList());
    for (String titleFromList : youTubeTitles) {
      String titleFromListLC = titleFromList.toLowerCase();
      if (titleFromListLC.contains(middleOfTitleString.toLowerCase())
          || youTubeTitle.toLowerCase().contains(titleFromListLC)
          || titleFromListLC.equalsIgnoreCase(youTubeTitle)) {
        return true;
      }

      // if it's not duplicate let's check it matches few words
      // check if first three words in the title List
      String[] splitStr = youTubeTitle.toLowerCase()
                                      .split(regexSequenceOfWhiteCharacters);
      int length = splitStr.length;
      if (length > 5 && titleFromListLC.contains(splitStr[1]) && titleFromListLC
          .contains(splitStr[2])
          && titleFromListLC.contains(splitStr[3]))
        return true;

      // check also if last three words in the title List
      if (length > 5 && titleFromListLC.contains(splitStr[length - 1])
          && titleFromListLC.contains(splitStr[length - 2]) && titleFromListLC
          .contains(splitStr[length - 3]))
        return true;
    }
    return false;
  }

}
