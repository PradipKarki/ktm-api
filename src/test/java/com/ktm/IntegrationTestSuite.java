package com.ktm;

import com.ktm.rest.documentary.controller.DocumentaryControllerIT;
import com.ktm.rest.rss.controller.RssNewsControllerIT;
import com.ktm.rest.twitter.controller.TwitterControllerIT;
import com.ktm.rest.youtube.controller.YouTubeControllerIT;
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
