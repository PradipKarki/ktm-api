package com.ktm.rest.documentary.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.ktm.rest.documentary.model.Documentary;
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
public class DocumentaryControllerIT {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void getDocumentaryVideos_verifyDetails() {
    ResponseEntity<Documentary[]> responseEntity =
        restTemplate.getForEntity("/documentary", Documentary[].class);
    List<Documentary> documentaries = Arrays.asList(responseEntity.getBody());
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assert (documentaries.size() > 1);
    assert (StringUtils.isNotEmpty(documentaries.stream().findFirst().get().getTitle()));
    assert (StringUtils.isNotEmpty(documentaries.stream().findFirst().get().getVideoId()));
    assert (documentaries.stream().findFirst().get().getUrl()
                         .contains("https://www.youtube.com/watch?v="));
  }
}