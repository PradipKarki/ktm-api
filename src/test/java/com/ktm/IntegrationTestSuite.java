package com.ktm;

import com.ktm.rss.controller.RssNewsControllerIT;
import com.ktm.twitter.controller.TwitterControllerIT;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    RssNewsControllerIT.class,
    TwitterControllerIT.class
})
public class IntegrationTestSuite {

}
