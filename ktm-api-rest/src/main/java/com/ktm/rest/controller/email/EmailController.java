package com.ktm.rest.controller.email;

import static com.ktm.rest.ApiConstants.EMAIL;
import static org.mapstruct.factory.Mappers.getMapper;

import com.ktm.library.core.mapper.email.EmailMapper;
import com.ktm.library.core.model.email.EmailDto;
import com.ktm.library.core.model.email.EmailPo;
import com.ktm.library.core.repository.email.EmailRepository;
import com.ktm.library.core.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

  @Autowired private EmailService emailService;
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
    EmailPo myEmail = getMapper(EmailMapper.class).toEmailPo(emailDto);
    this.emailService.sendMail(myEmail);
    this.emailRepository.save(myEmail);
  }
}
