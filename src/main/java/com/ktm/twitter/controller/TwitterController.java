package com.ktm.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktm.twitter.model.TwitterPO;
import com.ktm.twitter.service.TwitterService;

import twitter4j.TwitterException;

@RestController
@PropertySource("classpath:messages.properties")
@RequestMapping("/twitter")
public class TwitterController {
	
	@Autowired Environment env;
		
	@Autowired
    private TwitterService twitterService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<TwitterPO> getTweets() throws TwitterException {
		final String SEARCH_QUERY_NEPAL = this.env.getProperty("App.Nepal.TwitterSearchQueryKeyWord"); //$NON-NLS-1$
		return this.twitterService.getTweetsByQuery(SEARCH_QUERY_NEPAL);
	}

}
