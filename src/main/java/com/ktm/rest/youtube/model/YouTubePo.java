package com.ktm.rest.youtube.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ktm.rest.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "YOUTUBE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YouTubePo implements BaseEntity<String> {

  @Id @NotEmpty private String id;
  @NotEmpty private String title;
  @NotEmpty private String url;
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
