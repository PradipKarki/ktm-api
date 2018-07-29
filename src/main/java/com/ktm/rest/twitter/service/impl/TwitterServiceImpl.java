package com.ktm.rest.twitter.service.impl;

import static java.lang.Character.UnicodeBlock.DEVANAGARI;
import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsLast;
import static java.util.Comparator.reverseOrder;

import com.ktm.rest.twitter.builder.TwitterBuilder;
import com.ktm.rest.twitter.mapper.TwitterMapper;
import com.ktm.rest.twitter.model.TwitterPo;
import com.ktm.rest.twitter.service.TwitterService;
import com.ktm.utils.TextUtility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.QueryResult;
import twitter4j.TwitterException;

@Service
public class TwitterServiceImpl implements TwitterService {

  @Value("${Twitter.RegexSequenceOfWhiteCharacters}")
  private String regexSequenceOfWhiteCharacters;

  @Value("${Twitter.IrrelevantTwitterUsers}")
  private String[] irrelevantTwitterUsers;

  @Autowired private TwitterBuilder twitterBuilder;

  /**
   * Helper method to return if Twitter is similar or Duplicate, and update original twitterPos list
   * with removing duplicate item every iteration.
   *
   * @param twitterPos List of the Twitter objects
   * @param twitterPo Twitter object
   * @return Returns true if given Twitter object is already in the Twitter objects list
   */
  private boolean isTwitterDuplicateOrSimilar(List<TwitterPo> twitterPos, TwitterPo twitterPo) {
    String[] splitStr = twitterPo.getTitle().split(regexSequenceOfWhiteCharacters);
    String titleSubString = TextUtility.extractMiddleText(twitterPo.getTitle());
    long count =
        twitterPos
            .stream()
            .filter(
                t ->
                    t.getTitle().contains(titleSubString)
                        || TextUtility.containsFirstThreeWords(t.getTitle(), splitStr)
                        || TextUtility.containsLastThreeWords(t.getTitle(), splitStr))
            .count();
    if (count > 1L && StringUtils.isAnyEmpty(twitterPo.getImageUri(), twitterPo.getArticleUri())) {
      twitterPos.remove(twitterPo);
      return true;
    }
    return false;
  }

  private boolean isThisTweetFromIrrelevantUsers(TwitterPo twitterPo) {
    return Arrays.asList(irrelevantTwitterUsers).contains(twitterPo.getTwitterUser().getUserName());
  }

  @Override
  public List<TwitterPo> getTweetsByQuery(String queryString) throws TwitterException {
    QueryResult result = twitterBuilder.getQueryResult(queryString);
    List<TwitterPo> twitterPos =
        Mappers.getMapper(TwitterMapper.class).toTwitterPo(result.getTweets());
    List<TwitterPo> unModTwitterPos = new ArrayList<>(twitterPos);
    List<TwitterPo> tweets =
        unModTwitterPos
            .stream()
            .filter(t -> StringUtils.isNotEmpty(t.getTitle()))
            .filter(t -> !TextUtility.isThisUnicode(t.getTitle(), DEVANAGARI))
            .filter(t -> !isThisTweetFromIrrelevantUsers(t))
            .filter(t -> !isTwitterDuplicateOrSimilar(twitterPos, t))
            .sorted(comparing(TwitterPo::getImageUri, nullsLast(reverseOrder())))
            .collect(Collectors.toList());
    tweets.forEach(t -> t.setTitle(TextUtility.cleanTweet(t.getTitle())));
    return tweets;
  }
}
