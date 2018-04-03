package com.ktm.youtube.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@PropertySource("classpath:messages.properties")
public class YouTubeService {
	
	@Autowired Environment env;
	
	@Value("${youtube.apikey}")
	private String apiValue;

	/**
	 * Returns the first 50 YouTube videos that match the query term
	 * 
	 * @throws IOException
	 */
	public List<YouTubePO> fetchVideosByQuery(String queryTerm) throws IOException {
		final String YOUTUBE_VIDEO_SEARCH_SET_FIELDS = this.env.getProperty("YouTube.VideoSearchSetFields"); //$NON-NLS-1$
		final String CATERGORY_NEWS_POLITICS = this.env.getProperty("YouTube.CatergoryNewsPolitics"); //$NON-NLS-1$
		final String ORDER_BY = this.env.getProperty("YouTube.OrderBy"); //$NON-NLS-1$
		final String VIDEO_EMBEDDABLE_TRUE = this.env.getProperty("YouTube.VideoEmbeddableTrue"); //$NON-NLS-1$
		final String VIDEO_TYPE_MODERATE = this.env.getProperty("YouTube.VideoTypeModerate"); //$NON-NLS-1$
		final String MEDIA_TYPE_VIDEO = this.env.getProperty("YouTube.MediaTypeVideo"); //$NON-NLS-1$
		final String ID_SNIPPET = this.env.getProperty("YouTube.IdSnippet"); //$NON-NLS-1$
		final String ENGLISH_LANGUAGE = this.env.getProperty("App.English.Language"); //$NON-NLS-1$
		final Long MAX_SEARCH_RESULTS = Long.valueOf(50);
		
		List<YouTubePO> videos = new ArrayList<>();
		YouTube youtube = getYouTube();
		YouTube.Search.List search = youtube.search().list(ID_SNIPPET);
		search.setKey(this.apiValue);
		search.setQ(queryTerm);
		search.setType(MEDIA_TYPE_VIDEO);
		search.setSafeSearch(VIDEO_TYPE_MODERATE);
		search.setVideoEmbeddable(VIDEO_EMBEDDABLE_TRUE);
		search.setOrder(ORDER_BY);
		search.setRelevanceLanguage(ENGLISH_LANGUAGE);
		search.setVideoCategoryId(CATERGORY_NEWS_POLITICS);
		search.setFields(YOUTUBE_VIDEO_SEARCH_SET_FIELDS);
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

	public static DateTime getDateTimeOfWeekAgo() {
		// set seven days ago
		long DAY_IN_MS = 1000 * 60 * 60 * 24;
		DateTime lastWeek = new DateTime(System.currentTimeMillis() - (7 * DAY_IN_MS));
		return lastWeek;
	}

	/**
	 * Constructs the URL to play the YouTube video
	 */
	private String buildVideoUrl(String videoId) {
		final String YOUTUBE_VIDEO_URL_PREFIX = this.env.getProperty("YouTube.VideoUrlPrefix"); //$NON-NLS-1$
		StringBuilder builder = new StringBuilder();
		builder.append(YOUTUBE_VIDEO_URL_PREFIX);
		builder.append(videoId);
		return builder.toString();
	}

	/**
	 * Instantiates the YouTube object
	 */
	private YouTube getYouTube() {
		final String YOUTUBE_SPRING_APP = this.env.getProperty("YouTube.AppName"); //$NON-NLS-1$
		YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), (reqeust) -> {
		/*empty block*/}).setApplicationName(YOUTUBE_SPRING_APP).build();
		return youtube;
	}

}
