package com.ktm.library.core.model.email;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserMessageDto {

  @NotEmpty private String userId;

  @NotEmpty
  @Size(min = 4)
  private String fullName;

  @NotEmpty @Email private String emailAddress;

  private String phone;

  @NotEmpty private String category;
  @NotEmpty private String content;
  @NotEmpty private String subject;
}
