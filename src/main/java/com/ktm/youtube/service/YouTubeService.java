package com.ktm.youtube.service;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.ktm.utils.DateUtility;
import com.ktm.utils.TextUtility;
import com.ktm.youtube.model.YouTubePO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:messages.properties")
public class YouTubeService {
  private static final Logger logger = LoggerFactory.getLogger(YouTubeService.class);

  @Autowired
  Environment env;

  @Value("${youtube.apikey}")
  private String apiValue;

  private static DateTime getDateTimeOfWeekAgo() {
    // set seven days ago
    long dayInMillSeconds = 1000L * 60L * 60L * 24L;
    return new DateTime(System.currentTimeMillis() - (7L * dayInMillSeconds));
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
  public List<YouTubePO> fetchVideosByQuery(String queryTerm) throws IOException {
    String youtubeVideoSearchSetFields = this.env
      .getProperty("YouTube.VideoSearchSetFields"); //$NON-NLS-1$
    String categoryNewsPolitics = this.env
      .getProperty("YouTube.CategoryNewsPolitics"); //$NON-NLS-1$
    String orderBy = this.env.getProperty("YouTube.OrderBy"); //$NON-NLS-1$
    String videoEmbeddableTrue = this.env
      .getProperty("YouTube.VideoEmbeddableTrue"); //$NON-NLS-1$
    String videoTypeModerate = this.env
      .getProperty("YouTube.VideoTypeModerate"); //$NON-NLS-1$
    String mediaTypeVideo = this.env
      .getProperty("YouTube.MediaTypeVideo"); //$NON-NLS-1$
    String idSnippet = this.env.getProperty("YouTube.IdSnippet"); //$NON-NLS-1$
    String englishLanguage = this.env.getProperty("App.English.Language"); //$NON-NLS-1$
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

  private List<YouTubePO> executeYouTubeSearch(YouTube.Search.List search) throws IOException {
    List<YouTubePO> videos = new ArrayList<>();
    // perform the search and parse the results
    SearchListResponse searchResponse = search.execute();
    List<SearchResult> searchResultList = searchResponse.getItems();
    if (null == searchResultList)
      return videos;
    for (SearchResult result : searchResultList) {
      YouTubePO video = convertResultToYouTubePO(result);
      if (!isYouTubeVideoDuplicate(video.getTitle(), videos)
        && !TextUtility
        .isThisUnicode(video.getTitle(), Character.UnicodeBlock.DEVANAGARI)) {
        videos.add(video);
      }
    }
    videos.sort((a, b) -> b.getPublishedDate().compareTo(a.getPublishedDate()));
    return videos;
  }

  private YouTubePO convertResultToYouTubePO(SearchResult result) {
    YouTubePO video = new YouTubePO();

    video.setVideoId(result.getId().getVideoId());
    String title = result.getSnippet().getTitle();
    video.setTitle(title);
    video.setUrl(buildVideoUrl(result.getId().getVideoId()));
    if (null != result.getSnippet().getThumbnails().getHigh())
      video.setThumbnailUrl(result.getSnippet().getThumbnails().getHigh().getUrl());
    else {
      video.setThumbnailUrl(result.getSnippet().getThumbnails().getDefault().getUrl());
    }
    video.setPublishedDate(DateUtility
      .convertToLocalDateTime(result.getSnippet().getPublishedAt().getValue()));
    video.setDescription(result.getSnippet().getDescription());
    return video;
  }

  /**
   * Constructs the URL to play the YouTube video
   */
  public String buildVideoUrl(String videoId) {
    String youtubeVideoUrlPrefix = this.env
      .getProperty("YouTube.VideoUrlPrefix"); //$NON-NLS-1$
    return youtubeVideoUrlPrefix + videoId;
  }

  /**
   * Instantiates the YouTube object
   */
  @SuppressWarnings("squid:S1611")
  private YouTube getYouTube() {
    String youtubeSpringApp = this.env.getProperty("YouTube.AppName"); //$NON-NLS-1$
    return new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), (request) -> {
      /* empty block */
    }).setApplicationName(youtubeSpringApp).build();
  }

  /**
   * take a piece of the youTube video title check if it is already in the youTube
   * video list if it's there, it's duplicate, ignore it
   */
  private boolean isYouTubeVideoDuplicate(String youTubeTitle, List<YouTubePO> videos) {
    String regexSequenceOfWhiteCharacters = this.env
      .getProperty("Twitter.RegexSequenceOfWhiteCharacters"); //$NON-NLS-1$
    String middleOfTitleString = TextUtility.getMiddleOfText(youTubeTitle);
    List<String> youTubeTitles = videos.stream().map(YouTubePO::getTitle)
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
