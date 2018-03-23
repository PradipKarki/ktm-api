package com.ktm.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktm.twitter.model.TwitterPO;
import com.ktm.twitter.service.TwitterService;

import twitter4j.TwitterException;

@RestController
@RequestMapping("/twitter")
public class TwitterController {
	 
	private static final String SEARCH_QUERY_NEPAL = "nepal filter:news -filter:retweets";
	
	@Autowired
    private TwitterService twitterService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<TwitterPO> getTweets() throws TwitterException {
		String queryString = SEARCH_QUERY_NEPAL;
		return twitterService.getTweetsByQuery(queryString);
	}

}
