package com.ktm.rest.mail.service;

import com.ktm.rest.mail.model.EmailSubscriber;

public interface EmailConfirmationService {

  void setEmailSubscriberStatusToConfirmed(EmailSubscriber emailSubscriber);

  void extendExpTimeAndIssueNewToken(EmailSubscriber emailSubscriber);

  void sendEmailStatingEmailIsVerified(String toAddress);

  void sendReConfirmationEmail(String toAddress);
}
