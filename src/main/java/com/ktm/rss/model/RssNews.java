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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class RssNews {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "RSS_NEWS_ID")
  @NonNull
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

}
