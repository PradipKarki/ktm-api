package com.ktm.rss.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktm.rss.model.RssNews;
import com.ktm.rss.service.InternationalRssService;
import com.ktm.rss.service.NationalRssService;
import com.rometools.rome.io.FeedException;

@RestController
@RequestMapping("/news")
@RefreshScope
public class RssNewsController {

	@Autowired
	private InternationalRssService internationalRssService;

	@Autowired
	private NationalRssService nationalRssService;

	@RequestMapping(value = "/international/getAll", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<RssNews> fetchInternationalRssFeedByQuery()
			throws IOException, IllegalArgumentException, FeedException {
		return this.internationalRssService.fetchRssFeedByQuery();
	}

	@RequestMapping(value = "/national/getAll", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<RssNews> fetchNationalRssFeedByQuery() throws IOException, IllegalArgumentException, FeedException {
		return this.nationalRssService.fetchRssFeedByQuery();
	}

}
