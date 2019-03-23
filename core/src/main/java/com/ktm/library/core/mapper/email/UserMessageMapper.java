package com.ktm.library.core.mapper.email;

import com.ktm.library.core.model.email.UserMessage;
import com.ktm.library.core.model.email.UserMessageDto;
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
