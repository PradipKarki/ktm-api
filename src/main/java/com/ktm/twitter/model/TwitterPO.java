package com.ktm.twitter.model;

import java.util.Date;

public class TwitterPO {
	
	private long id;
	private String title;
	private String imageURI;
	private String articleURI;
	private Date publishDate;
	private String tweetOwner;
	
	public TwitterPO() {
		super();
	}

	public TwitterPO(long id, String title, String imageURI, String articleURI, Date publishDate, String tweetOwner) {
		this.id = id;
		this.title = title;
		this.imageURI = imageURI;
		this.articleURI = articleURI;
		this.publishDate = publishDate;
		this.tweetOwner = tweetOwner;
	}
	
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getImageURI() {
		return this.imageURI;
	}
	public void setImageURI(String imageURI) {
		this.imageURI = imageURI;
	}
	
	public String getArticleURI() {
		return this.articleURI;
	}
	public void setArticleURI(String articleURI) {
		this.articleURI = articleURI;
	}
	
	public Date getPublishDate() {
		return this.publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public String getTweetOwner() {
		return this.tweetOwner;
	}
	public void setTweetOwner(String tweetOwner) {
		this.tweetOwner = tweetOwner;
	}

}
