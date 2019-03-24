package com.ktm.rest.controller;

import static com.ktm.rest.ApiConstants.EndPoints.NEWS_INTERNATIONAL_ENDPOINT;
import static com.ktm.rest.ApiConstants.EndPoints.NEWS_NATIONAL_ENDPOINT;
import static org.assertj.core.api.Assertions.assertThat;

import com.ktm.library.core.model.RssNews;
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

public class RssNewsControllerIT extends BaseControllerTest {
  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void fetchInternationalRssFeedAndVerifyDetailsTest() {
    ResponseEntity<RssNews[]> responseEntity =
        restTemplate.getForEntity(NEWS_INTERNATIONAL_ENDPOINT, RssNews[].class);
    assertRssNewsResponse(responseEntity);
  }

  @Test
  public void fetchNationalRssFeedAndVerifyDetailsTest() {
    ResponseEntity<RssNews[]> responseEntity =
        restTemplate.getForEntity(NEWS_NATIONAL_ENDPOINT, RssNews[].class);
    assertRssNewsResponse(responseEntity);
  }

  public void assertRssNewsResponse(ResponseEntity<RssNews[]> responseEntity) {
    List<RssNews> rssNewsList = Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    RssNews rssNews =
        rssNewsList.stream().findFirst().orElseThrow(TestUtils::newIllegalStateException);
    assert (StringUtils.isNotEmpty(rssNews.getDescription()));
    assert (StringUtils.isNotEmpty(rssNews.getUri()));
  }
}
