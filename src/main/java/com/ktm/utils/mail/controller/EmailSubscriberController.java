package com.ktm.utils.mail.controller;

import com.ktm.utils.exception.ResourceNotFoundException;
import com.ktm.utils.mail.model.EmailSubscriber;
import com.ktm.utils.mail.repository.EmailSubscriberRepository;
import java.util.List;
import java.util.Optional;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscribe")
@RefreshScope
public class EmailSubscriberController {

    @Autowired
    EmailSubscriberRepository emailSubscriberRepository;

    // Get All EmailSubscribers
    @GetMapping("/")
    public List<EmailSubscriber> getAllEmailSubscribers() {
        return this.emailSubscriberRepository.findAll();
    }

    // Get All EmailSubscribers
    @GetMapping("/active")
    public List<EmailSubscriber> getAllActiveEmailSubscribers() {
        return this.emailSubscriberRepository.findByIsSubscribed(true);
    }

    // Get All EmailSubscribers
    @GetMapping("/inactive")
    public List<EmailSubscriber> getAllInactiveEmailSubscribers() {
        return this.emailSubscriberRepository.findByIsSubscribed(false);
    }

    // Create a new EmailSubscriber
    @PostMapping("/")
    public EmailSubscriber createEmailSubscriber(@Valid @RequestBody EmailSubscriber emailSubscriberDetails) {
        String emailAddress = emailSubscriberDetails.getEmailAddress().toLowerCase();
        try {
            InternetAddress internetAddress = new InternetAddress(emailAddress);
            internetAddress.validate();
        } catch (AddressException ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
        Optional<EmailSubscriber> emailSubscriber = this.emailSubscriberRepository
                .findByEmailAddress(emailAddress);
        if (!emailSubscriber.isPresent()) {
            EmailSubscriber newEmailSubscriber = new EmailSubscriber(emailAddress, true);
            return this.emailSubscriberRepository.save(newEmailSubscriber);
        }
        emailSubscriber.get().setSubscribed(true);
        return this.emailSubscriberRepository.save(emailSubscriber.get());
    }

    // Update a EmailSubscriber, put on unsubscribe list
    // always be false for requests coming from other apps -> unsubscribe email
    @PutMapping("/")
    public EmailSubscriber updateEmailSubscriberStatus(@Valid @RequestBody EmailSubscriber emailSubscriberDetails) {
        String emailAddress = emailSubscriberDetails.getEmailAddress().toLowerCase();
        boolean isSubscribed = emailSubscriberDetails.isSubscribed();
        EmailSubscriber emailSubscriber = this.emailSubscriberRepository
                .findByEmailAddress(emailAddress)
                .orElseThrow(ResourceNotFoundException::new);
        emailSubscriber.setSubscribed(isSubscribed);
        this.emailSubscriberRepository.save(emailSubscriber);
        return emailSubscriber;
    }
}
