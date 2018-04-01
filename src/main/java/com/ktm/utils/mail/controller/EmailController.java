package com.ktm.utils.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktm.utils.mail.model.EmailPO;
import com.ktm.utils.mail.service.EmailService;

@RestController
@RequestMapping("/mail")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/mail/support", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean sendMailFromSupportDomain(@RequestBody EmailPO myEmail) {
		emailService.sendMail(myEmail);
		return true;
	}
	
	@RequestMapping(value = "/mail/news", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public boolean sendMailFromNewsDomain(@RequestBody EmailPO myEmail) {
		emailService.sendMail(myEmail);
		return true;
	}

}
