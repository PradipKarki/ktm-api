package com.ktm.twitter.controller;

import com.ktm.ApiConstants;
import com.ktm.exception.ResourceNotFoundException;
import com.ktm.twitter.model.TwitterPo;
import com.ktm.twitter.repository.TwitterRepository;
import com.ktm.twitter.service.TwitterService;
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
  public List<TwitterPo> getTweetsNepal() throws TwitterException {
    return this.twitterService.getTweetsByQuery(searchNepalQuery);
  }

  @GetMapping("/everest")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all Tweets Related to Everest")
  public List<TwitterPo> getTweetsEverest() throws TwitterException {
    return this.twitterService.getTweetsByQuery(searchEverestQuery);
  }

  @GetMapping("/kathmandu")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all Tweets Related to Kathmandu")
  public List<TwitterPo> getTweetsKathmandu() throws TwitterException {
    return this.twitterService.getTweetsByQuery(searchKathmanduQuery);
  }

  @GetMapping("/search/{tweetKeyWord}")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all Tweets Related to Search KeyWord")
  public List<TwitterPo> getTweetsByKeyWord(@PathVariable String tweetKeyWord) throws
      TwitterException {
    return this.twitterService.getTweetsByQuery(tweetKeyWord);
  }

  @GetMapping
  @ApiOperation("Retrieve all Tweets from Data Source")
  public List<TwitterPo> getAllTwitterPOs() {
    return this.twitterRepository.findAll();
  }

  @PostMapping
  @ApiOperation("Create a new Tweet")
  public TwitterPo createTwitterPO(@Valid @RequestBody TwitterPo twitterPo) {
    return this.twitterRepository.save(twitterPo);
  }

  @GetMapping("/{id}")
  @ApiOperation("Get a Single Tweet by Id")
  public TwitterPo getTwitterPOById(@PathVariable Long id) {
    return this.twitterRepository.findById(id)
                                 .orElseThrow(ResourceNotFoundException::new);
  }

  @PutMapping("/{id}")
  @ApiOperation("Update a Tweet by Id")
  public TwitterPo updateTwitterPO(@PathVariable Long id,
      @Valid @RequestBody TwitterPo twitterPoDetails) {
    TwitterPo twitterPo = this.twitterRepository.findById(id)
                                                .orElseThrow(ResourceNotFoundException::new);
    twitterPo.setTitle(twitterPoDetails.getTitle());
    twitterPo.setImageUri(twitterPoDetails.getImageUri());
    twitterPo.setArticleUri(twitterPoDetails.getArticleUri());
    twitterPo.setPublishedDate(twitterPoDetails.getPublishedDate());
    twitterPo.setTwitterUser(twitterPoDetails.getTwitterUser());
    return this.twitterRepository.save(twitterPo);
  }

  @DeleteMapping("/{id}")
  @ApiOperation("Delete a Tweet by Id")
  public ResponseEntity<TwitterPo> deleteTwitterPO(@PathVariable Long id) {
    TwitterPo twitterPo = this.twitterRepository.findById(id)
                                                .orElseThrow(ResourceNotFoundException::new);
    this.twitterRepository.delete(twitterPo);
    return ResponseEntity.ok().build();
  }

}
