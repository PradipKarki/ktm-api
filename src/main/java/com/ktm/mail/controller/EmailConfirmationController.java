package com.ktm.mail.controller;

import static com.ktm.ApiConstants.EMAIL;
import static com.ktm.mail.controller.EmailConfirmationStatus.ALREADY_CONFIRMED;
import static com.ktm.mail.controller.EmailConfirmationStatus.CONFIRMATION_DATE_EXPIRED;
import static com.ktm.mail.controller.EmailConfirmationStatus.CONFIRMED;
import static com.ktm.mail.controller.EmailConfirmationStatus.INVALID_TOKEN;

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
@Api(tags = EMAIL, description = "Save User Message/Email, Send Mail from Support/News Domain, Verify new User Email Token, Maintain Subscribe/Unsubscribe List")
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
      // it's already confirmed, show page showing the email is already confirmed
      return ALREADY_CONFIRMED;
    }
    EmailSubscriber email = emailSubscriber.get();
    if (email.getExpirationDate().isAfter(LocalDateTime.now())) {
      // email verification time is expired, extend exp time, re-issue token
      // show page showing the email needs to be confirmed with new link
      this.emailConfirmationService.extendExpTimeAndIssueNewToken(email);
      // send new email verification to confirm
      this.emailConfirmationService.sendReConfirmationEmail(email.getEmailAddress());
      return CONFIRMATION_DATE_EXPIRED;
    }
    // looks good, set to confirmed and null out token and expiry date, send
    // confirmation email
    // show page showing the email is confirmed
    this.emailConfirmationService.setEmailSubscriberStatusToConfirmed(email);
    this.emailConfirmationService.sendEmailStatingEmailIsVerified(email.getEmailAddress());
    return CONFIRMED;
  }
}
