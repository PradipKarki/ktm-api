package com.ktm.library.core.model.twitter;

import com.ktm.library.core.model.BaseDto;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Class to expose endpoints in place of TwitterPO for security purpose */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwitterDto implements BaseDto<Long> {

  private Long id;
  @NotEmpty private String title;
  private String imageUri;
  private String articleUri;
  private LocalDateTime publishedDate;

  private TwitterUser twitterUser;
}
