package com.ktm.share.model;

import com.ktm.utils.MediaType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserLike extends UserEntityInfo {
  @Id
  @Column(name = "USER_LIKE_ID", nullable = false)
  private long id;

  public UserLike() {
    super();
  }

  public UserLike(String entityId, MediaType entityType, String userId) {
    super(entityId, entityType, userId);
  }

}
