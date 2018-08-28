package com.ktm.rest.share.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserComment extends UserEntity {
  private static final long serialVersionUID = 1L;

  @NotEmpty
  private String emailAddress;
  @NotEmpty
  private String comment;

  public UserComment() {
    super();
  }

  public UserComment(String userId, String fullName, String emailAddress, String comment) {
    super("UserComment", userId, fullName, emailAddress);
    this.comment = comment;
  }

}
