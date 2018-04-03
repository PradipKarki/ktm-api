package com.ktm.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:messages.properties")
public class TextUtility {

	@Autowired Environment env;
	
	private final static String EMPTY_STRING = ""; //$NON-NLS-1$

	public String cleanTweetText(String tweet) {
		final String HTTP_PREFIX = this.env.getProperty("TextUtility.HttpPrefix"); //$NON-NLS-1$
		final String SPECIAL_CHARACTERS_EXCEPT_SPACES = this.env.getProperty("TextUtility.SpecialCharactersExceptSpaces"); //$NON-NLS-1$
		final String RT_KEYWORD = this.env.getProperty("TextUtility.RTWord"); //$NON-NLS-1$
		String cleanText = tweet;
		if (cleanText.contains(HTTP_PREFIX)) {
			int index = cleanText.indexOf(HTTP_PREFIX);
			if (index != 0) cleanText = cleanText.substring(0, index);
		}
		if (cleanText.contains(HTTP_PREFIX)) {
			int index = cleanText.indexOf(HTTP_PREFIX);
			if (index == 0) return EMPTY_STRING;
		} 
		cleanText = cleanText.replaceAll(SPECIAL_CHARACTERS_EXCEPT_SPACES, EMPTY_STRING).trim();
		cleanText = cleanText.replaceAll(RT_KEYWORD, EMPTY_STRING);
		return cleanText;
	}

}
