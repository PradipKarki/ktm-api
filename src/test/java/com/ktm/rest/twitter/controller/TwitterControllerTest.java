package com.ktm.rest.twitter.controller;

import static com.ktm.rest.ApiConstants.EndPoints.TWITTER_ENDPOINT;
import static com.ktm.rest.TestConstants.ITEM_NOT_FOUND_ID;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ktm.rest.twitter.repository.TwitterRepository;
import com.ktm.rest.twitter.service.TwitterService;
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
public class TwitterControllerTest {

  @Autowired private MockMvc mvc;

  @MockBean private TwitterService twitterService;

  @MockBean private TwitterRepository twitterRepository;

  @Test
  public void getTweetNotFoundTest() throws Exception {
    given(twitterRepository.findById(anyLong())).willReturn(Optional.empty());

    mvc.perform(get(TWITTER_ENDPOINT + ITEM_NOT_FOUND_ID).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotFound());
  }

  @Configuration
  @ComponentScan
  public static class TestConf {}
}
