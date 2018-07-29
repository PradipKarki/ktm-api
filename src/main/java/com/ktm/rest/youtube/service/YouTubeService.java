package com.ktm.rest.youtube.service;

import com.google.api.services.youtube.model.Video;
import com.ktm.rest.ApiConstants;
import com.ktm.rest.youtube.model.YouTubePo;
import java.io.IOException;
import java.util.List;

public interface YouTubeService {

  /**
   * Constructs the URL to play the YouTube video.
   *
   * @param videoId ID of the YouTube
   * @return URL of the YouTUBe video
   */
  static String buildVideoUrl(String videoId) {
    return ApiConstants.YOUTUBE_VIDEO_URL_PREFIX + videoId;
  }

  List<YouTubePo> fetchVideosByQuery(String queryTerm) throws IOException;

  Video getVideoByVideoId(String videoId) throws IOException;
}
