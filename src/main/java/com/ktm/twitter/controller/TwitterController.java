package com.ktm.twitter.controller;

import com.ktm.twitter.model.TwitterPO;
import com.ktm.twitter.repository.TwitterRepository;
import com.ktm.twitter.service.TwitterService;
import com.ktm.utils.exception.ResourceNotFoundException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

@RestController
@PropertySource("classpath:messages.properties")
@RequestMapping("/twitter")
@RefreshScope
public class TwitterController {

  @Autowired
  Environment env;
  @Autowired
  TwitterRepository twitterRepository;
  @Autowired
  private TwitterService twitterService;

  @RequestMapping(value = "/nepal", method = RequestMethod.GET)
  @CrossOrigin(origins = "http://localhost:4200")
  public List<TwitterPO> getTweetsNepal() throws TwitterException {
    String searchQuery = this.env
      .getProperty("App.Nepal.TwitterSearchQueryKeyWord"); //$NON-NLS-1$
    return this.twitterService.getTweetsByQuery(searchQuery);
  }

  @RequestMapping(value = "/everest", method = RequestMethod.GET)
  @CrossOrigin(origins = "http://localhost:4200")
  public List<TwitterPO> getTweetsEverest() throws TwitterException {
    String searchQuery = this.env
      .getProperty("App.Everest.TwitterSearchQueryKeyWord"); //$NON-NLS-1$
    return this.twitterService.getTweetsByQuery(searchQuery);
  }

  @RequestMapping(value = "/kathmandu", method = RequestMethod.GET)
  @CrossOrigin(origins = "http://localhost:4200")
  public List<TwitterPO> getTweetsKathmandu() throws TwitterException {
    String searchQuery = this.env
      .getProperty("App.Kathmandu.TwitterSearchQueryKeyWord"); //$NON-NLS-1$
    return this.twitterService.getTweetsByQuery(searchQuery);
  }

  // Get All TwitterPOs
  @GetMapping("/")
  public List<TwitterPO> getAllTwitterPOs() {
    return this.twitterRepository.findAll();
  }

  // Create a new TwitterPO
  @PostMapping("/")
  public TwitterPO createTwitterPO(@Valid @RequestBody TwitterPO twitterPO) {
    return this.twitterRepository.save(twitterPO);
  }

  // Get a Single TwitterPO by Id
  @GetMapping("/{id}")
  public TwitterPO getTwitterPOById(@PathVariable Long id) {
    return this.twitterRepository.findById(id)
                                 .orElseThrow(ResourceNotFoundException::new);
  }

  // Update a TwitterPO
  @PutMapping("/{id}")
  public TwitterPO updateTwitterPO(@PathVariable Long id,
                                   @Valid @RequestBody TwitterPO twitterPODetails) {
    TwitterPO twitterPO = this.twitterRepository.findById(id)
                                                .orElseThrow(ResourceNotFoundException::new);
    twitterPO.setTitle(twitterPODetails.getTitle());
    twitterPO.setImageURI(twitterPODetails.getImageURI());
    twitterPO.setArticleURI(twitterPODetails.getArticleURI());
    twitterPO.setPublishedDate(twitterPODetails.getPublishedDate());
    twitterPO.setTwitterUser(twitterPODetails.getTwitterUser());
    return this.twitterRepository.save(twitterPO);
  }

  // Delete a TwitterPO
  @DeleteMapping("/{id}")
  public ResponseEntity<TwitterPO> deleteTwitterPO(@PathVariable Long id) {
    TwitterPO twitterPO = this.twitterRepository.findById(id)
                                                .orElseThrow(ResourceNotFoundException::new);
    this.twitterRepository.delete(twitterPO);
    return ResponseEntity.ok().build();
  }

}
