package com.ktm.rss.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.List;
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
public class RssNewsControllerIT {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void fetchInternationalRssFeed_verifyDetails() {
    //arrange

    //act
    ResponseEntity<List> response =
        restTemplate.getForEntity("/news/international", List.class);

    //assert
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  public void fetchNationalRssFeed_verifyDetails() {
    //arrange

    //act
    ResponseEntity<List> response =
        restTemplate.getForEntity("/news/national", List.class);

    //assert
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}