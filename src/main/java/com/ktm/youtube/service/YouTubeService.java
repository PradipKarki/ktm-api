package com.ktm.youtube.service;

import static java.lang.Character.UnicodeBlock.DEVANAGARI;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.ktm.utils.TextUtility;
import com.ktm.youtube.builder.YouTubeBuilder;
import com.ktm.youtube.mapper.VideoMapper;
import com.ktm.youtube.model.YouTubePo;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class YouTubeService {

  @Value("${YouTube.VideoUrlPrefix}")
  private static String youtubeVideoUrlPrefix;
  @Value("${Twitter.RegexSequenceOfWhiteCharacters}")
  private String regexSequenceOfWhiteCharacters;

  /**
   * Constructs the URL to play the YouTube video.
   *
   * @param videoId ID of the YouTube
   * @return URL of the YouTUBe video
   */
  public static String buildVideoUrl(String videoId) {
    return youtubeVideoUrlPrefix + videoId;
  }

  public List<YouTubePo> fetchVideosByQuery(String queryTerm) throws IOException {
    List<SearchResult> searchResults = new YouTubeBuilder().getSearchResults(queryTerm);
    List<YouTubePo> youTubePos = Mappers.getMapper(VideoMapper.class).toYouTubePo(searchResults);
    return youTubePos.stream()
                     .filter(y -> !TextUtility.isThisUnicode(y.getTitle(), DEVANAGARI))
                     .filter(y -> !isYouTubeDuplicateOrSimilar(youTubePos, y))
                     .sorted(Comparator.comparing(YouTubePo::getPublishedDate))
                     .collect(Collectors.toList());
  }

  public Video getVideoByVideoId(String videoId) throws IOException {
    YouTube.Videos.List search = new YouTubeBuilder().getSearchByVideoId(videoId);
    return search.execute().getItems().get(0);
  }

  /**
   * Helper method to return if YouTube is similar or Duplicate, and update original YouTubePos
   * list with removing duplicate item every iteration.
   *
   * @param youTubePos List of the YouTube Videos
   * @param youTubePo  YouTube video
   * @return Returns true if given YouTube video is already in the YouTube Videos list
   */
  private boolean isYouTubeDuplicateOrSimilar(List<YouTubePo> youTubePos, YouTubePo youTubePo) {
    String[] splitStr = youTubePo.getTitle().split(regexSequenceOfWhiteCharacters);
    String titleSubString = TextUtility.extractMiddleText(youTubePo.getTitle());
    Long count = youTubePos.stream()
                           .filter(y -> y.getTitle().contains(titleSubString) ||
                               TextUtility.containsFirstThreeWords(y.getTitle(), splitStr) ||
                               TextUtility.containsLastThreeWords(y.getTitle(), splitStr))
                           .count();
    if (count > 1L) {
      youTubePos.remove(youTubePo);
      return true;
    }
    return false;
  }

}
