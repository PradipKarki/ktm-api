package com.ktm.library.core.model.share;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserShare extends UserEntity {
  private static final long serialVersionUID = 1L;

  @NotEmpty
  private String socialMediaName;

  public UserShare() {
    super();
  }

  public UserShare(String userId, String fullName, String emailAddress, String shareLocation) {
    super("UserShare", userId, fullName, emailAddress);
    this.socialMediaName = shareLocation;
  }

}
