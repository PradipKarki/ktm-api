package com.ktm.youtube.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.ktm.youtube.model.YouTubePO;

@Service
@Configuration
@PropertySource("classpath:youtube.properties")
public class YouTubeService {

	private static final String YOUTUBE_SPRING_APP = "com-ktm-youtube-search";
	private static final String YOUTUBE_VIDEO_URL_PREFIX = "https://www.youtube.com/watch?v=";
	private static final String YOUTUBE_VIDEO_SEARCH_SET_FIELDS = "items(id/kind,id/videoId,snippet/title,snippet/description,snippet/publishedAt,snippet/thumbnails/default/url,snippet/thumbnails/high/url)";
	private static final String CATERGORY_NEWS_POLITICS = "25";
	private static final String ORDER_BY = "relevance";
	private static final String VIDEO_EMBEDDABLE_TRUE = "true";
	private static final String VIDEO_TYPE_MODERATE = "moderate";
	private static final String MEDIA_TYPE_VIDEO = "video";
	private static final String YOUTUBE_APIKEY = "youtube.apikey";
	private static final String ID_SNIPPET = "id,snippet";
	private static final String ENGLISH_LANGUAGE = "en";
	private static final long MAX_SEARCH_RESULTS = 50;

	@Autowired
	private Environment env;

	/**
	 * Returns the first 50 YouTube videos that match the query term
	 * 
	 * @throws IOException
	 */
	public List<YouTubePO> fetchVideosByQuery(String queryTerm) throws IOException {
		List<YouTubePO> videos = new ArrayList<>();

		// instantiate youtube object
		YouTube youtube = getYouTube();

		// define what info we want to get
		YouTube.Search.List search = youtube.search().list(ID_SNIPPET);

		// set our credentials
		String apiKey = env.getProperty(YOUTUBE_APIKEY);
		search.setKey(apiKey);

		// set the search term
		search.setQ(queryTerm);

		// we only want moderate kind video results
		search.setType(MEDIA_TYPE_VIDEO);
		search.setSafeSearch(VIDEO_TYPE_MODERATE);
		search.setVideoEmbeddable(VIDEO_EMBEDDABLE_TRUE);
		search.setOrder(ORDER_BY);
		search.setRelevanceLanguage(ENGLISH_LANGUAGE);
		search.setVideoCategoryId(CATERGORY_NEWS_POLITICS);

		// set the fields that we're going to use
		search.setFields(YOUTUBE_VIDEO_SEARCH_SET_FIELDS);

		// set the max results
		search.setMaxResults(MAX_SEARCH_RESULTS);

		DateTime lastWeek = getDateTimeOfWeekAgo();
		search.setPublishedAfter(lastWeek);

		// perform the search and parse the results
		SearchListResponse searchResponse = search.execute();
		List<SearchResult> searchResultList = searchResponse.getItems();
		if (searchResultList == null)
			return videos;
		for (SearchResult result : searchResultList) {
			YouTubePO video = new YouTubePO();
			video.setvideoId(result.getId().getVideoId());
			video.setTitle(result.getSnippet().getTitle());
			video.setUrl(buildVideoUrl(result.getId().getVideoId()));
			if (result.getSnippet().getThumbnails().getHigh() != null)
				video.setThumbnailUrl(result.getSnippet().getThumbnails().getHigh().getUrl());
			else {
				video.setThumbnailUrl(result.getSnippet().getThumbnails().getDefault().getUrl());
			}
			video.setPublishDate(new Date(result.getSnippet().getPublishedAt().getValue()));
			video.setDescription(result.getSnippet().getDescription());
			videos.add(video);
		}
		videos.sort((a, b) -> b.getPublishDate().compareTo(a.getPublishDate()));
		return videos;
	}

	public DateTime getDateTimeOfWeekAgo() {
		// set seven days ago
		long DAY_IN_MS = 1000 * 60 * 60 * 24;
		DateTime lastWeek = new DateTime(System.currentTimeMillis() - (7 * DAY_IN_MS));
		return lastWeek;
	}

	/**
	 * Constructs the URL to play the YouTube video
	 */
	private String buildVideoUrl(String videoId) {
		StringBuilder builder = new StringBuilder();
		builder.append(YOUTUBE_VIDEO_URL_PREFIX);
		builder.append(videoId);
		return builder.toString();
	}

	/**
	 * Instantiates the YouTube object
	 */
	private YouTube getYouTube() {
		YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), (reqeust) -> {
		}).setApplicationName(YOUTUBE_SPRING_APP).build();
		return youtube;
	}

}
