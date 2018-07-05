package com.ktm.share.model;

import com.ktm.utils.MediaType;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;

@MappedSuperclass
public abstract class UserEntityInfo {
  @NotEmpty
  private String entityId;
  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "SMALLINT") //smallint= 2 bytes
  @NotEmpty
  private MediaType entityType;
  @NotEmpty
  private String userId;
  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;

  public UserEntityInfo(String entityId, MediaType entityType, String userId) {
    this.entityId = entityId;
    this.entityType = entityType;
    this.userId = userId;
  }

  public String getEntityId() {
    return this.entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public MediaType getEntityType() {
    return this.entityType;
  }

  public void setEntityType(MediaType entityType) {
    this.entityType = entityType;
  }

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

}
