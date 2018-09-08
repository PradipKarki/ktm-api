package com.ktm.rest.directory.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.ktm.rest.directory.model.Directory;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
public class DirectoryControllerIT {

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void getTweetsNepal_verifyDetails() {
    // arrange

    // act
    ResponseEntity<Directory[]> responseEntity =
        restTemplate.getForEntity("/directory", Directory[].class);
    assertTwitterResponse(responseEntity);
  }

  public void assertTwitterResponse(ResponseEntity<Directory[]> responseEntity) {
    List<Directory> twitterPos = Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));

    // assert
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assert (!twitterPos.isEmpty());
    assert (StringUtils.isNotEmpty(twitterPos.stream().findFirst().get().getDescription()));
    assert (twitterPos.stream().findFirst().get().getId() != 0L);
  }
}
