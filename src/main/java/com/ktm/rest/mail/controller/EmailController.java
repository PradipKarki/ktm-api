package com.ktm.rest.mail.controller;

import static com.ktm.rest.ApiConstants.EMAIL;

import com.ktm.rest.mail.mapper.EmailMapper;
import com.ktm.rest.mail.model.EmailDto;
import com.ktm.rest.mail.model.EmailPo;
import com.ktm.rest.mail.repository.EmailRepository;
import com.ktm.rest.mail.service.impl.EmailServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RefreshScope
@Api(tags = EMAIL)
public class EmailController {

  @Autowired private EmailServiceImpl emailServiceImpl;
  @Autowired private EmailRepository emailRepository;

  @PostMapping("/support")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Send Email to Users from Support Domain")
  public void sendMailFromSupportDomain(@RequestBody EmailDto emailDto) {
    sendMail(emailDto);
  }

  @PostMapping("/news")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Send Email to Users from News Domain")
  public void sendMailFromNewsDomain(@RequestBody EmailDto emailDto) {
    sendMail(emailDto);
  }

  private void sendMail(EmailDto emailDto) {
    EmailPo myEmail = Mappers.getMapper(EmailMapper.class).toEmailPo(emailDto);
    this.emailServiceImpl.sendMail(myEmail);
    this.emailRepository.save(myEmail);
  }
}
