package com.ktm.youtube.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.ktm.youtube.model.YouTubePo;
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
public class YouTubeControllerIT {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void getYouTubeNepalVideos_verifyDetails() {
    ResponseEntity<YouTubePo[]> responseEntity =
        restTemplate.getForEntity("/youtube/nepal", YouTubePo[].class);
    List<YouTubePo> youTubePos = Arrays.asList(responseEntity.getBody());
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assert (!youTubePos.isEmpty());
    assert (StringUtils.isNotEmpty(youTubePos.stream().findFirst().get().getTitle()));
    assert (StringUtils.isNotEmpty(youTubePos.stream().findFirst().get().getVideoId()));
    assert (youTubePos.stream().findFirst().get().getUrl()
                      .contains("https://www.youtube.com/watch?v="));
  }
}