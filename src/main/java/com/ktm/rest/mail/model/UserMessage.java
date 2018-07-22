package com.ktm.rest.mail.model;

import com.ktm.rest.share.model.UserEntity;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserMessage extends UserEntity {

  @NotEmpty
  @Size(min = 4)
  private String fullName;
  @NotEmpty
  @Email
  private String emailAddress;
  private String phone;
  @NotEmpty
  private String category;
  @NotEmpty
  private String content;
  @NotEmpty
  private String subject;

  public UserMessage() {
    super();
  }

  public UserMessage(String userId, String fullName, String emailAddress, String phone,
      String category, String content, String subject) {
    super("UserMessage", userId, fullName, emailAddress);
    this.phone = phone;
    this.category = category;
    this.content = content;
    this.subject = subject;
  }

}
