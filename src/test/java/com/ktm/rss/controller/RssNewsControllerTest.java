package com.ktm.rss.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ktm.rss.model.RssNews;
import com.ktm.rss.service.InternationalRssService;
import com.ktm.rss.service.NationalRssService;
import java.util.Arrays;
import java.util.List;
import org.junit.Ignore;
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
@WebMvcTest(RssNewsController.class)
@Ignore
public class RssNewsControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private InternationalRssService internationalRssService;

  @MockBean
  private NationalRssService nationalRssService;

  @Test
  public void fetchInternationalRssFeedByQuery() throws Exception {
    RssNews rssNews = new RssNews();
    rssNews.setId(1L);
    rssNews.setTitle("my title");
    List<RssNews> rssNewsList = Arrays.asList(rssNews);

    given(internationalRssService.fetchRssFeedByQuery()).willReturn(rssNewsList);
    mvc.perform(get("/news/international").accept(MediaType.APPLICATION_JSON_VALUE))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$.id", is(1)))
       .andExpect(jsonPath("$.name", is("John")))
       .andExpect(jsonPath("$.age", is(25)));
  }

  public void fetchNationalRssFeedByQuery() {
  }

  @Configuration
  @ComponentScan(basePackageClasses = RssNewsController.class)
  public static class TestConf {
  }
}