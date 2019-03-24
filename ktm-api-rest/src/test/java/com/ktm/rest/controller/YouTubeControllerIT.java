package com.ktm.rest.controller;

import static com.ktm.rest.ApiConstants.EndPoints.YOUTUBE_NEPAL_ENDPOINT;
import static org.assertj.core.api.Assertions.assertThat;

import com.ktm.library.core.model.YouTubePo;
import com.ktm.library.core.utils.CoreApiConstants;
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

public class YouTubeControllerIT extends BaseIntegrationTest {
  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void getYouTubeNepalVideosAndVerifyDetailsTest() {
    ResponseEntity<YouTubePo[]> responseEntity =
        restTemplate.getForEntity(YOUTUBE_NEPAL_ENDPOINT, YouTubePo[].class);
    List<YouTubePo> youTubePos = Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    YouTubePo youTubePo =
        youTubePos.stream().findFirst().orElseThrow(TestUtils::newIllegalStateException);
    assert (StringUtils.isNotEmpty(youTubePo.getTitle()));
    assert (StringUtils.isNotEmpty(youTubePo.getId()));
    assert (youTubePo.getUrl().contains(CoreApiConstants.YOUTUBE_VIDEO_URL_PREFIX));
  }
}
