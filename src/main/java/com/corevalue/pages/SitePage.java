package com.corevalue.pages;

import org.springframework.stereotype.Component;

import ru.yandex.qatools.allure.annotations.Step;

@Component
public class SitePage extends PageObject {

    @Step("Navigating to home page ...")
    public HomePage gotoHomePage() {
        webDriver.navigate().to(appProperties.getBaseUrl());
        return getBean(HomePage.class);
    }
}
