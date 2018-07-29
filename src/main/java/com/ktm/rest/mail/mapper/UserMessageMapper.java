package com.ktm.rest.mail.mapper;

import com.ktm.rest.mail.model.UserMessage;
import com.ktm.rest.mail.model.UserMessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMessageMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "entityType", ignore = true)
  UserMessage toUserMessage(UserMessageDto userMessageDto);
}
