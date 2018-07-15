package com.ktm.youtube.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "YOUTUBE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YouTubePo {

  @Id
  @Column(name = "YOUTUBE_ID")
  @NotEmpty
  private String videoId;
  @NotEmpty
  private String title;
  @NotEmpty
  private String url;
  private String thumbnailUrl;
  private LocalDateTime publishedDate;
  private String description;

}
