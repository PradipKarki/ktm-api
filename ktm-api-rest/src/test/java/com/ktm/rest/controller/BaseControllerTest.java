package com.ktm.rest.controller;

import com.ktm.library.core.KtmLibConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(secure = false)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@Import(KtmLibConfig.class)
public abstract class BaseControllerTest {}
