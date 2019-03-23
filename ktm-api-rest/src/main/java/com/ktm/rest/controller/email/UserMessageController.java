package com.ktm.rest.controller.email;

import static com.ktm.rest.ApiConstants.EMAIL;
import static org.mapstruct.factory.Mappers.getMapper;

import com.ktm.library.core.mapper.email.UserMessageMapper;
import com.ktm.library.core.model.email.UserMessage;
import com.ktm.library.core.model.email.UserMessageDto;
import com.ktm.library.core.repository.email.UserMessageRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
@RefreshScope
@Api(tags = EMAIL)
public class UserMessageController {
  @Autowired private UserMessageRepository userMessageRepository;

  @PostMapping
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Save Message Send by Users in Database")
  public void saveUserMessage(@Valid @RequestBody UserMessageDto userMessageDto) {
    UserMessage userMessage = getMapper(UserMessageMapper.class).toUserMessage(userMessageDto);
    this.userMessageRepository.save(userMessage);
  }
}
