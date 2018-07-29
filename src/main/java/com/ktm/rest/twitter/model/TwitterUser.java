package com.ktm.rest.twitter.model;

import com.ktm.rest.BaseEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TwitterUser implements BaseEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TWITTER_USER_ID")
  private Long id;

  private String userName;
  private String miniProfileImageURL;
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "twitterUser", cascade = CascadeType.ALL)
  private List<TwitterPo> tweets;

  public TwitterUser(String userName, String miniProfileImageURL, String name) {
    this.userName = userName;
    this.miniProfileImageURL = miniProfileImageURL;
    this.name = name;
  }
}
