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
public class UserComment extends UserEntityInfo {
  @Id
  @Column(name = "USER_COMMENT_ID", nullable = false)
  private long id;
  private String userName;
  @NotEmpty
  private String email;
  @NotEmpty
  private String comment;

  public UserComment() {
    super();
  }

  public UserComment(String entityId, MediaType entityType, String userId, String userName,
      String email, String comment) {
    super(entityId, entityType, userId);
    this.userName = userName;
    this.email = email;
    this.comment = comment;
  }

}
