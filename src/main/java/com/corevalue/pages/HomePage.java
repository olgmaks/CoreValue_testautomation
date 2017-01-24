package com.corevalue.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

import ru.yandex.qatools.allure.annotations.Step;

@Component
public class HomePage extends PageObject {

    @FindBy(xpath = "//*[@data-utag-name='loans']")
    private WebElement loansButton;

    @FindBy(id = "topLangMenuItem")
    private WebElement currentLanguageLabel;

    @FindBy(xpath = "//*[@data-utag-name='mortgage_loan']")
    private WebElement mortgagesButton;

    @Step("Open Mortgages page ...")
    public MortgagePage openMortgagesPage() {
        loansButton.click();
        newWait().until(ExpectedConditions.visibilityOf(mortgagesButton)).click();
        return getBean(MortgagePage.class);
    }

    @Step("Wait until home page loading ...")
    public HomePage waitForLoad() {
        newWait().until(ExpectedConditions.textToBePresentInElement(currentLanguageLabel, "FR"));
        return this;
    }
}
