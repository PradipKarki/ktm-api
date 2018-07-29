package com.ktm.rest.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ktm.rest.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TWITTER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitterPo implements BaseEntity<Long> {

  @Id
  @Column(name = "TWITTER_ID", nullable = false)
  private Long id;

  @NotEmpty private String title;
  private String imageUri;
  private String articleUri;
  private LocalDateTime publishedDate;

  @ManyToOne
  @JoinColumn(name = "TWITTER_USER_ID", nullable = false)
  private TwitterUser twitterUser;

  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;
}
