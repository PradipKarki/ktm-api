package com.ktm.documentary.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.youtube.model.Video;
import com.ktm.documentary.model.Documentary;
import com.ktm.youtube.service.YouTubeService;

@Service
public class DocumentaryService {
	@Autowired
	private YouTubeService youtubeService;
	
	public Documentary getDocumentaryVideoByVideoId(final String videoId) throws IOException {
		Documentary documentaryVideo = new Documentary();
		Video video = this.youtubeService.getVideoByVideoId(videoId);
		documentaryVideo.setId(video.getId());
		final String title = video.getSnippet().getTitle();
		documentaryVideo.setTitle(title);
		documentaryVideo.setUrl(this.youtubeService.buildVideoUrl(videoId));
		if (video.getSnippet().getThumbnails().getHigh() != null)
			documentaryVideo.setThumbnailUrl(video.getSnippet().getThumbnails().getHigh().getUrl());
		else {
			documentaryVideo.setThumbnailUrl(video.getSnippet().getThumbnails().getDefault().getUrl());
		}
		documentaryVideo.setPublishedDate(new Date(video.getSnippet().getPublishedAt().getValue()));
		documentaryVideo.setDescription(video.getSnippet().getDescription());
		return documentaryVideo;
	}

	public List<Documentary> getDocumentaryVideos(List<String> youTubeDocumentary) throws IOException {
		System.out.println(Arrays.toString(youTubeDocumentary.toArray()));
		List<Documentary> videos = new ArrayList<>();
		int i = 0; 
		for (String videoId : youTubeDocumentary) {
			i++;
			Documentary documentaryVideo = getDocumentaryVideoByVideoId(videoId);
			videos.add(documentaryVideo);
			if (i==3) break;
		}
		return videos;
	}

}
