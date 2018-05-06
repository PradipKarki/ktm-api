package com.ktm.utils.mail.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktm.utils.mail.model.EmailPO;
import com.ktm.utils.mail.model.EmailSubscriber;
import com.ktm.utils.mail.repository.EmailSubscriberRepository;

@Service
public class EmailConfirmationService {
	@Autowired
	private EmailService emailService;
	@Autowired
	private EmailSubscriberRepository emailSubscriberRepository;
	private String fromAddress = "support@ktmtimes.com"; //$NON-NLS-1$
	
	public void setEmailSubscriberStatusToConfirmed(EmailSubscriber emailSubscriber) {
		emailSubscriber.setConfirmed(true);
		emailSubscriber.setExpirationDate(null);
		emailSubscriber.setVerifyToken(null);
		this.emailSubscriberRepository.save(emailSubscriber);
	}
	
	public void extendExpTimeAndIssueNewToken(EmailSubscriber emailSubscriber) {
		String uniqueId = UUID.randomUUID().toString();
		Date expirationDate = Date.from(LocalDateTime.now().plusWeeks(1).toInstant(ZoneOffset.UTC));
		emailSubscriber.setConfirmed(false);
		emailSubscriber.setExpirationDate(expirationDate);
		emailSubscriber.setVerifyToken(uniqueId);
		this.emailSubscriberRepository.save(emailSubscriber);
	}

	public void sendEmailStatingEmailIsVerified(String toAddress) {
		String subject = "Verify Your email"; //$NON-NLS-1$
		String text = "If you are having any issues with your account, please don't hesitate to contact us by replying to this email. Thanks!"; //$NON-NLS-1$
		EmailPO emailConfirmation = new EmailPO();
		emailConfirmation.setFromAddress(this.fromAddress);
		emailConfirmation.setToAddress(toAddress);
		emailConfirmation.setSubject(subject);
		emailConfirmation.setText(text);
		this.emailService.sendMail(emailConfirmation);
	}

	public void sendReConfirmationEmail(String toAddress) {
		String subject = "Email Confirmation"; //$NON-NLS-1$
		String text = "Hey there, Please click the big yellow button below to verify your email address. Thanks!"; //$NON-NLS-1$
		EmailPO emailReConfirmation = new EmailPO();
		emailReConfirmation.setFromAddress(this.fromAddress);
		emailReConfirmation.setToAddress(toAddress);
		emailReConfirmation.setSubject(subject);
		emailReConfirmation.setText(text);
		this.emailService.sendMail(emailReConfirmation);
	}

}
