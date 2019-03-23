package com.ktm;

import com.ktm.rest.controller.DocumentaryControllerIT;
import com.ktm.rest.controller.RssNewsControllerIT;
import com.ktm.rest.controller.TwitterControllerIT;
import com.ktm.rest.controller.YouTubeControllerIT;
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
