package com.ktm.rest.twitter.model;

import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitterDto {

  @NotEmpty private String title;
  private String imageUri;
  private String articleUri;
  private LocalDateTime publishedDate;

  private TwitterUser twitterUser;
}
