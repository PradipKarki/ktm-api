package com.ktm.twitter.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TWITTER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitterPo {

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

}
