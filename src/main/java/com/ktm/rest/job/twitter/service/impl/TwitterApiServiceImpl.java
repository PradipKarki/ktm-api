package com.ktm.rest.job.twitter.service.impl;

import static java.lang.Character.UnicodeBlock.DEVANAGARI;
import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsLast;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;

import com.ktm.exception.JobException;
import com.ktm.rest.job.twitter.builder.TwitterApiBuilder;
import com.ktm.rest.job.twitter.service.TwitterApiService;
import com.ktm.rest.twitter.model.TwitterPo;
import com.ktm.rest.twitter.repository.TwitterRepository;
import com.ktm.utils.TextUtility;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.Status;
import twitter4j.TwitterException;

@Service
public class TwitterApiServiceImpl implements TwitterApiService {
  private static final Logger logger = LoggerFactory.getLogger(TwitterApiServiceImpl.class);

  @Value("${Twitter.RegexSequenceOfWhiteCharacters}")
  private String regexSequenceOfWhiteCharacters;

  @Value("${Twitter.IrrelevantTwitterUsers}")
  private String[] irrelevantTwitterUsers;

  @Autowired private TwitterApiBuilder twitterApiBuilder;
  @Autowired private TwitterRepository twitterRepository;

  @Override
  public List<Status> getDataFromAPI(String queryString) {
    try {
      return Collections.unmodifiableList(
          twitterApiBuilder.getQueryResult(queryString).getTweets());
    } catch (TwitterException e) {
      String errorMessage = "Job failed during fetching tweets from Twitter API.";
      logger.error(String.format("Error Message %s %s", errorMessage, e.getMessage()));
      throw new JobException(errorMessage, e);
    }
  }

  @Override
  public List<TwitterPo> processData(List<TwitterPo> twitterPos) {
    List<TwitterPo> tweets =
        twitterPos
            .stream()
            .filter(t -> StringUtils.isNotEmpty(t.getTitle()))
            .filter(t -> !TextUtility.isThisUnicode(t.getTitle(), DEVANAGARI))
            .filter(t -> !isThisTweetFromIrrelevantUsers(t))
            .filter(t -> !isTwitterDuplicateOrSimilar(twitterPos, t))
            .sorted(comparing(TwitterPo::getImageUri, nullsLast(reverseOrder())))
            .collect(toList());
    tweets.forEach(t -> t.setTitle(TextUtility.cleanTweet(t.getTitle())));
    return Collections.unmodifiableList(tweets);
  }

  @Override
  public void saveToDB(List<TwitterPo> twitterPos) {
    twitterRepository.saveAll(twitterPos);
  }

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
}
