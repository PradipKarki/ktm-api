package com.ktm.rest.controller;

import static com.ktm.rest.ApiConstants.EndPoints.NEWS_INTERNATIONAL_ENDPOINT;
import static com.ktm.rest.ApiConstants.EndPoints.NEWS_NATIONAL_ENDPOINT;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ktm.library.core.model.RssNews;
import com.ktm.library.core.service.RssService;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

  @MockBean
  @Qualifier("international")
  private RssService internationalRssService;

  @MockBean
  @Qualifier("national")
  private RssService nationalRssService;

  @Test
  public void fetchInternationalRssFeedTest() throws Exception {
    RssNews rssNews = new RssNews();
    rssNews.setId(0L);
    rssNews.setTitle("international news title");
    List<RssNews> interNationalRssNewsList = Collections.singletonList(rssNews);

    given(internationalRssService.fetchRssFeedByQuery()).willReturn(interNationalRssNewsList);
    mvc.perform(get(NEWS_INTERNATIONAL_ENDPOINT).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id", is(0)))
        .andExpect(jsonPath("$[0].title", is("international news title")));
  }

  @Test
  public void fetchNationalRssFeedTest() throws Exception {
    RssNews rssNews = new RssNews();
    rssNews.setId(0L);
    rssNews.setTitle("national news title");
    List<RssNews> nationalRssNewsList = Collections.singletonList(rssNews);

    given(nationalRssService.fetchRssFeedByQuery()).willReturn(nationalRssNewsList);
    mvc.perform(get(NEWS_NATIONAL_ENDPOINT).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id", is(0)))
        .andExpect(jsonPath("$[0].title", is("national news title")));
  }

  @Test
  public void getInternationalNewsEmptyCollectionAndVerify200Test() throws Exception {
    given(internationalRssService.fetchRssFeedByQuery()).willReturn(Collections.emptyList());

    mvc.perform(get(NEWS_INTERNATIONAL_ENDPOINT).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());

    given(internationalRssService.fetchRssFeedByQuery()).willReturn(null);

    mvc.perform(get(NEWS_INTERNATIONAL_ENDPOINT).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());
  }

  @Configuration
  @ComponentScan
  public static class TestConf {}
}
