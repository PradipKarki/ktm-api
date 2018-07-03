package com.ktm.utils;

import java.lang.Character.UnicodeBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:messages.properties")
public class TextUtility {

    private static final String EMPTY_STRING = ""; //$NON-NLS-1$
    @Autowired
    Environment env;

    public static String getMiddleOfText(String text) {
        // get the middle of the text
        int mid = text.length() / 2;
        String[] parts = {text.substring(0, mid), text.substring(mid)};
        // get the index of the second tweet up to middle
        int indexOfTweet2 = (parts[1].length() / 2) + parts[0].length();
        return text.substring(mid / 2, indexOfTweet2);
    }

    public static boolean isThisUnicode(String text, UnicodeBlock unicodeBlock) {
        boolean isThisUnicodeBlock = false;
        for (char c : text.toCharArray())
            if (Character.UnicodeBlock.of(c) == unicodeBlock) {
                isThisUnicodeBlock = true;
                break;
            }
        return isThisUnicodeBlock;
    }

    public String cleanTweetText(String tweet) {
        String httpPrefix = this.env.getProperty("TextUtility.HttpPrefix"); //$NON-NLS-1$
        String specialCharactersExceptSpaces = this.env
                .getProperty("TextUtility.SpecialCharactersExceptSpaces"); //$NON-NLS-1$
        String rtKeyword = this.env.getProperty("TextUtility.RTWord"); //$NON-NLS-1$
        String cleanText = tweet;
        if (cleanText.contains(httpPrefix)) {
            int index = cleanText.indexOf(httpPrefix);
            if (index != 0) cleanText = cleanText.substring(0, index);
        }
        if (cleanText.contains(httpPrefix)) {
            int index = cleanText.indexOf(httpPrefix);
            if (index == 0) return EMPTY_STRING;
        }
        cleanText = cleanText.replaceAll(specialCharactersExceptSpaces, EMPTY_STRING).trim();
        cleanText = cleanText.replaceAll(rtKeyword, EMPTY_STRING);
        return cleanText;
    }

}
