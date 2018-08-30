package com.ktm.rest.job.youtube.service.impl;

import static com.ktm.utils.PredicateHolder.doesYouTubeContainsSimilarWords;
import static java.lang.Character.UnicodeBlock.DEVANAGARI;
import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsLast;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import com.google.api.services.youtube.model.SearchResult;
import com.google.common.collect.ImmutableList;
import com.ktm.exception.JobException;
import com.ktm.rest.job.youtube.builder.YouTubeApiBuilder;
import com.ktm.rest.job.youtube.service.YouTubeApiService;
import com.ktm.rest.youtube.model.YouTubePo;
import com.ktm.rest.youtube.repository.YouTubeRepository;
import com.ktm.utils.TextUtility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class YouTubeApiServiceImpl implements YouTubeApiService {
  private static final Logger logger = LoggerFactory.getLogger(YouTubeApiServiceImpl.class);

  @Value("${YouTube.VideoUrlPrefix}")
  private String youtubeVideoUrlPrefix;

  @Value("${Twitter.RegexSequenceOfWhiteCharacters}")
  private String regexSequenceOfWhiteCharacters;

  @Autowired private YouTubeApiBuilder youTubeApiBuilder;
  @Autowired private YouTubeRepository youTubeRepository;

  @Override
  public List<SearchResult> getDataFromAPI(String queryString) {
    try {
      return Collections.unmodifiableList(youTubeApiBuilder.getSearchResults(queryString));
    } catch (IOException e) {
      String errorMessage = "Job failed during fetching videos from YouTube API.";
      logger.error(String.format("Error Message %s %s", errorMessage, e.getMessage()), e);
      throw new JobException(errorMessage, e);
    }
  }

  @Override
  public List<YouTubePo> processData(List<YouTubePo> youTubePos) {
    List<YouTubePo> modifiableYouTubePos = new ArrayList<>(youTubePos);
    return modifiableYouTubePos
        .stream()
        .filter(y -> StringUtils.isNotEmpty(y.getTitle()))
        .filter(y -> !TextUtility.isThisUnicode(y.getTitle(), DEVANAGARI))
        .filter(y -> !isYouTubeDuplicateOrSimilar(youTubePos, y))
        .sorted(comparing(YouTubePo::getPublishedDate, nullsLast(reverseOrder())))
        .collect(collectingAndThen(toList(), ImmutableList::copyOf));
  }

  @Override
  public void saveToDB(List<YouTubePo> youTubePos) {
    youTubeRepository.saveAll(youTubePos);
  }

  /**
   * Helper method to return if YouTube is similar or Duplicate, and update original YouTubePos list
   * with removing duplicate item every iteration.
   *
   * @param youTubePos List of the YouTube Videos
   * @param youTubePo YouTube video
   * @return Returns true if given YouTube video is already in the YouTube Videos list
   */
  private boolean isYouTubeDuplicateOrSimilar(List<YouTubePo> youTubePos, YouTubePo youTubePo) {
    String[] splitStr = youTubePo.getTitle().split(regexSequenceOfWhiteCharacters);
    String titleSubString = TextUtility.extractMiddleText(youTubePo.getTitle());
    long count =
        youTubePos
            .stream()
            .filter(doesYouTubeContainsSimilarWords(splitStr, titleSubString))
            .count();

    return youTubePos.removeIf(y -> count > 1L);
  }
}
