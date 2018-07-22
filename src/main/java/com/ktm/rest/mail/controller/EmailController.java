package com.ktm.rest.mail.controller;

import static com.ktm.ApiConstants.EMAIL;

import com.ktm.rest.mail.model.EmailPo;
import com.ktm.rest.mail.repository.EmailRepository;
import com.ktm.rest.mail.service.EmailService;
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

  @Autowired
  private EmailService emailService;
  @Autowired
  private EmailRepository emailRepository;

  @PostMapping("/support")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Send Email to Users from Support Domain")
  public void sendMailFromSupportDomain(@RequestBody EmailPo myEmail) {
    sendMail(myEmail);
  }

  @PostMapping("/news")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Send Email to Users from News Domain")
  public void sendMailFromNewsDomain(@RequestBody EmailPo myEmail) {
    sendMail(myEmail);
  }

  private void sendMail(@RequestBody EmailPo myEmail) {
    this.emailService.sendMail(myEmail);
    this.emailRepository.save(myEmail);
  }

}
