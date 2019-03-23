package com.ktm.library.core.mapper.email;

import com.ktm.library.core.model.email.EmailDto;
import com.ktm.library.core.model.email.EmailPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmailMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "sendAt", ignore = true)
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  EmailPo toEmailPo(EmailDto emailDto);
}
