package com.ktm.rss.model;

import com.rometools.rome.feed.synd.SyndImage;
import com.rometools.rome.feed.synd.SyndPerson;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

@Entity
public class RssNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RSS_NEWS_ID")
    private long id;
    @ElementCollection
    @Transient
    private List<SyndPerson> authors;
    @ElementCollection
    @Transient
    private List<SyndPerson> contributors;
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "RSS_NEWS_ID"))
    private List<String> tags;
    private String feedType;
    private String comments;
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "RSS_NEWS_ID"))
    private List<String> contents;
    @Transient
    private SyndImage icon;
    @Transient
    private SyndImage image;
    @NotEmpty
    private String title;
    private String description;
    private String uri;
    private LocalDateTime publishedDate;
    private LocalDateTime updatedDate;

    public RssNews() {
        super();
    }

    public RssNews(long id, List<SyndPerson> authors, List<SyndPerson> contributors, List<String> tags,
                   String feedType, String comments, List<String> contents, SyndImage icon, SyndImage image, String title,
                   String description, String uri, LocalDateTime publishedDate, LocalDateTime updatedDate) {
        this.id = id;
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

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SyndPerson> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<SyndPerson> authors) {
        this.authors = authors;
    }

    public List<SyndPerson> getContributors() {
        return this.contributors;
    }

    public void setContributors(List<SyndPerson> contributors) {
        this.contributors = contributors;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getFeedType() {
        return this.feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<String> getContents() {
        return this.contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public SyndImage getIcon() {
        return this.icon;
    }

    public void setIcon(SyndImage icon) {
        this.icon = icon;
    }

    public SyndImage getImage() {
        return this.image;
    }

    public void setImage(SyndImage image) {
        this.image = image;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public LocalDateTime getPublishedDate() {
        return this.publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public LocalDateTime getUpdatedDate() {
        return this.updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

}
