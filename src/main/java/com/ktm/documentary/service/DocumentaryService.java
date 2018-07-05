package com.ktm.documentary.service;

import com.google.api.services.youtube.model.Video;
import com.ktm.documentary.model.Documentary;
import com.ktm.youtube.service.YouTubeService;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentaryService {
  private static final Logger logger = LoggerFactory.getLogger(DocumentaryService.class);

  @Autowired
  private YouTubeService youtubeService;

  public Documentary getDocumentaryVideoByVideoId(String videoId) throws IOException {
    Documentary documentaryVideo = new Documentary();
    Video video = this.youtubeService.getVideoByVideoId(videoId);
    documentaryVideo.setId(video.getId());
    String title = video.getSnippet().getTitle();
    documentaryVideo.setTitle(title);
    documentaryVideo.setUrl(this.youtubeService.buildVideoUrl(videoId));
    if (null != video.getSnippet().getThumbnails().getHigh())
      documentaryVideo.setThumbnailUrl(video.getSnippet().getThumbnails().getHigh().getUrl());
    else {
      documentaryVideo
        .setThumbnailUrl(video.getSnippet().getThumbnails().getDefault().getUrl());
    }
    LocalDateTime videoPublishedDate =
      Instant.ofEpochMilli(video.getSnippet().getPublishedAt().getValue())
             .atZone(ZoneId.systemDefault()).toLocalDateTime();
    documentaryVideo.setPublishedDate(videoPublishedDate);
    documentaryVideo.setDescription(video.getSnippet().getDescription());
    return documentaryVideo;
  }

  public List<Documentary> getDocumentaryVideos(List<String> youTubeDocumentary) throws IOException {
    if (logger.isInfoEnabled()) {
      logger.info(Arrays.toString(youTubeDocumentary.toArray()));
    }
    List<Documentary> videos = new ArrayList<>();
    int i = 0;
    for (String videoId : youTubeDocumentary) {
      i++;
      Documentary documentaryVideo = getDocumentaryVideoByVideoId(videoId);
      videos.add(documentaryVideo);
      if (i == 3) break;
    }
    return videos;
  }

}
