package com.ktm.youtube.model;

import java.util.Date;

public class YouTubePO {

	private String videoId;
	private String title;
	private String url;
	private String thumbnailUrl;
	private Date publishDate;
	private String description;

	public YouTubePO() {
		super();
	}

	public YouTubePO(String videoId, String title, String url, String thumbnailUrl, Date publishDate,
			String description, String channelId) {
		this.videoId = videoId;
		this.title = title;
		this.url = url;
		this.thumbnailUrl = thumbnailUrl;
		this.publishDate = publishDate;
		this.description = description;
	}

	public String getvideoId() {
		return videoId;
	}
	
	public void setvideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
