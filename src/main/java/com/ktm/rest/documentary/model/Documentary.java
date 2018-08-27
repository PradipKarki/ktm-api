package com.ktm.rest.documentary.model;

import static java.util.stream.Collectors.toList;

import com.ktm.rest.youtube.model.YouTubePo;
import java.util.List;
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

  private Documentary(YouTubePo youTubePo) {
    super(youTubePo);
  }

  public static Documentary createInstance(YouTubePo youTubePo) {
    return new Documentary(youTubePo);
  }

  public static List<Documentary> createInstances(List<YouTubePo> youTubePos) {
    return youTubePos.stream().map(Documentary::createInstance).collect(toList());
  }
}
