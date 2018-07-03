package com.ktm.utils.mail.controller;

import com.ktm.utils.mail.model.UserMessage;
import com.ktm.utils.mail.repository.UserMessageRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
@RefreshScope
public class UserMessageController {
    @Autowired
    private UserMessageRepository userMessageRepository;

    @PostMapping("/")
    @CrossOrigin(origins = "http://localhost:4200")
    public void saveUserMessage(@Valid @RequestBody UserMessage userMessage) {
        this.userMessageRepository.save(userMessage);
    }

}
