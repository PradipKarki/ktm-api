package com.ktm.youtube.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="YOUTUBE")
public class YouTubePO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private String videoId;
	private String title;
	private String url;
	private String thumbnailUrl;
	private Date publishedDate;
	private String description;

	public YouTubePO() {
		super();
	}

	public YouTubePO(String videoId, String title, String url, String thumbnailUrl, Date publishedDate,
			String description) {
		this.videoId = videoId;
		this.title = title;
		this.url = url;
		this.thumbnailUrl = thumbnailUrl;
		this.publishedDate = publishedDate;
		this.description = description;
	}

	public String getvideoId() {
		return this.videoId;
	}
	
	public void setvideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return this.thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public Date getPublishedDate() {
		return this.publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
