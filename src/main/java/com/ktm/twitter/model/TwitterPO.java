package com.ktm.twitter.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TWITTER")
public class TwitterPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String imageUri;
	private String articleUri;
	private Date publishedDate;
	private String tweetOwner;
	
	public TwitterPO() {
		super();
	}

	public TwitterPO(long id, String title, String imageURI, String articleURI, Date publishedDate, String tweetOwner) {
		this.id = id;
		this.title = title;
		this.imageUri = imageURI;
		this.articleUri = articleURI;
		this.publishedDate = publishedDate;
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
		return this.imageUri;
	}
	public void setImageURI(String imageURI) {
		this.imageUri = imageURI;
	}
	
	public String getArticleURI() {
		return this.articleUri;
	}
	public void setArticleURI(String articleURI) {
		this.articleUri = articleURI;
	}
	
	public Date getPublishedDate() {
		return this.publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	
	public String getTweetOwner() {
		return this.tweetOwner;
	}
	public void setTweetOwner(String tweetOwner) {
		this.tweetOwner = tweetOwner;
	}

}
