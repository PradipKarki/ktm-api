package com.ktm.rest.mail.service.impl;

import com.ktm.rest.mail.model.EmailPo;
import com.ktm.rest.mail.service.EmailService;
import org.simplejavamail.MailException;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.springsupport.SimpleJavaMailSpringSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@Configuration
@PropertySource("classpath:simplejavamail.yml")
@Import(SimpleJavaMailSpringSupport.class)
public class EmailServiceImpl implements EmailService {
  private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

  @Autowired private Mailer mailer; // configured completely using default properties

  @Override
  public void sendMail(EmailPo myEmail) {
    Email email =
        EmailBuilder.startingBlank()
            .from(myEmail.getFromName(), myEmail.getFromAddress())
            .to(myEmail.getToName(), myEmail.getToAddress())
            //		          .withRecipients(myEmail.getRecipients())
            .withSubject(myEmail.getSubject())
            .withHTMLText(myEmail.getHtmlText())
            .withPlainText(myEmail.getText())
            //		          .withEmbeddedImage("wink1",
            // Base64.getDecoder().decode(myEmail.getBase64String()), "image/png")
            //		          .withHeader("X-Priority", myEmail.getHeaders().get("X-Priority"))
            .withBounceTo(myEmail.getFromAddress())
            .buildEmail();
    try {
      this.mailer.sendMail(email);
    } catch (MailException e) {
      logger.error("error");
    }
  }
}
