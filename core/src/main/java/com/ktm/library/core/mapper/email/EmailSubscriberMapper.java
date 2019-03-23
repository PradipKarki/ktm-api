package com.ktm.library.core.mapper.email;

import com.ktm.library.core.model.email.EmailSubscriber;
import com.ktm.library.core.model.email.EmailSubscriberDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmailSubscriberMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  EmailSubscriber toEmailSubscriber(EmailSubscriberDto emailSubscriberDto);
}
