package com.ktm.twitter.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ktm.twitter.model.TwitterPO;
import com.ktm.twitter.service.TwitterService;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TwitterController.class, secure = false)
public class TwitterControllerTest {

  @InjectMocks
  private TwitterController twitterController;
  @Autowired
  private MockMvc mvc;
  @MockBean
  private TwitterService twitterService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.mvc = MockMvcBuilders.standaloneSetup(twitterController).build();
  }

  @Test
  public void givenTweets_whenGetTweets_thenReturnJsonArray() throws Exception {
    TwitterPO twitterPo = new TwitterPO();
    twitterPo.setId(1L);
    twitterPo.setTitle("my title");
    List<TwitterPO> twitterPoList = Arrays.asList(twitterPo);

    given(twitterService.getTweetsByQuery("nepal")).willReturn(twitterPoList);

    MvcResult result = mvc.perform(MockMvcRequestBuilders
        .get("/twitter/nepal").accept(MediaType.APPLICATION_JSON)).andReturn();

    String content = result.getResponse().getContentAsString();
    System.out.println("content: " + content);

    mvc.perform(get("/twitter/nepal").accept(MediaType
        .APPLICATION_JSON_VALUE))
       .andExpect(status().isOk())
       .andDo(print())
       .andExpect(jsonPath("$[0].id", is(1L)))
       .andExpect(jsonPath("$.0.title", is("my title")));
  }

  @Configuration
  @ComponentScan(basePackageClasses = TwitterController.class)
  public static class TestConf {
  }
}