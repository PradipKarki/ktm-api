package com.ktm.rest.documentary.controller;

import static com.ktm.rest.ApiConstants.EndPoints.YOUTUBE_ENDPOINT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.ktm.rest.ApiConstants;
import com.ktm.rest.TestUtils;
import com.ktm.rest.documentary.model.Documentary;
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
public class DocumentaryControllerIT {

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void getDocumentariesVerifyDetailsTest() {
    ResponseEntity<Documentary[]> responseEntity =
        restTemplate.getForEntity(YOUTUBE_ENDPOINT, Documentary[].class);
    List<Documentary> documentaries =
        Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    Documentary documentary =
        documentaries.stream().findFirst().orElseThrow(TestUtils::newIllegalStateException);
    assert (StringUtils.isNotEmpty(documentary.getTitle()));
    assert (StringUtils.isNotEmpty(documentary.getId()));
    assert (documentary.getUrl().contains(ApiConstants.YOUTUBE_VIDEO_URL_PREFIX));
  }
}
