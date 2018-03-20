package com.ktm.twitter.model;

public class TwitterPO {
	long id;
	String title;
	String imageURI;
	String articleURI;
	
	public TwitterPO(long id, String title, String imageURI, String articleURI) {
		super();
		this.id = id;
		this.title = title;
		this.imageURI = imageURI;
		this.articleURI = articleURI;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageURI() {
		return imageURI;
	}
	public void setImageURI(String imageURI) {
		this.imageURI = imageURI;
	}
	public String getArticleURI() {
		return articleURI;
	}
	public void setArticleURI(String articleURI) {
		this.articleURI = articleURI;
	}
	
}
