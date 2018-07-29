package com.ktm.rest.rss.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ktm.rest.rss.model.RssNews;
import com.ktm.rest.rss.service.impl.InternationalRssService;
import com.ktm.rest.rss.service.impl.NationalRssService;
import java.util.Collections;
import java.util.List;
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
public class RssNewsControllerTest {

  @Autowired private MockMvc mvc;

  @MockBean private InternationalRssService internationalRssService;

  @MockBean private NationalRssService nationalRssService;

  @Test
  public void fetchInternationalRssFeed_thenReturnJsonArray() throws Exception {
    RssNews rssNews = new RssNews();
    rssNews.setId(0L);
    rssNews.setTitle("international news title");
    List<RssNews> interNationalRssNewsList = Collections.singletonList(rssNews);

    given(internationalRssService.fetchRssFeedByQuery()).willReturn(interNationalRssNewsList);
    mvc.perform(get("/news/international").accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id", is(0)))
        .andExpect(jsonPath("$[0].title", is("international news title")));
  }

  @Test
  public void fetchNationalRssFeed_thenReturnJsonArray() throws Exception {
    RssNews rssNews = new RssNews();
    rssNews.setId(0L);
    rssNews.setTitle("national news title");
    List<RssNews> nationalRssNewsList = Collections.singletonList(rssNews);

    given(internationalRssService.fetchRssFeedByQuery()).willReturn(nationalRssNewsList);
    mvc.perform(get("/news/national").accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id", is(0)))
        .andExpect(jsonPath("$[0].title", is("national news title")));
  }

  @Test
  public void getRssNewsFeed_emptyCollection_verify200HttpStatus() throws Exception {
    given(internationalRssService.fetchRssFeedByQuery()).willReturn(Collections.emptyList());

    mvc.perform(get("/news/international").accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());

    given(internationalRssService.fetchRssFeedByQuery()).willReturn(null);

    mvc.perform(get("/news/international").accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());
  }

  @Configuration
  @ComponentScan
  public static class TestConf {}
}
