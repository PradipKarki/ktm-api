package com.ktm.rest.mail.mapper;

import com.ktm.rest.mail.model.EmailSubscriber;
import com.ktm.rest.mail.model.EmailSubscriberDto;
import org.mapstruct.Mapping;

public interface EmailSubscriberMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  EmailSubscriber toEmailSubscriber(EmailSubscriberDto emailSubscriberDto);
}
