package com.ktm.twitter.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TwitterUser {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="TWITTER_USER_ID")
	private long id;

	private String userName;
	private String miniProfileImageURL;
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="twitterUser", cascade = CascadeType.ALL)
	private List<TwitterPO> tweets;

	public TwitterUser() {
		super();
	}

	public TwitterUser(String userName, String miniProfileImageURL, String name) {
		super();
		this.userName = userName;
		this.miniProfileImageURL = miniProfileImageURL;
		this.name = name;
	}
	
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getMiniProfileImageURL() {
		return this.miniProfileImageURL;
	}
	public void setMiniProfileImageURL(String miniProfileImageURL) {
		this.miniProfileImageURL = miniProfileImageURL;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<TwitterPO> getTweets() {
		return this.tweets;
	}
	public void setTweets(List<TwitterPO> tweets) {
		this.tweets = tweets;
	}
}
