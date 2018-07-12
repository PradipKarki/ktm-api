package com.ktm.share.model;

import com.ktm.utils.MediaType;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
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

}
