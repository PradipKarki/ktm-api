package com.ktm.twitter.service;

import com.ktm.twitter.builder.TwitterKtmApp;
import com.ktm.twitter.model.TwitterPO;
import com.ktm.twitter.model.TwitterUser;
import com.ktm.utils.DateUtility;
import com.ktm.utils.TextUtility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.URLEntity;
import twitter4j.User;

@Service
public class TwitterService {

  private static final String EMPTY_STRING = ""; //$NON-NLS-1$

  @Value("${App.English.Language}")
  private String englishLanguage;
  @Value("${Twitter.RegexSequenceOfWhiteCharacters}")
  private String regexSequenceOfWhiteCharacters;
  @Value("${Twitter.IrrelevantTwitterUsers}")
  private String[] irrelevantTwitterUsers;

  @Autowired
  private TextUtility textUtility;
  @Autowired
  private TwitterKtmApp twitterKtmApp;

  private static void ifDuplicateRemoveTwitterPOFromList(Map<Long, TwitterPO> twitterPOHM,
                                                         Map<Long, String> tweetHM, String
                                                           tweetFromTheList) {
    tweetHM.entrySet().stream()
           .filter(x -> tweetFromTheList.equals(x.getValue())).map(Map.Entry::getKey)
           .forEach(twitterPOHM::remove);
  }

  public List<TwitterPO> getTweetsByQuery(String queryString) throws TwitterException {
    Twitter twitter = twitterKtmApp.getInstance();
    Query query = new Query(queryString);
    query.lang(englishLanguage);
    query.setCount(100);
    QueryResult result = twitter.search(query);
    return getTwitterPOList(result);
  }

  // parse the tweets
  private List<TwitterPO> getTwitterPOList(QueryResult result) {
    Map<Long, TwitterPO> twitterPOHM = new HashMap<>();
    Map<Long, String> tweetHM = new HashMap<>();
    for (Status status : result.getTweets()) {
      long twitterID = status.getId();
      String tweet = status.getText();
      String url;
      String mediaURL;
      tweet = this.textUtility.cleanTweetText(tweet);
      // check if image URL is empty or has value
      MediaEntity media = Stream.of(status.getMediaEntities()).findAny()
                                .filter(m -> !m.getURL().isEmpty())
                                .orElse(null);
      mediaURL = (null != media) ? media.getMediaURL() : EMPTY_STRING;
      URLEntity urlEntity = Stream.of(status.getURLEntities()).findAny()
                                  .filter(u -> !u.getURL().isEmpty())
                                  .orElse(null);
      url = (null != urlEntity) ? urlEntity.getURL() : EMPTY_STRING;
      boolean isThisTweetFromIrrelevantUsers = isThisTweetFromIrrelevantUsers(status);
      boolean isTweetDuplicate = isTweetDuplicate(tweet, mediaURL, url, twitterPOHM, tweetHM);
      if (!isTweetDuplicate && !tweet.isEmpty() && !isThisTweetFromIrrelevantUsers) {
        User user = status.getUser();
        TwitterUser twitterUser = new TwitterUser();
        twitterUser.setMiniProfileImageURL(user.getMiniProfileImageURL());
        twitterUser.setName(user.getName());
        twitterUser.setUserName(user.getScreenName());
        TwitterPO twitterPO = new TwitterPO(twitterID, tweet, mediaURL, url, DateUtility
          .convertToLocalDateTime(status.getCreatedAt()), twitterUser);
        tweetHM.put(twitterID, tweet);
        twitterPOHM.put(twitterID, twitterPO);
      }
    }
    List<TwitterPO> twitterPOList = new ArrayList<>(twitterPOHM.values());
    twitterPOList.sort((a, b) -> b.getImageURI().compareTo(a.getImageURI()));
    return twitterPOList;
  }

  private boolean isThisTweetFromIrrelevantUsers(Status status) {
    return Arrays.asList(irrelevantTwitterUsers)
                 .contains(status.getUser().getScreenName());
  }

  /**
   * take a piece of the tweet check if it is already in the tweeterList if it's
   * there, it's duplicate, ignore it
   *
   * @param tweet      tweet
   * @param mediaURL   mediaURL
   * @param articleURI articleURI
   */
  private boolean isTweetDuplicate(String tweet, String mediaURL, String articleURI,
                                   Map<Long, TwitterPO> twitterPOHM, Map<Long, String> tweetHM) {
    String middleOfTheTweetString = TextUtility.getMiddleOfText(tweet);
    for (String tweetFromTheList : new ArrayList<>(tweetHM.values())) {
      String tweetFromTheListLC = tweetFromTheList.toLowerCase();
      if (tweetFromTheListLC.contains(middleOfTheTweetString.toLowerCase())
        || tweet.toLowerCase().contains(tweetFromTheListLC) ||
        tweetFromTheListLC.equalsIgnoreCase(tweet)) {
        if (mediaURL.isEmpty() || articleURI.isEmpty())
          return true;
        ifDuplicateRemoveTwitterPOFromList(twitterPOHM, tweetHM, tweetFromTheList);
      }

      // if it's not duplicate let's check it matches few words
      // check also if three words in the tweeterList
      if (null == regexSequenceOfWhiteCharacters) {
        throw new AssertionError();
      }
      String[] splitStr = tweet.toLowerCase().split(regexSequenceOfWhiteCharacters);
      int length = splitStr.length;
      if (length > 5 && tweetFromTheListLC.contains(splitStr[1]) && tweetFromTheListLC
        .contains(splitStr[2])
        && tweetFromTheListLC.contains(splitStr[3])) {
        if (mediaURL.isEmpty() || articleURI.isEmpty())
          return true;
        ifDuplicateRemoveTwitterPOFromList(twitterPOHM, tweetHM, tweetFromTheList);
      }

      // check also if last three words in the tweeterList
      if (length > 5 && tweetFromTheListLC
        .contains(splitStr[length - 1]) && tweetFromTheListLC
        .contains(splitStr[length - 2])
        && tweetFromTheListLC.contains(splitStr[length - 3])) {
        if (mediaURL.isEmpty() || articleURI.isEmpty())
          return true;
        ifDuplicateRemoveTwitterPOFromList(twitterPOHM, tweetHM, tweetFromTheList);
      }
    }
    return false;
  }

}
