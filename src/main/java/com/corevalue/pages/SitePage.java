package com.corevalue.pages;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SitePage extends PageObject {

    private static final Logger LOG = Logger.getLogger(HomePage.class);

    public HomePage gotoHomePage() {
        LOG.info(String.format("Navigating to home page URL [%s]", appProperties.getBaseUrl()));
        webDriver.navigate().to(appProperties.getBaseUrl());
        return getBean(HomePage.class);
    }
}
