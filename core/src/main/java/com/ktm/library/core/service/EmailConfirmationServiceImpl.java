package com.ktm.library.core.service;

import com.ktm.library.core.model.email.EmailPo;
import com.ktm.library.core.model.email.EmailSubscriber;
import com.ktm.library.core.repository.email.EmailSubscriberRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailConfirmationServiceImpl implements EmailConfirmationService {
  private static final String FROM_ADDRESS = "support@ktmtimes.com";

  @Autowired private EmailService emailService;
  @Autowired private EmailSubscriberRepository emailSubscriberRepository;

  private static EmailPo buildEmail(String toAddress, String subject, String text) {
    return EmailPo.builder()
        .fromAddress(FROM_ADDRESS)
        .toAddress(toAddress)
        .subject(subject)
        .text(text)
        .build();
  }

  @Override
  public void setEmailSubscriberStatusToConfirmed(EmailSubscriber emailSubscriber) {
    emailSubscriber.setIsConfirmed(Boolean.TRUE);
    emailSubscriber.setExpirationDate(null);
    emailSubscriber.setVerificationToken(null);
    emailSubscriberRepository.save(emailSubscriber);
  }

  @Override
  public void extendExpTimeAndIssueNewToken(EmailSubscriber emailSubscriber) {
    String uniqueId = UUID.randomUUID().toString();
    LocalDateTime expirationDate = LocalDateTime.now().plusWeeks(1L);
    emailSubscriber.setIsConfirmed(Boolean.FALSE);
    emailSubscriber.setExpirationDate(expirationDate);
    emailSubscriber.setVerificationToken(uniqueId);
    emailSubscriberRepository.save(emailSubscriber);
  }

  @Override
  public void sendEmailStatingEmailIsVerified(String toAddress) {
    String subject = "Verify Your emailAddress"; // $NON-NLS-1$
    String text =
        "If you are having any issues with your account, please don't hesitate to "
            + "contact us by replying to this emailAddress. Thanks!"; //$NON-NLS-1$
    emailService.sendMail(buildEmail(toAddress, subject, text));
  }

  @Override
  public void sendReConfirmationEmail(String toAddress) {
    String subject = "Email Confirmation"; // $NON-NLS-1$
    String text =
        "Hey there, Please click the big yellow button below to verify your "
            + "emailAddress "
            + "address. Thanks!"; //$NON-NLS-1$
    emailService.sendMail(buildEmail(toAddress, subject, text));
  }
}
