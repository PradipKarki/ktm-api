package com.ktm.utils;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.lang.Character.UnicodeBlock;
import java.util.List;
import java.util.regex.Pattern;

public final class TextUtility {

  private static final String HTTP_PREFIX = "http";
  private static final String SPECIAL_CHARACTERS_EXCEPT_SPACES = "[^a-zA-Z0-9 ]+";
  private static final String RT_KEYWORD = "RT";
  private static final Pattern SPECIAL_CHARACTERS_PATTERN =
      Pattern.compile(SPECIAL_CHARACTERS_EXCEPT_SPACES);
  private static final Pattern RT_KEYWORD_PATTERN = Pattern.compile(RT_KEYWORD);

  private TextUtility() {}

  public static String extractMiddleText(String text) {
    int mid = text.length() / 2;
    String[] parts = {text.substring(0, mid), text.substring(mid)};
    int indexOfMidOfText2 = (parts[1].length() / 2) + parts[0].length();
    return text.substring(mid / 2, indexOfMidOfText2);
  }

  /**
   * Checks if the given text has this specific unicode.
   *
   * @param text text
   * @param unicodeBlock unicode
   * @return boolean true if unicode is in text
   */
  public static boolean isThisUnicode(String text, UnicodeBlock unicodeBlock) {
    return text.chars().anyMatch(c -> Character.UnicodeBlock.of(c) == unicodeBlock);
  }

  public static boolean containsFirstThreeWords(String text, String[] words) {
    return words.length > 5
        && text.contains(words[0])
        && text.contains(words[1])
        && text.contains(words[2]);
  }

  public static boolean containsLastThreeWords(String text, String[] words) {
    int length = words.length;
    return length > 5
        && text.contains(words[length - 1])
        && text.contains(words[length - 2])
        && text.contains(words[length - 3]);
  }

  public static boolean containsSubString(List<String> stringList, String substring) {
    return stringList.stream().anyMatch(s -> s.contains(substring));
  }

  /**
   * Strips http url and anything after it If http is the first word, strips everything Strips
   * special characters Strips RT keyword of tweet
   *
   * @param tweet tweet
   * @return finalCleanText finalCleanText
   */
  public static String cleanTweet(String tweet) {
    StringBuilder cleanText = new StringBuilder(EMPTY);
    if (tweet.contains(HTTP_PREFIX)) {
      int index = tweet.indexOf(HTTP_PREFIX);
      if (index != 0) {
        cleanText.append(tweet, 0, index);
      }
    }
    String cleansedText =
        SPECIAL_CHARACTERS_PATTERN.matcher(cleanText.toString()).replaceAll(EMPTY).trim();
    return RT_KEYWORD_PATTERN.matcher(cleansedText).replaceAll(EMPTY);
  }
}
