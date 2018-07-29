package com.ktm.rest.twitter.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ktm.rest.twitter.model.TwitterPo;
import com.ktm.rest.twitter.repository.TwitterRepository;
import com.ktm.rest.twitter.service.impl.TwitterServiceImpl;
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
public class TwitterControllerTest {

  @Autowired private MockMvc mvc;

  @MockBean private TwitterServiceImpl twitterServiceImpl;

  @MockBean private TwitterRepository twitterRepository;

  @Test
  public void givenTweets_whenGetTweets_thenReturnJsonArray() throws Exception {
    TwitterPo twitterPo = new TwitterPo();
    twitterPo.setId(1L);
    twitterPo.setTitle("my title");
    List<TwitterPo> twitterPoList = Collections.singletonList(twitterPo);

    given(twitterServiceImpl.getTweetsByQuery(anyString())).willReturn(twitterPoList);

    mvc.perform(get("/twitter/nepal").accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0]title", is("my title")));
  }

  @Test
  public void getTweet_notFound() throws Exception {
    given(twitterRepository.findById(anyLong())).willReturn(Optional.empty());

    mvc.perform(get("/twitter/1").accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotFound());

    given(twitterRepository.findById(anyLong())).willReturn(Optional.empty());

    mvc.perform(get("/twitter/2").accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotFound());
  }

  @Configuration
  @ComponentScan
  public static class TestConf {}
}
