package com.ktm.rss.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktm.rss.model.RSSNewsPO;
import com.ktm.rss.service.InternationalRSSService;
import com.ktm.rss.service.NationalRSSService;
import com.rometools.rome.io.FeedException;

@RestController
@RequestMapping("/news")
public class RSSNewsController {

	@Autowired
	private InternationalRSSService internationalRSSService;

	@Autowired
	private NationalRSSService nationalRSSService;

	@RequestMapping(value = "/international/getAll", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<RSSNewsPO> fetchInternationalRSSFeedByQuery()
			throws IOException, IllegalArgumentException, FeedException {
		return this.internationalRSSService.fetchRSSFeedByQuery();
	}

	@RequestMapping(value = "/national/getAll", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<RSSNewsPO> fetchNationalRSSFeedByQuery() throws IOException, IllegalArgumentException, FeedException {
		return this.nationalRSSService.fetchRSSFeedByQuery();
	}

}
