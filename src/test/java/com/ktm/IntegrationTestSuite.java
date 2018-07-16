package com.ktm;

import com.ktm.documentary.controller.DocumentaryControllerIT;
import com.ktm.rss.controller.RssNewsControllerIT;
import com.ktm.twitter.controller.TwitterControllerIT;
import com.ktm.youtube.controller.YouTubeControllerIT;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    DocumentaryControllerIT.class,
    RssNewsControllerIT.class,
    TwitterControllerIT.class,
    YouTubeControllerIT.class
})
public class IntegrationTestSuite {

}
