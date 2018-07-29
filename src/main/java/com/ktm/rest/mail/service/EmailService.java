package com.ktm.rest.mail.service;

import com.ktm.rest.mail.model.EmailPo;

public interface EmailService {

  void sendMail(EmailPo myEmail);
}
