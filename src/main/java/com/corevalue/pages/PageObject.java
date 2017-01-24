package com.corevalue.pages;

import com.corevalue.properties.AppProperties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public abstract class PageObject {

    private static final int TIME_OUT_IN_SECONDS = 10;

    @Autowired
    protected WebDriver webDriver;
    @Autowired
    protected ApplicationContext context;
    @Autowired
    protected AppProperties appProperties;

    protected Actions actions() {
        return new Actions(webDriver);
    }

    protected WebDriverWait newWait() {
        return new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS);
    }

    protected <T> T getBean(Class<T> pageType) {
        return context.getBean(pageType);
    }
}
