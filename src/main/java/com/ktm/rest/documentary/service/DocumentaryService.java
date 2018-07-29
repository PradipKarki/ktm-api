package com.ktm.rest.documentary.service;

import com.ktm.rest.documentary.model.Documentary;
import java.io.IOException;
import java.util.List;

public interface DocumentaryService {

  Documentary getDocumentaryVideoByVideoId(String videoId) throws IOException;

  List<Documentary> getDocumentaryVideos(List<String> youTubeDocumentary) throws IOException;
}
