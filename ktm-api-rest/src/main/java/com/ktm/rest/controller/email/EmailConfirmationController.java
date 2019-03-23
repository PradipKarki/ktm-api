package com.ktm.rest.controller.email;

import static com.ktm.rest.ApiConstants.EMAIL;

import com.ktm.library.core.dictionary.EmailConfirmationStatus;
import com.ktm.library.core.model.email.EmailSubscriber;
import com.ktm.library.core.repository.email.EmailSubscriberRepository;
import com.ktm.library.core.service.EmailConfirmationService;
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
@Api(tags = EMAIL, value = EMAIL)
public class EmailConfirmationController {
  @Autowired private EmailConfirmationService emailConfirmationService;
  @Autowired private EmailSubscriberRepository emailSubscriberRepository;

  @GetMapping("/{token}")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Confirm User Registration by Verifying Email Token")
  public EmailConfirmationStatus confirmRegistration(@PathVariable("token") String token) {
    if (StringUtils.isEmpty(token)) {
      return EmailConfirmationStatus.INVALID_TOKEN;
    }

    Optional<EmailSubscriber> emailSubscriber =
        this.emailSubscriberRepository.findByVerificationToken(token);
    if (!emailSubscriber.isPresent()) {
      return EmailConfirmationStatus.ALREADY_CONFIRMED;
    }
    EmailSubscriber email = emailSubscriber.get();
    if (email.getExpirationDate().isAfter(LocalDateTime.now())) {
      this.emailConfirmationService.extendExpTimeAndIssueNewToken(email);
      this.emailConfirmationService.sendReConfirmationEmail(email.getEmailAddress());
      return EmailConfirmationStatus.CONFIRMATION_DATE_EXPIRED;
    }
    this.emailConfirmationService.setEmailSubscriberStatusToConfirmed(email);
    this.emailConfirmationService.sendEmailStatingEmailIsVerified(email.getEmailAddress());
    return EmailConfirmationStatus.CONFIRMED;
  }
}
