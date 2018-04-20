package com.ktm.utils;

import java.lang.Character.UnicodeBlock;

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
	
	public static String getMiddleOfText(final String text) {
		// get the middle of the text
		final int mid = text.length() / 2;
		String[] parts = { text.substring(0, mid), text.substring(mid) };
		// get the index of the second tweet upto middle
		final int indexOfTweet2 = (parts[1].length() / 2) + parts[0].length();
		String middleOfTheTweetString = text.substring(mid / 2, indexOfTweet2);
		return middleOfTheTweetString;
	}

	public static boolean isThisUnicode(final String text, UnicodeBlock unicodeBlock) {
		boolean isThisUnicodeBlock = false;
		for (char c: text.toCharArray())
			if (Character.UnicodeBlock.of(c) == unicodeBlock) {
				isThisUnicodeBlock = true;
				break;
			}
		return isThisUnicodeBlock;
	}

}
