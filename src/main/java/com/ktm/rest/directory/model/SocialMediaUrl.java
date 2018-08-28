package com.ktm.rest.directory.model;

import com.ktm.rest.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialMediaUrl implements BaseEntity<Long> {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SOCIAL_MEDIA_URL_ID")
  private Long id;

  private String faceBookUrl;
  private String twitterUrl;
  private String linkedInUrl;
  private String youTubeUrl;
  private String instagramUrl;
  private String googlePlusUrl;
  private String otherUrl;

  @ManyToOne
  @JoinColumn(name = "FK_ID", insertable = false, updatable = false)
  private Directory directory;
}
