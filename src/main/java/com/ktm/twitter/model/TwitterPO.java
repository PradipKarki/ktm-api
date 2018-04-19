package com.ktm.twitter.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TWITTER")
public class TwitterPO {

	@Id
	@Column(name="TWITTER_ID", nullable=false)
	private long id;
	private String title;
	private String imageUri;
	private String articleUri;
	@Temporal(TemporalType.TIMESTAMP)
	private Date publishedDate;
    @ManyToOne
    @JoinColumn(name="TWITTER_USER_ID", nullable=false)
    private TwitterUser twitterUser;
	
	public TwitterPO() {
		super();
	}

	public TwitterPO(long id, String title, String imageURI, String articleURI, Date publishedDate, TwitterUser twitterUser) {
		this.id = id;
		this.title = title;
		this.imageUri = imageURI;
		this.articleUri = articleURI;
		this.publishedDate = publishedDate;
		this.twitterUser = twitterUser;
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
	
	public TwitterUser getTwitterUser() {
		return this.twitterUser;
	}
	public void setTwitterUser(TwitterUser twitterUser) {
		this.twitterUser = twitterUser;
	}

}
