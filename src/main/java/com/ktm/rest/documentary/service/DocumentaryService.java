package com.ktm.rest.documentary.service;

import com.google.api.services.youtube.model.Video;
import com.ktm.rest.documentary.model.Documentary;
import com.ktm.rest.youtube.mapper.VideoMapper;
import com.ktm.rest.youtube.service.YouTubeService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
    return new Documentary(Mappers.getMapper(VideoMapper.class).toYouTubePo(video));
  }

  public List<Documentary> getDocumentaryVideos(
      List<String> youTubeDocumentary) throws IOException {
    if (logger.isInfoEnabled()) {
      logger.info(Arrays.toString(youTubeDocumentary.toArray()));
    }
    // TODO: 7/13/18 this is development code, need to change later
    List<Documentary> videos = new ArrayList<>();
    int i = 0;
    for (String videoId : youTubeDocumentary) {
      i++;
      Documentary documentaryVideo = getDocumentaryVideoByVideoId(videoId);
      videos.add(documentaryVideo);
      if (i == 3) break;
    }
    videos.sort(Comparator.comparing(Documentary::isFeatured));
    return videos;
  }

}
