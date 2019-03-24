package com.ktm.rest.controller;

import static com.ktm.rest.ApiConstants.EndPoints.DIRECTORY_ENDPOINT;
import static org.assertj.core.api.Assertions.assertThat;

import com.ktm.library.core.model.directory.Directory;
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

public class DirectoryControllerIT extends BaseIntegrationTest {
  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void getDirectoriesVerifyTest() {
    ResponseEntity<Directory[]> responseEntity =
        restTemplate.getForEntity(DIRECTORY_ENDPOINT, Directory[].class);
    assertDirectoryResponse(responseEntity);
  }

  public void assertDirectoryResponse(ResponseEntity<Directory[]> responseEntity) {
    List<Directory> directories =
        Arrays.asList(Objects.requireNonNull(responseEntity.getBody())); /* assert*/
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    Directory directory =
        directories.stream().findFirst().orElseThrow(TestUtils::newIllegalStateException);
    assert (StringUtils.isNotEmpty(directory.getDescription()));
    assert (directory.getId() != 0L);
  }
}
