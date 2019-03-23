package com.ktm.library.core.service;

import static com.ktm.library.core.utils.CoreApiConstants.REGEX_SEQUENCE_OF_WHITE_CHARACTERS;
import static com.ktm.library.core.utils.PredicateHolder.doesTwitterContainsSimilarWords;
import static java.lang.Character.UnicodeBlock.DEVANAGARI;
import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsLast;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;

import com.ktm.library.core.model.twitter.TwitterPo;
import com.ktm.library.core.utils.TextUtility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TwitterFeedSanitizer implements Sanitizer<TwitterPo> {

  @Value("${Twitter.IrrelevantTwitterUsers}")
  private String[] irrelevantTwitterUsers;

  @Override
  public List<TwitterPo> processData(List<TwitterPo> twitterPos) {
    List<TwitterPo> modifiableTweets = new ArrayList<>();
    Collections.copy(twitterPos, modifiableTweets);

    List<TwitterPo> tweets =
        twitterPos.stream()
            .filter(Objects::nonNull)
            .filter(t -> StringUtils.isNotEmpty(t.getTitle()))
            .filter(t -> !TextUtility.isThisUnicode(t.getTitle(), DEVANAGARI))
            .filter(t -> !isThisTweetFromIrrelevantUsers(t))
            .filter(t -> !isTwitterDuplicateOrSimilar(modifiableTweets, t))
            .sorted(comparing(TwitterPo::getImageUri, nullsLast(reverseOrder())))
            .collect(toList());
    tweets.forEach(t -> t.setTitle(TextUtility.cleanTweet(t.getTitle())));
    return Collections.unmodifiableList(tweets);
  }

  /**
   * Helper method to return if Twitter is similar or Duplicate, and update original twitterPos list
   * with removing duplicate item every iteration.
   *
   * @param twitterPos List of the Twitter objects
   * @param twitterPo Twitter object
   * @return Returns true if given Twitter object is already in the Twitter objects list
   */
  private boolean isTwitterDuplicateOrSimilar(List<TwitterPo> twitterPos, TwitterPo twitterPo) {
    String[] splitStr = twitterPo.getTitle().split(REGEX_SEQUENCE_OF_WHITE_CHARACTERS);
    String titleSubString = TextUtility.extractMiddleText(twitterPo.getTitle());
    long count =
        twitterPos.stream()
            .filter(doesTwitterContainsSimilarWords(splitStr, titleSubString))
            .count();

    return twitterPos.removeIf(
        t -> StringUtils.isAnyEmpty(t.getImageUri(), t.getArticleUri()) && count > 1L);
  }

  private boolean isThisTweetFromIrrelevantUsers(TwitterPo twitterPo) {
    return Arrays.asList(irrelevantTwitterUsers).contains(twitterPo.getTwitterUser().getUserName());
  }
}
