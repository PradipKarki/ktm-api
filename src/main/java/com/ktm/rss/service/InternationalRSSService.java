package com.ktm.rss.service;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ktm.rss.model.RSSNewsPO;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Service
public class InternationalRSSService implements RSSService {
	
	@Value("${rss.international.india.tribune-india-1}")
	private String url;

	public List<RSSNewsPO> fetchRSSFeedByQuery() throws IllegalArgumentException, FeedException, IOException {
		URL feedUrl = new URL(url);
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(feedUrl));
		List<SyndEntry> entries = feed.getEntries().stream()
				.filter(item -> item.getTitle().toLowerCase().contains("nepal")).collect(Collectors.toList());
		return readAndParseRSSEntries(entries);
	}

}
