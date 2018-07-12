package com.ktm.documentary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Documentary {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "DOCUMENTARY_ID")
  @NonNull
  private String id;
  @NotEmpty
  private String title;
  @NotEmpty
  private String url;
  private boolean isFeatured;
  private String thumbnailUrl;
  private LocalDateTime publishedDate;
  private String description;
  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;

}
