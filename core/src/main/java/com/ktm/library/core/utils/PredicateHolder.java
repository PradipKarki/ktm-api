package com.ktm.library.core.utils;

import com.ktm.library.core.model.twitter.TwitterPo;
import com.ktm.library.core.model.YouTubePo;
import java.util.function.Predicate;

public final class PredicateHolder {
  private PredicateHolder() {}

  public static Predicate<TwitterPo> doesTwitterContainsSimilarWords(
      String[] splitStr, String titleSubString) {
    return twitterPo ->
        twitterPo.getTitle().contains(titleSubString)
            || TextUtility.containsFirstThreeWords(twitterPo.getTitle(), splitStr)
            || TextUtility.containsLastThreeWords(twitterPo.getTitle(), splitStr);
  }

  public static Predicate<YouTubePo> doesYouTubeContainsSimilarWords(
      String[] splitStr, String titleSubString) {
    return youTubePo ->
        youTubePo.getTitle().contains(titleSubString)
            || TextUtility.containsFirstThreeWords(youTubePo.getTitle(), splitStr)
            || TextUtility.containsLastThreeWords(youTubePo.getTitle(), splitStr);
  }
}
