package com.ktm.library.core.model;

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
  private static final long serialVersionUID = 1L;

  private boolean isFeatured;

  public Documentary() {
    super();
  }
}
