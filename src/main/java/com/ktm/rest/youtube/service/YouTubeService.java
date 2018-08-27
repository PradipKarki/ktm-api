package com.ktm.rest.youtube.service;

import com.ktm.rest.ApiConstants;
import com.ktm.rest.CrudCollectionService;
import com.ktm.rest.CrudService;
import com.ktm.rest.youtube.model.YouTubePo;

public interface YouTubeService
    extends CrudService<String, YouTubePo>, CrudCollectionService<YouTubePo> {

  /**
   * Constructs the URL to play the YouTube video.
   *
   * @param videoId ID of the YouTube
   * @return URL of the YouTUBe video
   */
  static String buildVideoUrl(String videoId) {
    return ApiConstants.YOUTUBE_VIDEO_URL_PREFIX + videoId;
  }
}
