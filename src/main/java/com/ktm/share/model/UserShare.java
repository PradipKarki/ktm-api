package com.ktm.share.model;

import com.ktm.utils.MediaType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserShare extends UserEntityInfo {
  @Id
  @Column(name = "USER_SHARE_ID", nullable = false)
  private long id;
  @NotEmpty
  private String socialMediaName;

  public UserShare() {
    super();
  }

  public UserShare(String entityId, MediaType entityType, String userId, String shareLocation) {
    super(entityId, entityType, userId);
    this.socialMediaName = shareLocation;
  }

}
