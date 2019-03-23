package com.ktm.library.core.service;

import static com.ktm.library.core.utils.CoreApiConstants.REGEX_SEQUENCE_OF_WHITE_CHARACTERS;
import static com.ktm.library.core.utils.PredicateHolder.doesYouTubeContainsSimilarWords;
import static java.lang.Character.UnicodeBlock.DEVANAGARI;
import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsLast;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import com.google.common.collect.ImmutableList;
import com.ktm.library.core.model.YouTubePo;
import com.ktm.library.core.utils.TextUtility;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class YouTubeFeedSanitizer implements Sanitizer<YouTubePo> {

  @Value("${YouTube.VideoUrlPrefix}")
  private String youtubeVideoUrlPrefix;

  @Override
  public List<YouTubePo> processData(List<YouTubePo> youTubePos) {
    List<YouTubePo> modifiableYouTubePos = new ArrayList<>(youTubePos);
    return modifiableYouTubePos.stream()
        .filter(y -> StringUtils.isNotEmpty(y.getTitle()))
        .filter(y -> !TextUtility.isThisUnicode(y.getTitle(), DEVANAGARI))
        .filter(y -> !isYouTubeDuplicateOrSimilar(youTubePos, y))
        .sorted(comparing(YouTubePo::getPublishedDate, nullsLast(reverseOrder())))
        .collect(collectingAndThen(toList(), ImmutableList::copyOf));
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
    String[] splitStr = youTubePo.getTitle().split(REGEX_SEQUENCE_OF_WHITE_CHARACTERS);
    String titleSubString = TextUtility.extractMiddleText(youTubePo.getTitle());
    long count =
        youTubePos.stream()
            .filter(doesYouTubeContainsSimilarWords(splitStr, titleSubString))
            .count();

    return youTubePos.removeIf(y -> count > 1L);
  }
}
