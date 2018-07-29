package com.ktm.rest.rss.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ktm.rest.BaseEntity;
import com.rometools.rome.feed.synd.SyndImage;
import com.rometools.rome.feed.synd.SyndPerson;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RssNews implements BaseEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "RSS_NEWS_ID")
  private Long id;

  @ElementCollection @Transient private transient List<SyndPerson> authors;
  @ElementCollection @Transient private transient List<SyndPerson> contributors;

  @ElementCollection
  @CollectionTable(name = "RSS_NEWS_TAG", joinColumns = @JoinColumn(name = "RSS_NEWS_ID"))
  @Column(name = "TAG")
  private List<String> tags;

  private String feedType;
  private String comment;

  @ElementCollection
  @CollectionTable(name = "RSS_NEWS_CONTENT", joinColumns = @JoinColumn(name = "RSS_NEWS_ID"))
  @Column(name = "CONTENT")
  private List<String> contents;

  @Transient private transient SyndImage icon;
  @Transient private transient SyndImage image;
  @NotEmpty private String title;
  private String description;
  private String uri;
  private LocalDateTime publishedDate;
  private LocalDateTime updatedDate;

  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;
}
