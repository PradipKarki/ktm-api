package com.ktm.rss.model;

import java.util.Date;
import java.util.List;

import com.rometools.rome.feed.synd.SyndImage;
import com.rometools.rome.feed.synd.SyndPerson;

public class RSSNewsPO {
	private List<SyndPerson> authors;
	private List<SyndPerson> contributors;
	private List<String> tags;
	private String feedType;
	private String comments;
	private List<String> contents;
	private SyndImage icon;
	private SyndImage image;
	private String title;
	private String description;
	private String uri;
	private Date publishedDate;
	private Date updatedDate;

	public RSSNewsPO() {
		super();
	}

	public RSSNewsPO(List<SyndPerson> authors, List<SyndPerson> contributors, List<String> tags,
			String feedType, String comments, List<String> contents, SyndImage icon, SyndImage image, String title,
			String description, String uri, Date publishedDate, Date updatedDate) {
		super();
		this.authors = authors;
		this.contributors = contributors;
		this.tags = tags;
		this.feedType = feedType;
		this.comments = comments;
		this.contents = contents;
		this.icon = icon;
		this.image = image;
		this.title = title;
		this.description = description;
		this.uri = uri;
		this.publishedDate = publishedDate;
		this.updatedDate = updatedDate;
	}

	public List<SyndPerson> getAuthors() {
		return authors;
	}

	public void setAuthors(List<SyndPerson> authors) {
		this.authors = authors;
	}

	public List<SyndPerson> getContributors() {
		return contributors;
	}

	public void setContributors(List<SyndPerson> contributors) {
		this.contributors = contributors;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getFeedType() {
		return feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<String> getContents() {
		return contents;
	}

	public void setContents(List<String> contents) {
		this.contents = contents;
	}

	public SyndImage getIcon() {
		return icon;
	}

	public void setIcon(SyndImage icon) {
		this.icon = icon;
	}

	public SyndImage getImage() {
		return image;
	}

	public void setImage(SyndImage image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
