package com.ktm.library.core.utils;

import java.util.regex.Pattern;

public final class CoreApiConstants {
  public static final String EMAIL = "email";
  public static final String YOUTUBE = "youtube";
  public static final String TWITTER = "twitter";
  public static final String DOCUMENTARY = "documentary";
  public static final String RSS = "rss news";
  public static final String DIRECTORY = "directory";

  public static final String SEPARATOR = ",";
  public static final String HTTP_PREFIX = "http";
  public static final String YOUTUBE_VIDEO_URL_PREFIX = "https://www.youtube.com/watch?v=";
  public static final String REGEX_SEQUENCE_OF_WHITE_CHARACTERS = "\\s+";
  public static final String SPECIAL_CHARACTERS_EXCEPT_SPACES = "[^a-zA-Z0-9 ]+";
  public static final Pattern SPECIAL_CHARACTERS_PATTERN =
      Pattern.compile(SPECIAL_CHARACTERS_EXCEPT_SPACES);
  public static final String RT_KEYWORD = "RT";
  public static final Pattern RT_KEYWORD_PATTERN = Pattern.compile(RT_KEYWORD);

  private CoreApiConstants() {}
}
