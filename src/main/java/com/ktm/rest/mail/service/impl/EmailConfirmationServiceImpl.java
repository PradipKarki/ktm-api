package com.ktm.rest.mail.service.impl;

import com.ktm.rest.mail.model.EmailPo;
import com.ktm.rest.mail.model.EmailSubscriber;
import com.ktm.rest.mail.repository.EmailSubscriberRepository;
import com.ktm.rest.mail.service.EmailConfirmationService;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailConfirmationServiceImpl implements EmailConfirmationService {
  private static final String FROM_ADDRESS = "support@ktmtimes.com"; // $NON-NLS-1$
  @Autowired private EmailServiceImpl emailServiceImpl;
  @Autowired private EmailSubscriberRepository emailSubscriberRepository;

  @Override
  public void setEmailSubscriberStatusToConfirmed(EmailSubscriber emailSubscriber) {
    emailSubscriber.setIsConfirmed(Boolean.TRUE);
    emailSubscriber.setExpirationDate(null);
    emailSubscriber.setVerificationToken(null);
    this.emailSubscriberRepository.save(emailSubscriber);
  }

  @Override
  public void extendExpTimeAndIssueNewToken(EmailSubscriber emailSubscriber) {
    String uniqueId = UUID.randomUUID().toString();
    LocalDateTime expirationDate = LocalDateTime.now().plusWeeks(1L);
    emailSubscriber.setIsConfirmed(Boolean.FALSE);
    emailSubscriber.setExpirationDate(expirationDate);
    emailSubscriber.setVerificationToken(uniqueId);
    this.emailSubscriberRepository.save(emailSubscriber);
  }

  @Override
  public void sendEmailStatingEmailIsVerified(String toAddress) {
    String subject = "Verify Your emailAddress"; // $NON-NLS-1$
    String text =
        "If you are having any issues with your account, please don't hesitate to "
            + "contact us by replying to this emailAddress. Thanks!"; //$NON-NLS-1$
    EmailPo emailConfirmation =
        EmailPo.builder()
            .fromAddress(FROM_ADDRESS)
            .toAddress(toAddress)
            .subject(subject)
            .text(text)
            .build();
    this.emailServiceImpl.sendMail(emailConfirmation);
  }

  @Override
  public void sendReConfirmationEmail(String toAddress) {
    String subject = "Email Confirmation"; // $NON-NLS-1$
    String text =
        "Hey there, Please click the big yellow button below to verify your "
            + "emailAddress "
            + "address. Thanks!"; //$NON-NLS-1$
    EmailPo emailReConfirmation =
        EmailPo.builder()
            .fromAddress(FROM_ADDRESS)
            .toAddress(toAddress)
            .subject(subject)
            .text(text)
            .build();
    this.emailServiceImpl.sendMail(emailReConfirmation);
  }
}
