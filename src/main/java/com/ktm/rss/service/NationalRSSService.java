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

import com.ktm.rss.model.RSSNewsPO;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Service
@PropertySource("classpath:messages.properties")
public class NationalRSSService implements RSSService {
	
	@Autowired Environment env;
	
	@Value("${rss.national.nepal.himalayan-times-1}")
	private String url;
	
	public List<RSSNewsPO> fetchRSSFeedByQuery() throws java.lang.IllegalArgumentException, FeedException, IOException {
		final String SEARCH_QUERY_NEPAL = this.env.getProperty("App.Nepal.SearchQueryKeyWord"); //$NON-NLS-1$
		URL feedUrl = new URL(this.url);
		SyndFeedInput input = new SyndFeedInput();
		try (Reader xmlReader = new XmlReader(feedUrl)) {
			SyndFeed feed = input.build(xmlReader);
			List<SyndEntry> entries = feed.getEntries().stream()
					.filter(item -> item.getTitle().toLowerCase().contains(SEARCH_QUERY_NEPAL))
					.collect(Collectors.toList());
			return readAndParseRSSEntries(entries);
		}
	}

}
