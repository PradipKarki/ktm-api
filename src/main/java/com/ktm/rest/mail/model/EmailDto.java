package com.ktm.rest.mail.model;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.simplejavamail.email.Recipient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class EmailDto {

  private String fromName;
  @NotEmpty @Email private String fromAddress;
  private String toName;
  @NotEmpty @Email private String toAddress;
  private String bounceToAddress;
  private List<Recipient> recipients;

  private String text;
  private String htmlText;

  private String subject;

  private String base64String;
  private Map<String, String> headers;
}
