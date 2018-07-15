package com.ktm.utils;

import java.lang.Character.UnicodeBlock;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TextUtility {

  private static final String EMPTY_STRING = "";
  @Value("${TextUtility.HttpPrefix}")
  private String httpPrefix;
  @Value("${TextUtility.SpecialCharactersExceptSpaces}")
  private String specialCharactersExceptSpaces;
  @Value("${TextUtility.RTWord}")
  private String rtKeyword;

  public static String extractMiddleText(String text) {
    int mid = text.length() / 2;
    String[] parts = {text.substring(0, mid), text.substring(mid)};
    int indexOfMidOfText2 = (parts[1].length() / 2) + parts[0].length();
    return text.substring(mid / 2, indexOfMidOfText2);
  }

  /**
   * Checks if the given text has this specific unicode.
   *
   * @param text         text
   * @param unicodeBlock unicode
   * @return boolean true if unicode is in text
   */
  public static boolean isThisUnicode(String text, UnicodeBlock unicodeBlock) {
    for (char c : text.toCharArray())
      if (Character.UnicodeBlock.of(c) == unicodeBlock) {
        return true;
      }
    return false;
  }

  public static boolean containsFirstThreeWords(String text, String[] words) {
    return words.length > 5 && text.contains(words[1])
        && text.contains(words[2])
        && text.contains(words[3]);
  }

  public static boolean containsLastThreeWords(String text, String[] words) {
    int length = words.length;
    return length > 5 && text.contains(words[length - 1])
        && text.contains(words[length - 2])
        && text.contains(words[length - 3]);
  }

  public static boolean containsSubString(List<String> stringList, String substring) {
    return stringList.stream().findAny()
                     .filter(s -> s.contains(substring))
                     .isPresent();
  }

  /**
   * Strips http url and anything after it
   * If http is the first word, strips everything
   * Strips special characters
   * Strips RT keyword of tweet
   *
   * @param tweet tweet
   * @return cleanTweet cleanTweet
   */
  public String cleanTweet(String tweet) {
    String cleanText = tweet;
    if (cleanText.contains(httpPrefix)) {
      int index = cleanText.indexOf(httpPrefix);
      if (index != 0) cleanText = cleanText.substring(0, index);
    }
    if (cleanText.contains(httpPrefix)) {
      int index = cleanText.indexOf(httpPrefix);
      if (index == 0) return EMPTY_STRING;
    }
    cleanText = cleanText.replaceAll(specialCharactersExceptSpaces, EMPTY_STRING).trim();
    cleanText = cleanText.replaceAll(rtKeyword, EMPTY_STRING);
    return cleanText;
  }

}
