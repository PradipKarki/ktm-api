package com.ktm.twitter.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.List;
import java.util.Objects;
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
    ResponseEntity<List> response =
        restTemplate.getForEntity("/twitter/nepal", List.class);
    String firstItemFromResponse = Objects.requireNonNull(response.getBody())
                                          .get(0).toString();

    //assert
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(firstItemFromResponse).contains("Nepal");
  }
}