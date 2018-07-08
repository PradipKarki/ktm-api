package com.ktm.utils;

import java.lang.Character.UnicodeBlock;
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

  public static String getMiddleOfText(String text) {
    // get the middle of the text
    int mid = text.length() / 2;
    String[] parts = {text.substring(0, mid), text.substring(mid)};
    // get the index of the second tweet up to middle
    int indexOfTweet2 = (parts[1].length() / 2) + parts[0].length();
    return text.substring(mid / 2, indexOfTweet2);
  }

  public static boolean isThisUnicode(String text, UnicodeBlock unicodeBlock) {
    boolean isThisUnicodeBlock = false;
    for (char c : text.toCharArray())
      if (Character.UnicodeBlock.of(c) == unicodeBlock) {
        isThisUnicodeBlock = true;
        break;
      }
    return isThisUnicodeBlock;
  }

  public String cleanTweetText(String tweet) {
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
