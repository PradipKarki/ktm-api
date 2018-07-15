package com.ktm.twitter.service;

import static java.lang.Character.UnicodeBlock.DEVANAGARI;
import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsLast;

import com.ktm.twitter.builder.TwitterBuilder;
import com.ktm.twitter.mapper.TwitterMapper;
import com.ktm.twitter.model.TwitterPo;
import com.ktm.utils.TextUtility;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.MediaEntity;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.URLEntity;

@Service
public class TwitterService {

  private static final String EMPTY_STRING = ""; //$NON-NLS-1$

  @Value("${Twitter.RegexSequenceOfWhiteCharacters}")
  private String regexSequenceOfWhiteCharacters;
  @Value("${Twitter.IrrelevantTwitterUsers}")
  private String[] irrelevantTwitterUsers;

  @Autowired
  private TwitterBuilder twitterBuilder;

  public static String getArticleUrl(Status status) {
    return Stream.of(status.getURLEntities()).findAny()
                 .filter(u -> !u.getURL().isEmpty())
                 .map(URLEntity::getURL)
                 .orElse(EMPTY_STRING);
  }

  public static String getMediaUrl(Status status) {
    return Stream.of(status.getMediaEntities()).findAny()
                 .filter(m -> !m.getMediaURL().isEmpty())
                 .map(MediaEntity::getMediaURL)
                 .orElse(EMPTY_STRING);
  }

  /**
   * Helper method to return if Twitter is similar or Duplicate, and update original twitterPos
   * list with removing duplicate item every iteration.
   *
   * @param twitterPos List of the Twitter objects
   * @param twitterPo  Twitter object
   * @return Returns true if given Twitter object is already in the Twitter objects list
   */
  private boolean isTwitterDuplicateOrSimilar(List<TwitterPo> twitterPos, TwitterPo twitterPo) {
    String[] splitStr = twitterPo.getTitle().split(regexSequenceOfWhiteCharacters);
    String titleSubString = TextUtility.extractMiddleText(twitterPo.getTitle());
    Long count = twitterPos.stream()
                           .filter(t -> t.getTitle().contains(titleSubString) ||
                               TextUtility.containsFirstThreeWords(t.getTitle(), splitStr) ||
                               TextUtility.containsLastThreeWords(t.getTitle(), splitStr))
                           .count();
    if (count > 1L &&
        StringUtils.isAnyEmpty(twitterPo.getImageUri(), twitterPo.getArticleUri())) {
      twitterPos.remove(twitterPo);
      return true;
    }
    return false;
  }

  private boolean isThisTweetFromIrrelevantUsers(TwitterPo twitterPo) {
    return Arrays.asList(irrelevantTwitterUsers)
                 .contains(twitterPo.getTwitterUser().getUserName());
  }

  public List<TwitterPo> getTweetsByQuery(String queryString) throws TwitterException {
    QueryResult result = twitterBuilder.getQueryResult(queryString);
    List<TwitterPo> twitterPos = Mappers.getMapper(TwitterMapper.class)
                                        .toTwitterPo(result.getTweets());
    List<TwitterPo> tweets =
        twitterPos.stream()
                  .filter(t -> !TextUtility.isThisUnicode(t.getTitle(), DEVANAGARI))
                  .filter(t -> !isThisTweetFromIrrelevantUsers(t))
                  .filter(t -> !isTwitterDuplicateOrSimilar(twitterPos, t))
                  .sorted(comparing(TwitterPo::getImageUri, nullsLast(naturalOrder())))
                  .collect(Collectors.toList());
    tweets.forEach(t -> t.setTitle(new TextUtility().cleanTweet(t.getTitle())));
    return tweets;
  }

}
