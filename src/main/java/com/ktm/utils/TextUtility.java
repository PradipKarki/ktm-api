package com.ktm.utils;

public class TextUtility {
	public static String cleanTweetText(String tweet) {
		String cleanText = tweet;
		if (cleanText.contains("http")) {
			int index = cleanText.indexOf("http");
			if (index != 0) cleanText = cleanText.substring(0, index);
		}
		if (cleanText.contains("http")) {
			int index = cleanText.indexOf("http");
			if (index == 0) return "";
		} 
		// Delete all special characters except spaces
		cleanText = cleanText.replaceAll("[^a-zA-Z0-9 ]+", "").trim();
		// Delete all RT (retweets flags)
		cleanText = cleanText.replaceAll("RT", "");
		return cleanText;
	}

}
