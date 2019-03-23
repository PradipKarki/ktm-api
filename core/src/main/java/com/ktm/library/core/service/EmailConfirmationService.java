package com.ktm.library.core.service;

import com.ktm.library.core.model.email.EmailSubscriber;

public interface EmailConfirmationService {

  void setEmailSubscriberStatusToConfirmed(EmailSubscriber emailSubscriber);

  void extendExpTimeAndIssueNewToken(EmailSubscriber emailSubscriber);

  void sendEmailStatingEmailIsVerified(String toAddress);

  void sendReConfirmationEmail(String toAddress);
}
