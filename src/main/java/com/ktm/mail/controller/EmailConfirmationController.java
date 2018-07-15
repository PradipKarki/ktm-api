package com.ktm.mail.controller;

import static com.dictionary.EmailConfirmationStatus.ALREADY_CONFIRMED;
import static com.dictionary.EmailConfirmationStatus.CONFIRMATION_DATE_EXPIRED;
import static com.dictionary.EmailConfirmationStatus.CONFIRMED;
import static com.dictionary.EmailConfirmationStatus.INVALID_TOKEN;
import static com.ktm.ApiConstants.EMAIL;

import com.dictionary.EmailConfirmationStatus;
import com.ktm.mail.model.EmailSubscriber;
import com.ktm.mail.repository.EmailSubscriberRepository;
import com.ktm.mail.service.EmailConfirmationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify_email")
@RefreshScope
@Api(tags = EMAIL, description = "Save User Message/Email, Send Mail from Support/News Domain, " +
    "Verify new User Email Token, Maintain Subscribe/Unsubscribe List")
public class EmailConfirmationController {
  @Autowired
  private EmailConfirmationService emailConfirmationService;
  @Autowired
  private EmailSubscriberRepository emailSubscriberRepository;

  @GetMapping("/{token}")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Confirm User Registration by Verifying Email Token")
  public EmailConfirmationStatus confirmRegistration(@PathVariable("token") String token) {
    if (StringUtils.isEmpty(token)) {
      return INVALID_TOKEN;
    }

    Optional<EmailSubscriber> emailSubscriber = this.emailSubscriberRepository
        .findByVerifyToken(token);
    if (!emailSubscriber.isPresent()) {
      return ALREADY_CONFIRMED;
    }
    EmailSubscriber email = emailSubscriber.get();
    if (email.getExpirationDate().isAfter(LocalDateTime.now())) {
      this.emailConfirmationService.extendExpTimeAndIssueNewToken(email);
      this.emailConfirmationService.sendReConfirmationEmail(email.getEmailAddress());
      return CONFIRMATION_DATE_EXPIRED;
    }
    this.emailConfirmationService.setEmailSubscriberStatusToConfirmed(email);
    this.emailConfirmationService.sendEmailStatingEmailIsVerified(email.getEmailAddress());
    return CONFIRMED;
  }
}
