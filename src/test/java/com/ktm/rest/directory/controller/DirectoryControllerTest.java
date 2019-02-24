package com.ktm.rest.directory.controller;

import static com.ktm.rest.ApiConstants.EndPoints.DIRECTORY_ENDPOINT;
import static com.ktm.rest.TestConstants.ITEM_NOT_FOUND_ID;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ktm.rest.directory.model.Directory;
import com.ktm.rest.directory.service.DirectoryService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(secure = false)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class DirectoryControllerTest {

  @Autowired private MockMvc mvc;

  @MockBean private DirectoryService directoryService;

  @Test
  public void getDirectoriesVerifyTest() throws Exception {
    Directory directory = new Directory();
    directory.setId(1L);
    directory.setName("KTM Business");
    directory.setWebsite("ktmtimes.com");
    List<Directory> directories = Collections.singletonList(directory);

    given(directoryService.retrieveAll()).willReturn(directories);
    mvc.perform(get(DIRECTORY_ENDPOINT).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].name", is("KTM Business")))
        .andExpect(jsonPath("$[0].website", is("ktmtimes.com")));
  }

  @Test
  public void getDirectoriesEmptyCollectionVerify200HttpStatusTest() throws Exception {
    List<Directory> directories = Collections.emptyList();

    given(directoryService.retrieveAll()).willReturn(directories);
    mvc.perform(get(DIRECTORY_ENDPOINT).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());

    given(directoryService.retrieveAll()).willReturn(null);
    mvc.perform(get(DIRECTORY_ENDPOINT).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());
  }

  @Test
  public void getDirectoryNotFoundTest() throws Exception {
    given(directoryService.read(anyLong())).willReturn(Optional.empty());

    mvc.perform(
            get(DIRECTORY_ENDPOINT + ITEM_NOT_FOUND_ID).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotFound());
  }

  @Configuration
  @ComponentScan
  public static class TestConf {}
}
