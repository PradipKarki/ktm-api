package com.ktm.rest.share.model;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserRating extends UserEntity {

  @NotEmpty
  @Max(10)
  private int userRatingScore;

  public UserRating() {
    super();
  }

  public UserRating(String userId, String fullName, String emailAddress, int userRatingScore) {
    super("UserRating", userId, fullName, emailAddress);
    this.setUserRatingScore(userRatingScore);
  }

}
