package com.ktm.twitter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.ktm.twitter.model.TwitterPO;
import com.ktm.utils.TextUtility;

import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;

@Service
@Configuration
@PropertySource("classpath:twitter4j.properties")
public class TwitterService {

	private static final String ENGLISH_LANGUAGE = "en";
	private static final String [] IRRELEVANT_TWITTER_USERS = {"nepal_venture"};

	public List<TwitterPO> getTweetsByQuery(String queryString) throws TwitterException {
		Twitter twitter = TwitterFactory.getSingleton();
		Query query = new Query(queryString);
		query.lang(ENGLISH_LANGUAGE);
		query.setCount(100);
		QueryResult result = twitter.search(query);
		return getTwitterPOList(result);
	}
	
	// specific logic -- how to display result, method is private
	private List<TwitterPO> getTwitterPOList(QueryResult result) throws TwitterException {
		List<TwitterPO> twitterPOList = new ArrayList<>();
		List<String> articleURIList = new ArrayList<>();
		List<String> tweeterList = new ArrayList<>();
		for (Status status : result.getTweets()) {
			long TwitterID = status.getId();
			String tweet = status.getText();
			String url = "";
			String mediaURL = "";
			tweet = TextUtility.cleanTweetText(tweet);
			for (MediaEntity media : status.getMediaEntities()) {
				if (!media.getURL().isEmpty()) {
					mediaURL = Optional.ofNullable(media.getMediaURL()).orElse("");
					break;
				}
			}
			boolean isTweetDuplicate = isTweetDuplicate(tweet, mediaURL, tweeterList);
			boolean isThisTweetFromIrrelevantUsers = isThisTweetFromIrrelevantUsers(status);
			if (!isTweetDuplicate && !tweet.isEmpty() && !isThisTweetFromIrrelevantUsers) {
				for (URLEntity urlEntity : status.getURLEntities()) {
					if (!urlEntity.getURL().isEmpty()) {
						url = Optional.ofNullable(urlEntity.getURL()).orElse("");
						break;
					}
				}
				if (!articleURIList.contains(url) && !url.isEmpty()) {
					tweeterList.add(tweet);
					articleURIList.add(url);
					twitterPOList.add(new TwitterPO(TwitterID, tweet, mediaURL, url, null, null));
				}
			}
		}
		twitterPOList.sort((a, b) -> b.getImageURI().compareTo(a.getImageURI()));
		return twitterPOList;
	}
	
	public boolean isThisTweetFromIrrelevantUsers(Status status) {
		return (Arrays.asList(IRRELEVANT_TWITTER_USERS)
				.contains(status.getUser().getScreenName())) ? true : false;
	}

	/**
	 * take a piece of the tweet check if it is already in the tweeterList if it's
	 * there, it's duplicate, ignore it
	 * 
	 * @param tweet
	 * @param mediaURL 
	 * @param tweeterList 
	 */
	private boolean isTweetDuplicate(String tweet, String mediaURL, List<String> tweeterList) {
		for (String tweetFromTheList : tweeterList) {
			String tweetFromTheListLC = tweetFromTheList.toLowerCase();
			// get the middle of the tweet
			final int mid = tweet.length() / 2;
			String[] parts = { tweet.substring(0, mid), tweet.substring(mid) };
			// get the index of the second tweet upto middle
			final int indexOfTweet2 = (parts[1].length() / 2) + parts[0].length();
			String middleOfTheTweetString = tweet.substring(mid / 2, indexOfTweet2);
			if (tweetFromTheListLC.contains(middleOfTheTweetString.toLowerCase())
					|| tweet.toLowerCase().contains(tweetFromTheListLC)) {
				if (mediaURL.isEmpty()) return true;
				if (!mediaURL.isEmpty()) return false;
			}
			// check also if first five words in the tweeterList
			String[] splitStr = tweet.toLowerCase().split("\\s+");
			if (splitStr.length > 5 && tweetFromTheListLC.contains(splitStr[1])
					&& tweetFromTheListLC.contains(splitStr[2]) && tweetFromTheListLC.contains(splitStr[3])) {
				if (mediaURL.isEmpty()) return true;
				if (!mediaURL.isEmpty()) return false;
			}
		}
		return false;
	}

}
