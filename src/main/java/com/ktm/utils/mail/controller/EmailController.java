package com.ktm.utils.mail.controller;

import com.ktm.utils.mail.model.EmailPO;
import com.ktm.utils.mail.repository.EmailRepository;
import com.ktm.utils.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RefreshScope
public class EmailController {

	@Autowired
	private EmailService emailService;
	@Autowired
	private EmailRepository emailRepository;

	@RequestMapping(value = "/support", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
    public void sendMailFromSupportDomain(@RequestBody EmailPO myEmail) {
        sendMail(myEmail);
    }

	@RequestMapping(value = "/news", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
    public void sendMailFromNewsDomain(@RequestBody EmailPO myEmail) {
        sendMail(myEmail);
    }

    private void sendMail(@RequestBody EmailPO myEmail) {
		this.emailService.sendMail(myEmail);
		this.emailRepository.save(myEmail);
	}

}
