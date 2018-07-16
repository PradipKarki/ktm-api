package com.ktm.documentary.model;

import com.ktm.youtube.model.YouTubePo;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Documentary extends YouTubePo {

  private boolean isFeatured;

  public Documentary() {
    super();
  }

  public Documentary(YouTubePo youTubePo) {
    super(youTubePo.getVideoId(), youTubePo.getTitle(),
        youTubePo.getUrl(), youTubePo.getThumbnailUrl(),
        youTubePo.getPublishedDate(), youTubePo.getDescription(),
        youTubePo.getLastModifiedDate(), youTubePo.getCreatedDate());
  }
}
