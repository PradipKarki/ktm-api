package com.ktm.twitter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ktm.twitter.model.TwitterPO;
import com.ktm.twitter.model.TwitterUser;
import com.ktm.utils.TextUtility;

import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.User;

@Service
@PropertySource("classpath:twitter4j.properties")
@PropertySource("classpath:messages.properties")
public class TwitterService {

	@Autowired
	Environment env;
	@Autowired
	TextUtility textUtility;

	private final static String EMPTY_STRING = ""; //$NON-NLS-1$

	public List<TwitterPO> getTweetsByQuery(final String queryString) throws TwitterException {
		final String ENGLISH_LANGUAGE = this.env.getProperty("App.English.Language"); //$NON-NLS-1$
		Twitter twitter = TwitterFactory.getSingleton();
		Query query = new Query(queryString);
		query.lang(ENGLISH_LANGUAGE);
		query.setCount(100);
		QueryResult result = twitter.search(query);
		return getTwitterPOList(result);
	}

	// parse the tweets
	private List<TwitterPO> getTwitterPOList(final QueryResult result) {
		List<TwitterPO> twitterPOList = new ArrayList<>();
		Map<Long, TwitterPO> twitterPOHM = new HashMap<>();
		Map<Long, String> tweetHM = new HashMap<>();
		for (Status status : result.getTweets()) {
			long TwitterID = status.getId();
			String tweet = status.getText();
			String url = EMPTY_STRING;
			String mediaURL = EMPTY_STRING;
			tweet = this.textUtility.cleanTweetText(tweet);
			// check if image URL is empty or has value
			MediaEntity media = Stream.of(status.getMediaEntities()).findAny().filter(m -> !m.getURL().isEmpty())
					.orElse(null);
			mediaURL = (media != null) ? media.getMediaURL() : EMPTY_STRING;
			URLEntity urlEntity = Stream.of(status.getURLEntities()).findAny().filter(u -> !u.getURL().isEmpty())
					.orElse(null);
			url = (urlEntity != null) ? urlEntity.getURL() : EMPTY_STRING;
			boolean isThisTweetFromIrrelevantUsers = isThisTweetFromIrrelevantUsers(status);
			boolean isTweetDuplicate = isTweetDuplicate(tweet, mediaURL, url, twitterPOHM, tweetHM);
			if (!isTweetDuplicate && !tweet.isEmpty() && !isThisTweetFromIrrelevantUsers) {
				User user = status.getUser();
				TwitterUser twitterUser = new TwitterUser();
				twitterUser.setMiniProfileImageURL(user.getMiniProfileImageURL());
				twitterUser.setName(user.getName());
				twitterUser.setUserName(user.getScreenName());
				TwitterPO twitterPO = new TwitterPO(TwitterID, tweet, mediaURL, url, status.getCreatedAt(),
						twitterUser);
				tweetHM.put(Long.valueOf(TwitterID), tweet);
				twitterPOHM.put(Long.valueOf(TwitterID), twitterPO);
			}
		}
		twitterPOList = new ArrayList<>(twitterPOHM.values());
		twitterPOList.sort((a, b) -> b.getImageURI().compareTo(a.getImageURI()));
		return twitterPOList;
	}

	public boolean isThisTweetFromIrrelevantUsers(final Status status) {
		final String[] IRRELEVANT_TWITTER_USERS = { this.env.getProperty("Twitter.IrrelevantTwitterUsers") }; //$NON-NLS-1$
		return (Arrays.asList(IRRELEVANT_TWITTER_USERS).contains(status.getUser().getScreenName())) ? true : false;
	}

	/**
	 * take a piece of the tweet check if it is already in the tweeterList if it's
	 * there, it's duplicate, ignore it
	 * 
	 * @param tweet
	 * @param mediaURL
	 * @param articleURI
	 * @param tweetHM2
	 * @param tweeterList
	 * @param imageURIHM
	 */
	private boolean isTweetDuplicate(final String tweet, final String mediaURL, final String articleURI,
			final Map<Long, TwitterPO> twitterPOHM, final Map<Long, String> tweetHM) {
		final String REGEX_SEQUENCE_OF_WHITE_CHARACTERS = this.env
				.getProperty("Twitter.RegexSequenceOfWhiteCharacters"); //$NON-NLS-1$
		for (String tweetFromTheList : new ArrayList<>(tweetHM.values())) {
			String tweetFromTheListLC = tweetFromTheList.toLowerCase();
			// get the middle of the tweet
			final int mid = tweet.length() / 2;
			String[] parts = { tweet.substring(0, mid), tweet.substring(mid) };
			// get the index of the second tweet upto middle
			final int indexOfTweet2 = (parts[1].length() / 2) + parts[0].length();
			String middleOfTheTweetString = tweet.substring(mid / 2, indexOfTweet2);
			if (tweetFromTheListLC.contains(middleOfTheTweetString.toLowerCase())
					|| tweet.toLowerCase().contains(tweetFromTheListLC) ||
					tweetFromTheListLC.equals(tweet.toLowerCase())) {
				if (mediaURL.isEmpty() || articleURI.isEmpty())
					return true;
				Stream<Long> twitterIdStream = tweetHM.entrySet().stream()
						.filter(x -> tweetFromTheList.equals(x.getValue())).map(Map.Entry::getKey);
				twitterIdStream.forEach(twitterId -> twitterPOHM.remove(twitterId));
			}
			// if it's not duplicate let's check it matches few words
			// check also if three words in the tweeterList
			String[] splitStr = tweet.toLowerCase().split(REGEX_SEQUENCE_OF_WHITE_CHARACTERS);
			int length = splitStr.length;
			if (length > 5 && tweetFromTheListLC.contains(splitStr[1]) && tweetFromTheListLC.contains(splitStr[2])
					&& tweetFromTheListLC.contains(splitStr[3])) {
				if (mediaURL.isEmpty() || articleURI.isEmpty())
					return true;
				Stream<Long> twitterIdStream = tweetHM.entrySet().stream()
						.filter(x -> tweetFromTheList.equals(x.getValue())).map(Map.Entry::getKey);
				twitterIdStream.forEach(twitterId -> twitterPOHM.remove(twitterId));
			}

			// check also if last three words in the tweeterList
			if (tweetFromTheListLC.contains(splitStr[length-1]) && tweetFromTheListLC.contains(splitStr[length-2])
					&& tweetFromTheListLC.contains(splitStr[length-3])) {
				if (mediaURL.isEmpty() || articleURI.isEmpty())
					return true;
				Stream<Long> twitterIdStream = tweetHM.entrySet().stream()
						.filter( x -> tweetFromTheList.equals(x.getValue()) ).map(Map.Entry::getKey);
				twitterIdStream.forEach(twitterId -> twitterPOHM.remove(twitterId));
			}
		}
		return false;
	}

}
