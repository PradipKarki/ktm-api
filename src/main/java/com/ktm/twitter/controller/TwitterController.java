package com.ktm.twitter.controller;

import com.ktm.ApiConstants;
import com.ktm.twitter.model.TwitterPO;
import com.ktm.twitter.repository.TwitterRepository;
import com.ktm.twitter.service.TwitterService;
import com.ktm.utils.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

@RestController
@RequestMapping("/twitter")
@RefreshScope
@Api(tags = ApiConstants.TWITTER, description = "KTM Times Twitter CRUD operations")
public class TwitterController {

  @Value("${App.Nepal.TwitterSearchQueryKeyWord}")
  private String searchNepalQuery;
  @Value("${App.Everest.TwitterSearchQueryKeyWord}")
  private String searchEverestQuery;
  @Value("${App.Kathmandu.TwitterSearchQueryKeyWord}")
  private String searchKathmanduQuery;

  @Autowired
  private TwitterRepository twitterRepository;
  @Autowired
  private TwitterService twitterService;

  @GetMapping("/nepal")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all Tweets Related to Nepal")
  public List<TwitterPO> getTweetsNepal() throws TwitterException {
    return this.twitterService.getTweetsByQuery(searchNepalQuery);
  }

  @GetMapping("/everest")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all Tweets Related to Everest")
  public List<TwitterPO> getTweetsEverest() throws TwitterException {
    return this.twitterService.getTweetsByQuery(searchEverestQuery);
  }

  @GetMapping("/kathmandu")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all Tweets Related to Kathmandu")
  public List<TwitterPO> getTweetsKathmandu() throws TwitterException {
    return this.twitterService.getTweetsByQuery(searchKathmanduQuery);
  }

  @GetMapping("/search/{tweetKeyWord}")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all Tweets Related to Search KeyWord")
  public List<TwitterPO> getTweetsByKeyWord(@PathVariable String tweetKeyWord) throws
    TwitterException {
    return this.twitterService.getTweetsByQuery(tweetKeyWord);
  }

  @GetMapping
  @ApiOperation("Retrieve all Tweets from Data Source")
  public List<TwitterPO> getAllTwitterPOs() {
    return this.twitterRepository.findAll();
  }

  @PostMapping
  @ApiOperation("Create a new Tweet")
  public TwitterPO createTwitterPO(@Valid @RequestBody TwitterPO twitterPO) {
    return this.twitterRepository.save(twitterPO);
  }

  @GetMapping("/{id}")
  @ApiOperation("Get a Single Tweet by Id")
  public TwitterPO getTwitterPOById(@PathVariable Long id) {
    return this.twitterRepository.findById(id)
                                 .orElseThrow(ResourceNotFoundException::new);
  }

  @PutMapping("/{id}")
  @ApiOperation("Update a Tweet by Id")
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

  @DeleteMapping("/{id}")
  @ApiOperation("Delete a Tweet by Id")
  public ResponseEntity<TwitterPO> deleteTwitterPO(@PathVariable Long id) {
    TwitterPO twitterPO = this.twitterRepository.findById(id)
                                                .orElseThrow(ResourceNotFoundException::new);
    this.twitterRepository.delete(twitterPO);
    return ResponseEntity.ok().build();
  }

}
