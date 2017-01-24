package com.corevalue.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import ru.yandex.qatools.allure.annotations.Step;

@Component
public class MortgagePage extends PageObject {

    @FindBy(xpath = "//*[@data-utag-name='calculate_your_payments' and @data-utag-type='button']")
    private WebElement calculatePaymentsButton;

    @Step("Open Mortgage Payment Calculator Page ...")
    public MortgagePaymentCalculatorPage openMortgagePaymentCalculatorPage() {
        calculatePaymentsButton.click();
        return getBean(MortgagePaymentCalculatorPage.class);
    }
}
