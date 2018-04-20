package com.ktm.rss.service;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ktm.rss.model.RssNews;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Service
@PropertySource("classpath:messages.properties")
public class InternationalRssService implements RssService {
	
	@Autowired Environment env;

	@Value("${rss.international.india.tribune-india-1}")
	private String url;

	public List<RssNews> fetchRssFeedByQuery() throws java.lang.IllegalArgumentException, FeedException, IOException {
		final String SEARCH_QUERY_NEPAL = this.env.getProperty("App.Nepal.SearchQueryKeyWord"); //$NON-NLS-1$
		URL feedUrl = new URL(this.url);
		SyndFeedInput input = new SyndFeedInput();
		try (Reader xmlReader = new XmlReader(feedUrl)) {
			SyndFeed feed = input.build(xmlReader);
			List<SyndEntry> entries = feed.getEntries().stream()
					.filter(item -> item.getTitle().toLowerCase().contains(SEARCH_QUERY_NEPAL))
					.collect(Collectors.toList());
			return readAndParseRssEntries(entries);
		}
	}

}
