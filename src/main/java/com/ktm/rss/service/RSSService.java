package com.ktm.rss.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ktm.rss.model.RSSNewsPO;
import com.rometools.rome.feed.synd.SyndEntry;

public interface RSSService {

	public default List<RSSNewsPO> readAndParseRSSEntries(List<SyndEntry> entries) {
		List<RSSNewsPO> rssNewsList = new ArrayList<>();
		for (SyndEntry entry : entries) {
			RSSNewsPO rssNews = new RSSNewsPO();
			rssNews.setAuthors(entry.getAuthors());
			rssNews.setContributors(entry.getContributors());
			List<String> tags = entry.getCategories().stream().flatMap(category -> Stream.of(category.getName()))
					.collect(Collectors.toList());
			rssNews.setTags(tags);
			rssNews.setComments(entry.getComments());
			List<String> contents = entry.getContents().stream().flatMap(content -> Stream.of(content.getValue()))
					.collect(Collectors.toList());
			rssNews.setContents(contents);
			rssNews.setTitle(entry.getTitle());
			rssNews.setDescription(entry.getDescription().getValue());
			rssNews.setUri(entry.getUri());
			rssNews.setPublishedDate(entry.getPublishedDate());
			rssNews.setUpdatedDate(entry.getUpdatedDate());
			if (entry.getSource() != null) {
				rssNews.setIcon(entry.getSource().getIcon());
				rssNews.setFeedType(entry.getSource().getFeedType());
				rssNews.setImage(entry.getSource().getImage());
			}
			rssNewsList.add(rssNews);
		}
		return rssNewsList;
	}
}
