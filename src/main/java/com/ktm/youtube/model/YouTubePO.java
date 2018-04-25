package com.ktm.youtube.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="YOUTUBE")
public class YouTubePO {

    @Id
	@Column(name="YOUTUBE_ID")
	@NotEmpty
	private String videoId;
    @NotEmpty
	private String title;
	@NotEmpty
	private String url;
	private String thumbnailUrl;
	@Temporal(TemporalType.TIMESTAMP)
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

	@Override
	public String toString() {
		return "YouTubePO [videoId=" + this.videoId + ", title=" + this.title + ", url=" + this.url + ", thumbnailUrl=" + this.thumbnailUrl //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ ", publishedDate=" + this.publishedDate + ", description=" + this.description + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

}
