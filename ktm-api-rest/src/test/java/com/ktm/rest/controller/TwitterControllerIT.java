package com.ktm.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.ktm.library.core.model.twitter.TwitterPo;
import com.ktm.rest.TestUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TwitterControllerIT extends BaseIntegrationTest {
  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void getTweetsNepalAndVerifyDetailsTest() {
    ResponseEntity<TwitterPo[]> responseEntity =
        restTemplate.getForEntity("/twitter/nepal", TwitterPo[].class);
    assertTwitterResponse(responseEntity);
  }

  public void assertTwitterResponse(ResponseEntity<TwitterPo[]> responseEntity) {
    List<TwitterPo> twitterPos = Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    TwitterPo tweetPo =
        twitterPos.stream().findFirst().orElseThrow(TestUtils::newIllegalStateException);
    assert (StringUtils.isNotEmpty(tweetPo.getTitle()));
    assert (tweetPo.getId() != 0L);
  }

  @Test
  public void getTweetsEverestAndVerifyDetailsTest() {
    ResponseEntity<TwitterPo[]> responseEntity =
        restTemplate.getForEntity("/twitter/everest", TwitterPo[].class);
    assertTwitterResponse(responseEntity);
  }

  @Test
  public void getTweetsKathmanduAndVerifyDetailsTest() {
    ResponseEntity<TwitterPo[]> responseEntity =
        restTemplate.getForEntity("/twitter/kathmandu", TwitterPo[].class);
    assertTwitterResponse(responseEntity);
  }

  @Test
  public void getTweetsSearchSoccerAndVerifyDetails() {
    ResponseEntity<TwitterPo[]> responseEntity =
        restTemplate.getForEntity("/twitter/search/soccer", TwitterPo[].class);
    assertTwitterResponse(responseEntity);
  }
}
