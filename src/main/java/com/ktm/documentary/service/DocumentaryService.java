package com.ktm.documentary.service;

import com.google.api.services.youtube.model.Video;
import com.ktm.documentary.model.Documentary;
import com.ktm.youtube.mapper.VideoMapper;
import com.ktm.youtube.service.YouTubeService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.mapstruct.factory.Mappers;
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
    Video video = this.youtubeService.getVideoByVideoId(videoId);
    return (Documentary) Mappers.getMapper(VideoMapper.class).toYouTubePo(video);
  }

  public List<Documentary> getDocumentaryVideos(
      List<String> youTubeDocumentary) throws IOException {
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
