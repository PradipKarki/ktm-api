package com.ktm.twitter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktm.twitter.model.TwitterPO;

import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;

@RestController
@RequestMapping("/twitter")
public class TwitterController {

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<TwitterPO> getTweets() throws TwitterException {
		List<TwitterPO> twitterPOList = new ArrayList<>();
		List<String> articleURIList = new ArrayList<>();
		Twitter twitter = TwitterFactory.getSingleton();
		Query query = new Query("nepal filter:news");
		query.setCount(100);
		QueryResult result = twitter.search(query);
		for (Status status : result.getTweets()) {
			long TwitterID = status.getId();
			String tweet = status.getText();
			if (tweet.contains("http")) {
				int index = tweet.indexOf("http");
				if (index != 0) {
					tweet = tweet.substring(0, index);
				}
			}
			// Delete all special characters except spaces 
			tweet = tweet.replaceAll("[^a-zA-Z0-9 ]+", "").trim();
			// Delete all RT (retweets flags)
			tweet = tweet.replaceAll("RT", "");
			String url = "";
			String mediaURL = "";
			for (URLEntity urlEntity : status.getURLEntities()) {
				if (!urlEntity.getURL().isEmpty()) {
					url = Optional.ofNullable(urlEntity.getURL()).orElse("");
					break;
				}
			}
			for (MediaEntity media : status.getMediaEntities()) {
				if (!media.getURL().isEmpty()) {
					mediaURL = Optional.ofNullable(media.getMediaURL()).orElse("");
				}
			}
			if (!articleURIList.contains(url) && !tweet.isEmpty()) {
				twitterPOList.add(new TwitterPO(TwitterID, tweet, mediaURL, url));
				articleURIList.add(url);
			} else {
				if ("".equals(url) && !tweet.isEmpty()) {
					twitterPOList.add(new TwitterPO(TwitterID, tweet, mediaURL, url));
					articleURIList.add(url);
				}
			}
		}
		twitterPOList.sort((a, b) -> b.getImageURI().compareTo(a.getImageURI()));
		return twitterPOList;
	}

}
