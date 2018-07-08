package com.ktm.twitter.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ktm.rss.controller.RssNewsController;
import com.ktm.twitter.model.TwitterPO;
import com.ktm.twitter.service.TwitterService;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(TwitterController.class)
public class TwitterControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private TwitterService twitterService;

  @Test
  public void getTweetsNepal() throws Exception {
    TwitterPO twitterPo = new TwitterPO();
    twitterPo.setId(1L);
    twitterPo.setTitle("my title");
    List<TwitterPO> twitterPoList = Arrays.asList(twitterPo);

    given(twitterService.getTweetsByQuery("nepal")).willReturn(twitterPoList);
    mvc.perform(get("/twitter/nepal").accept(MediaType
      .APPLICATION_JSON_VALUE))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$.id", is(1L)))
       .andExpect(jsonPath("$.title", is("my title")));
  }

  @Configuration
  @ComponentScan(basePackageClasses = {RssNewsController.class})
  public static class TestConf {
  }
}