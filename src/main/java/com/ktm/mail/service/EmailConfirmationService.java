package com.ktm.mail.service;

import com.ktm.mail.model.EmailPO;
import com.ktm.mail.model.EmailSubscriber;
import com.ktm.mail.repository.EmailSubscriberRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailConfirmationService {
  private static final String FROM_ADDRESS = "support@ktmtimes.com"; //$NON-NLS-1$
  @Autowired
  private EmailService emailService;
  @Autowired
  private EmailSubscriberRepository emailSubscriberRepository;

  public void setEmailSubscriberStatusToConfirmed(EmailSubscriber emailSubscriber) {
    emailSubscriber.setConfirmed(true);
    emailSubscriber.setExpirationDate(null);
    emailSubscriber.setVerifyToken(null);
    this.emailSubscriberRepository.save(emailSubscriber);
  }

  public void extendExpTimeAndIssueNewToken(EmailSubscriber emailSubscriber) {
    String uniqueId = UUID.randomUUID().toString();
    LocalDateTime expirationDate = LocalDateTime.now().plusWeeks(1L);
    emailSubscriber.setConfirmed(false);
    emailSubscriber.setExpirationDate(expirationDate);
    emailSubscriber.setVerifyToken(uniqueId);
    this.emailSubscriberRepository.save(emailSubscriber);
  }

  public void sendEmailStatingEmailIsVerified(String toAddress) {
    String subject = "Verify Your email"; //$NON-NLS-1$
    String text = "If you are having any issues with your account, please don't hesitate to contact us by replying to this email. Thanks!"; //$NON-NLS-1$
    EmailPO emailConfirmation = new EmailPO();
    emailConfirmation.setFromAddress(FROM_ADDRESS);
    emailConfirmation.setToAddress(toAddress);
    emailConfirmation.setSubject(subject);
    emailConfirmation.setText(text);
    this.emailService.sendMail(emailConfirmation);
  }

  public void sendReConfirmationEmail(String toAddress) {
    String subject = "Email Confirmation"; //$NON-NLS-1$
    String text = "Hey there, Please click the big yellow button below to verify your email address. Thanks!"; //$NON-NLS-1$
    EmailPO emailReConfirmation = new EmailPO();
    emailReConfirmation.setFromAddress(FROM_ADDRESS);
    emailReConfirmation.setToAddress(toAddress);
    emailReConfirmation.setSubject(subject);
    emailReConfirmation.setText(text);
    this.emailService.sendMail(emailReConfirmation);
  }

}
