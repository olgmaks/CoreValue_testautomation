package com.corevalue.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

@Component
public class HomePage extends PageObject {

    private static final Logger LOG = Logger.getLogger(HomePage.class);

    @FindBy(xpath = "//*[@data-utag-name='loans']")
    private WebElement loansButton;

    @FindBy(id = "topLangMenuItem")
    private WebElement currentLanguageLabel;

    @FindBy(xpath = "//*[@data-utag-name='mortgage_loan']")
    private WebElement mortgagesButton;

    public MortgagePage openMortgagesPage() {
        LOG.info("Open Mortgages page ...");
        loansButton.click();
        newWait().until(ExpectedConditions.visibilityOf(mortgagesButton)).click();
        return getBean(MortgagePage.class);
    }

    public HomePage waitForLoad() {
        newWait().until(ExpectedConditions.textToBePresentInElement(currentLanguageLabel, "FR"));
        return this;
    }
}
