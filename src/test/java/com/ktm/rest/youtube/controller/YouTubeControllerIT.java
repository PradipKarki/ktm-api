package com.ktm.rest.youtube.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.ktm.rest.youtube.model.YouTubePo;
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
public class YouTubeControllerIT {

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void getYouTubeNepalVideos_verifyDetails() {
    ResponseEntity<YouTubePo[]> responseEntity =
        restTemplate.getForEntity("/youtube/nepal", YouTubePo[].class);
    List<YouTubePo> youTubePos = Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assert (!youTubePos.isEmpty());
    YouTubePo youTubePo = youTubePos.stream().findFirst().orElse(null);
    assert (StringUtils.isNotEmpty(youTubePo.getTitle()));
    assert (StringUtils.isNotEmpty(youTubePo.getId()));
    assert (youTubePo.getUrl().contains("https://www.youtube.com/watch?v="));
  }
}
