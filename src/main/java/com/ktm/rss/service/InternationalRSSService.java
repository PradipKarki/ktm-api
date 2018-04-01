package com.ktm.rss.service;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ktm.rss.model.RSSNewsPO;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Service
@Configuration
@PropertySource(value = "classpath:international-rss.properties")
public class InternationalRSSService implements RSSService {

	@Autowired
	private Environment env;

	public List<RSSNewsPO> fetchRSSFeedByQuery() throws IllegalArgumentException, FeedException, IOException {
		URL feedUrl = new URL(env.getProperty("india.tribune-india-1"));
		SyndFeedInput input = new SyndFeedInput();

		SyndFeed feed = input.build(new XmlReader(feedUrl));
		List<SyndEntry> entries = feed.getEntries().stream()
				.filter(item -> item.getTitle().toLowerCase().contains("nepal")).collect(Collectors.toList());
		return readAndParseRSSEntries(entries);
	}

}
