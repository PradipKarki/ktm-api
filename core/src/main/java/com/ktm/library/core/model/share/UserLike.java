package com.ktm.library.core.model.share;

import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserLike extends UserEntity {
  private static final long serialVersionUID = 1L;

  private boolean isLiked;

  public UserLike() {
    super();
  }

  public UserLike(String userId, String fullName, String emailAddress, boolean isLiked) {
    super("UserLike", userId, fullName, emailAddress);
    this.isLiked = isLiked;
  }

}
