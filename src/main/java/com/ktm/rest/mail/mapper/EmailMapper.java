package com.ktm.rest.mail.mapper;

import com.ktm.rest.mail.model.EmailDto;
import com.ktm.rest.mail.model.EmailPo;
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
