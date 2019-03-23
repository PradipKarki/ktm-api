package com.ktm.library.core.service;

import com.ktm.library.core.builder.ApiBuilder;
import com.ktm.library.core.model.email.EmailPo;
import org.apache.commons.lang3.StringUtils;
import org.simplejavamail.MailException;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
  private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

  @Value("${simplejavamail.defaults.from.address}")
  private String fromAddress;

  @Value("${simplejavamail.defaults.from.name}")
  private String fromName;

  @Value("${simplejavamail.defaults.subject}")
  private String subject;

  @Autowired private ApiBuilder<Mailer> emailConfigBuilder;

  public void sendMail(EmailPo myEmail) {
    String adjustedFromName = getDefaultValueIfNotPresent(myEmail.getFromName(), fromName);
    String adjustedFromAddress = getDefaultValueIfNotPresent(myEmail.getFromAddress(), fromAddress);
    String adjustedSubject = getDefaultValueIfNotPresent(myEmail.getSubject(), fromAddress);
    Email email =
        EmailBuilder.startingBlank()
            .from(adjustedFromName, adjustedFromAddress)
            .to(myEmail.getToName(), myEmail.getToAddress())
            .withSubject(adjustedSubject)
            .withHTMLText(myEmail.getHtmlText())
            .withPlainText(myEmail.getText())
            .withBounceTo(myEmail.getFromAddress())
            .buildEmail();
    try {
      emailConfigBuilder.getInstance().sendMail(email);
    } catch (MailException e) {
      LOGGER.error("error");
    }
  }

  private String getDefaultValueIfNotPresent(String value, String defaultValue) {
    return StringUtils.isEmpty(value) ? defaultValue : value;
  }
}
