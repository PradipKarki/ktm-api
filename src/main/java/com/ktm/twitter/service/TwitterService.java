package com.ktm.twitter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
@PropertySource("classpath:twitter4j.properties")
@PropertySource("classpath:messages.properties")
public class TwitterService {

	@Autowired Environment env;
	@Autowired TextUtility textUtility;
	
	private final static String EMPTY_STRING = ""; //$NON-NLS-1$

	public List<TwitterPO> getTweetsByQuery(String queryString) throws TwitterException {
		final String ENGLISH_LANGUAGE = this.env.getProperty("App.English.Language"); //$NON-NLS-1$
		Twitter twitter = TwitterFactory.getSingleton();
		Query query = new Query(queryString);
		query.lang(ENGLISH_LANGUAGE);
		query.setCount(100);
		QueryResult result = twitter.search(query);
		return getTwitterPOList(result);
	}

	// parse the tweets
	private List<TwitterPO> getTwitterPOList(QueryResult result) {
		List<TwitterPO> twitterPOList = new ArrayList<>();
		List<String> articleURIList = new ArrayList<>();
		List<String> tweeterList = new ArrayList<>();
		for (Status status : result.getTweets()) {
			long TwitterID = status.getId();
			String tweet = status.getText();
			String url = EMPTY_STRING;
			String mediaURL = EMPTY_STRING;
			tweet = this.textUtility.cleanTweetText(tweet);
			MediaEntity media = Stream.of(status.getMediaEntities())
					.findAny().filter(m -> !m.getURL().isEmpty()).orElse(null);
			mediaURL = (media != null) ? media.getMediaURL() : EMPTY_STRING;
			boolean isTweetDuplicate = isTweetDuplicate(tweet, mediaURL, tweeterList);
			boolean isThisTweetFromIrrelevantUsers = isThisTweetFromIrrelevantUsers(status);
			if (!isTweetDuplicate && !tweet.isEmpty() && !isThisTweetFromIrrelevantUsers) {
				URLEntity urlEntity = Stream.of(status.getURLEntities())
						.findAny().filter(u -> !u.getURL().isEmpty()).orElse(null);
				url = (urlEntity != null) ? urlEntity.getURL() : EMPTY_STRING;
				if (!articleURIList.contains(url) && !url.isEmpty()) {
					tweeterList.add(tweet);
					articleURIList.add(url);
/*					User user = status.getUser();
					user.getId(), user.getEmail(), user.getLocation(), user.getName(), user.getScreenName(), user.getURL()
*/					twitterPOList.add(new TwitterPO(TwitterID, tweet, mediaURL, url, 
											status.getCreatedAt(), status.getUser().getName()));
				}
			}
		}
		twitterPOList.sort((a, b) -> b.getImageURI().compareTo(a.getImageURI()));
		return twitterPOList;
	}

	public boolean isThisTweetFromIrrelevantUsers(Status status) {
		final String[] IRRELEVANT_TWITTER_USERS = { this.env.getProperty("Twitter.IrrelevantTwitterUsers") }; //$NON-NLS-1$
		return (Arrays.asList(IRRELEVANT_TWITTER_USERS).contains(status.getUser().getScreenName())) ? true : false;
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
		final String REGEX_SEQUENCE_OF_WHITE_CHARACTERS = this.env.getProperty("Twitter.RegexSequenceOfWhiteCharacters"); //$NON-NLS-1$
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
				if (mediaURL.isEmpty())
					return true;
				if (!mediaURL.isEmpty())
					return false;
			}
			// check also if first five words in the tweeterList
			String[] splitStr = tweet.toLowerCase().split(REGEX_SEQUENCE_OF_WHITE_CHARACTERS);
			if (splitStr.length > 5 && tweetFromTheListLC.contains(splitStr[1])
					&& tweetFromTheListLC.contains(splitStr[2]) && tweetFromTheListLC.contains(splitStr[3])) {
				if (mediaURL.isEmpty())
					return true;
				if (!mediaURL.isEmpty())
					return false;
			}
		}
		return false;
	}

}
