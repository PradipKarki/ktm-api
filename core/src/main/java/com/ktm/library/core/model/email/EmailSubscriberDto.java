package com.ktm.library.core.model.email;

import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailSubscriberDto {

  @NotEmpty private Boolean isSubscribed;
  @NotEmpty private Boolean isConfirmed;
  @NotEmpty private String verificationToken;
  @NotEmpty private LocalDateTime expirationDate;

  @NotEmpty(message = "emailAddress address can not be empty")
  @Email
  private String emailAddress;
}
