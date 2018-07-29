package com.ktm.rest.mail.controller;

import static com.ktm.rest.ApiConstants.EMAIL;
import static com.ktm.dictionary.EmailConfirmationStatus.ALREADY_CONFIRMED;
import static com.ktm.dictionary.EmailConfirmationStatus.CONFIRMATION_DATE_EXPIRED;
import static com.ktm.dictionary.EmailConfirmationStatus.CONFIRMED;
import static com.ktm.dictionary.EmailConfirmationStatus.INVALID_TOKEN;

import com.ktm.dictionary.Dictionary;
import com.ktm.rest.mail.model.EmailSubscriber;
import com.ktm.rest.mail.repository.EmailSubscriberRepository;
import com.ktm.rest.mail.service.impl.EmailConfirmationServiceImpl;
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
@Api(
    tags = EMAIL,
    description =
        "Save User Message/Email, Send Mail from Support/News Domain, "
            + "Verify new User Email Token, Maintain Subscribe/Unsubscribe List")
public class EmailConfirmationController {
  @Autowired private EmailConfirmationServiceImpl emailConfirmationServiceImpl;
  @Autowired private EmailSubscriberRepository emailSubscriberRepository;

  @GetMapping("/{token}")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Confirm User Registration by Verifying Email Token")
  public Dictionary confirmRegistration(@PathVariable("token") String token) {
    if (StringUtils.isEmpty(token)) {
      return INVALID_TOKEN;
    }

    Optional<EmailSubscriber> emailSubscriber =
        this.emailSubscriberRepository.findByVerificationToken(token);
    if (!emailSubscriber.isPresent()) {
      return ALREADY_CONFIRMED;
    }
    EmailSubscriber email = emailSubscriber.get();
    if (email.getExpirationDate().isAfter(LocalDateTime.now())) {
      this.emailConfirmationServiceImpl.extendExpTimeAndIssueNewToken(email);
      this.emailConfirmationServiceImpl.sendReConfirmationEmail(email.getEmailAddress());
      return CONFIRMATION_DATE_EXPIRED;
    }
    this.emailConfirmationServiceImpl.setEmailSubscriberStatusToConfirmed(email);
    this.emailConfirmationServiceImpl.sendEmailStatingEmailIsVerified(email.getEmailAddress());
    return CONFIRMED;
  }
}
