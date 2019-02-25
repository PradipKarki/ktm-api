package com.ktm.rest.twitter.controller;

import static com.ktm.rest.ApiConstants.TWITTER;
import static org.mapstruct.factory.Mappers.getMapper;

import com.ktm.exception.ResourceNotFoundException;
import com.ktm.rest.twitter.mapper.TwitterMapper;
import com.ktm.rest.twitter.model.TwitterDto;
import com.ktm.rest.twitter.model.TwitterPo;
import com.ktm.rest.twitter.service.TwitterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/twitter")
@RefreshScope
@Api(tags = TWITTER, value = TWITTER)
public class TwitterController {

  @Autowired private TwitterService twitterService;

  @GetMapping
  @ApiOperation("Retrieve all Tweets from Data Source")
  public List<TwitterPo> getAllTwitterPOs() {
    return this.twitterService.retrieveAll();
  }

  @PostMapping
  @ApiOperation("Create a new Tweet")
  public TwitterPo createTwitterPO(@Valid @RequestBody TwitterDto twitterDto) {
    TwitterPo twitterPo = getMapper(TwitterMapper.class).toTwitterPo(twitterDto);
    return this.twitterService.create(twitterPo);
  }

  @GetMapping("/{id}")
  @ApiOperation("Get a Single Tweet by Id")
  public TwitterPo getTwitterPOById(@PathVariable Long id) {
    return this.twitterService.read(id).orElseThrow(ResourceNotFoundException::new);
  }

  @PutMapping("/{id}")
  @ApiOperation("Update a Tweet by Id")
  public TwitterPo updateTwitterPO(
      @PathVariable Long id, @Valid @RequestBody TwitterDto twitterDto) {
    TwitterPo twitterPo = this.twitterService.read(id).orElseThrow(ResourceNotFoundException::new);
    twitterPo.setTitle(twitterDto.getTitle());
    twitterPo.setImageUri(twitterDto.getImageUri());
    twitterPo.setArticleUri(twitterDto.getArticleUri());
    twitterPo.setPublishedDate(twitterDto.getPublishedDate());
    twitterPo.setTwitterUser(twitterDto.getTwitterUser());
    return this.twitterService.update(twitterPo);
  }

  @DeleteMapping("/{id}")
  @ApiOperation("Delete a Tweet by Id")
  public ResponseEntity<TwitterPo> deleteTwitterPO(@PathVariable Long id) {
    TwitterPo twitterPo = this.twitterService.read(id).orElseThrow(ResourceNotFoundException::new);
    this.twitterService.delete(twitterPo);
    return ResponseEntity.ok().build();
  }
}
