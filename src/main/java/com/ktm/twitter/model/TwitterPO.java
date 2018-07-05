package com.ktm.twitter.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "TWITTER")
public class TwitterPO {

    @Id
    @Column(name = "TWITTER_ID", nullable = false)
    private long id;
    @NotEmpty
    private String title;
    private String imageUri;
    private String articleUri;
    private LocalDateTime publishedDate;
    @ManyToOne
    @JoinColumn(name = "TWITTER_USER_ID", nullable = false)
    private TwitterUser twitterUser;

    public TwitterPO() {
    }

    public TwitterPO(long id, String title, String imageURI, String articleURI, LocalDateTime publishedDate, TwitterUser twitterUser) {
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

    public LocalDateTime getPublishedDate() {
        return this.publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public TwitterUser getTwitterUser() {
        return this.twitterUser;
    }

    public void setTwitterUser(TwitterUser twitterUser) {
        this.twitterUser = twitterUser;
    }

}
