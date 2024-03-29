package com.ktm.rest.controller.email;

import static com.ktm.rest.ApiConstants.EMAIL;

import com.ktm.library.core.exception.ResourceNotFoundException;
import com.ktm.library.core.model.email.EmailSubscriber;
import com.ktm.library.core.model.email.EmailSubscriberDto;
import com.ktm.library.core.repository.email.EmailSubscriberRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscribe")
@RefreshScope
@Api(tags = EMAIL)
public class EmailSubscriberController {

  @Autowired private EmailSubscriberRepository emailSubscriberRepository;

  @GetMapping("/emails")
  @ApiOperation("Retrieve all Email Subscribers")
  public List<EmailSubscriber> getAllEmailSubscribers() {
    return this.emailSubscriberRepository.findAll();
  }

  @GetMapping
  @ApiOperation(
      "Retrieve all Active/Inactive Email Subscribers by query parameter active = " + "true/false")
  public List<EmailSubscriber> getAllActiveEmailSubscribers(
      @RequestParam(value = "active", defaultValue = "false") boolean isActive) {
    return this.emailSubscriberRepository.findByIsSubscribed(isActive);
  }

  @PostMapping
  @ApiOperation("Add a new Email Subscriber")
  public EmailSubscriber createEmailSubscriber(
      @Valid @RequestBody EmailSubscriberDto emailSubscriberDto) {
    String emailAddress = emailSubscriberDto.getEmailAddress().toLowerCase();
    try {
      InternetAddress internetAddress = new InternetAddress(emailAddress);
      internetAddress.validate();
    } catch (AddressException ex) {
      throw new ResourceNotFoundException(ex.getMessage(), ex);
    }
    Optional<EmailSubscriber> emailSubscriber =
        this.emailSubscriberRepository.findByEmailAddress(emailAddress);
    if (!emailSubscriber.isPresent()) {
      EmailSubscriber newEmailSubscriber = new EmailSubscriber(emailAddress, true);
      return this.emailSubscriberRepository.save(newEmailSubscriber);
    }
    emailSubscriber.get().setIsSubscribed(Boolean.TRUE);
    return this.emailSubscriberRepository.save(emailSubscriber.get());
  }

  // always be false for requests coming from other apps -> unsubscribe emailAddress
  @PutMapping
  @ApiOperation("Update an Existing Active Email Subscriber to Inactive")
  public EmailSubscriber updateEmailSubscriberStatus(
      @Valid @RequestBody EmailSubscriberDto emailSubscriberDto) {
    String emailAddress = emailSubscriberDto.getEmailAddress().toLowerCase();
    boolean isSubscribed = emailSubscriberDto.getIsSubscribed();
    EmailSubscriber emailSubscriber =
        this.emailSubscriberRepository
            .findByEmailAddress(emailAddress)
            .orElseThrow(ResourceNotFoundException::new);
    emailSubscriber.setIsSubscribed(isSubscribed);
    this.emailSubscriberRepository.save(emailSubscriber);
    return emailSubscriber;
  }
}
