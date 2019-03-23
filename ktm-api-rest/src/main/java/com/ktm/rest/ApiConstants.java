package com.ktm.rest;

public final class ApiConstants {
  public static final String EMAIL = "email";
  public static final String YOUTUBE = "youtube";
  public static final String TWITTER = "twitter";
  public static final String DOCUMENTARY = "documentary";
  public static final String RSS = "rss news";
  public static final String DIRECTORY = "directory";

  private ApiConstants() {}

  public static final class EndPoints {
    public static final String TWITTER_ENDPOINT = "/twitter/";
    public static final String DIRECTORY_ENDPOINT = "/directory/";
    public static final String NEWS_INTERNATIONAL_ENDPOINT = "/news/international/";
    public static final String NEWS_NATIONAL_ENDPOINT = "/news/national/";
    public static final String YOUTUBE_ENDPOINT = "/youtube/";
    public static final String YOUTUBE_NEPAL_ENDPOINT = "/youtube/nepal";

    private EndPoints() {}
  }
}
