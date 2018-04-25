package com.ktm.utils.mail.service;

import org.simplejavamail.MailException;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.springsupport.SimpleJavaMailSpringSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.ktm.utils.mail.model.EmailPO;

@Service
@Configuration
@PropertySource("classpath:simplejavamail.properties")
@Import(SimpleJavaMailSpringSupport.class)
public class EmailService {

	@Autowired
	private Mailer mailer; // configured completely using default properties
	
	public boolean sendMail(EmailPO myEmail) {
		Email email = EmailBuilder.startingBlank()
				  .from(myEmail.getFromName(), myEmail.getFromAddress())
		          .to(myEmail.getToName(), myEmail.getToAddress())
//		          .withRecipients(myEmail.getRecipients())
		          .withSubject(myEmail.getSubject())
		          .withHTMLText(myEmail.getHtmlText())
		          .withPlainText(myEmail.getText())
//		          .withEmbeddedImage("wink1", Base64.getDecoder().decode(myEmail.getBase64String()), "image/png")
//		          .withHeader("X-Priority", myEmail.getHeaders().get("X-Priority"))
		          .withBounceTo(myEmail.getFromAddress())
		          .buildEmail();
		try {
			this.mailer.sendMail(email);
		} catch (MailException e) {
			System.err.println(e.getStackTrace());
			return false;
		}
		return true;
	}

}
