package com.ktm.library.core.utils;

public class AppUtils {

  private AppUtils() {}

  /**
   * Constructs the URL to play the YouTube video.
   *
   * @param videoId ID of the YouTube
   * @return URL of the YouTUBe video
   */
  public static String buildYouTubeUrl(String videoId) {
    return CoreApiConstants.YOUTUBE_VIDEO_URL_PREFIX + videoId;
  }
}
