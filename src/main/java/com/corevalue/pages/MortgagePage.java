package com.corevalue.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class MortgagePage extends PageObject {

    private static final Logger LOG = Logger.getLogger(HomePage.class);

    @FindBy(xpath = "//*[@data-utag-name='calculate_your_payments' and @data-utag-type='button']")
    private WebElement calculatePaymentsButton;

    public MortgagePaymentCalculatorPage openMortgagePaymentCalculatorPage() {
        LOG.info("Open Mortgage Payment Calculator Page ...");
        calculatePaymentsButton.click();
        return getBean(MortgagePaymentCalculatorPage.class);
    }
}
