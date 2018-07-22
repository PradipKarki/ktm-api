package com.ktm.rest.twitter.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.ktm.rest.twitter.model.TwitterPo;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TwitterControllerIT {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void getTweetsNepal_verifyDetails() {
    //arrange

    //act
    ResponseEntity<TwitterPo[]> responseEntity =
        restTemplate.getForEntity("/twitter/nepal", TwitterPo[].class);
    List<TwitterPo> twitterPos = Arrays.asList(responseEntity.getBody());

    //assert
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assert (!twitterPos.isEmpty());
    assert (StringUtils.isNotEmpty(twitterPos.stream().findFirst().get().getTitle()));
    assert (twitterPos.stream().findFirst().get().getId() != 0L);
  }

  @Test
  public void getTweetsEverest_verifyDetails() {
    ResponseEntity<TwitterPo[]> responseEntity =
        restTemplate.getForEntity("/twitter/everest", TwitterPo[].class);
    List<TwitterPo> twitterPos = Arrays.asList(responseEntity.getBody());
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assert (!twitterPos.isEmpty());
    assert (StringUtils.isNotEmpty(twitterPos.stream().findFirst().get().getTitle()));
    assert (twitterPos.stream().findFirst().get().getId() != 0L);
  }

  @Test
  public void getTweetsKathmandu_verifyDetails() {
    ResponseEntity<TwitterPo[]> responseEntity =
        restTemplate.getForEntity("/twitter/kathmandu", TwitterPo[].class);
    List<TwitterPo> twitterPos = Arrays.asList(responseEntity.getBody());
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assert (!twitterPos.isEmpty());
    assert (StringUtils.isNotEmpty(twitterPos.stream().findFirst().get().getTitle()));
    assert (twitterPos.stream().findFirst().get().getId() != 0L);
  }

  @Test
  public void getTweetsSearchSoccer_verifyDetails() {
    ResponseEntity<TwitterPo[]> responseEntity =
        restTemplate.getForEntity("/twitter/search/soccer", TwitterPo[].class);
    List<TwitterPo> twitterPos = Arrays.asList(responseEntity.getBody());
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assert (!twitterPos.isEmpty());
    assert (StringUtils.isNotEmpty(twitterPos.stream().findFirst().get().getTitle()));
    assert (twitterPos.stream().findFirst().get().getId() != 0L);
  }
}