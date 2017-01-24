package com.corevalue.tests;

import com.corevalue.asserter.MortgageCalculatorPageAsserter;
import com.corevalue.context.SeleniumTestExecutionListener;
import com.corevalue.pages.*;

import org.openqa.selenium.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@TestExecutionListeners(SeleniumTestExecutionListener.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class IndustrialAllianceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SitePage sitePage;

    @Autowired
    private MortgageCalculatorPageAsserter mortgageCalculatorPageAsserter;

    @Test
    public void testCalculatePayments() {

        // Open the www.ia.ca in the Chrome browser
        HomePage homePage = sitePage.gotoHomePage().waitForLoad();

        // Open Mortgages page
        MortgagePage mortgagePage = homePage.openMortgagesPage();

        // Open Mortgage Payment Calculator Page
        MortgagePaymentCalculatorPage mortgagePaymentCalculatorPage = mortgagePage.openMortgagePaymentCalculatorPage();
        SlideInputWidget purchaseWidget = mortgagePaymentCalculatorPage.getPurchaseInputWidget();

        // Validate that the Purchase Price Slider movement works

        // Moving slider to the right up to 53.2%
        Point previousSliderPosition = purchaseWidget.getSliderPosition();
        purchaseWidget.slideRight(53.2);
        Point newSliderPosition = purchaseWidget.getSliderPosition();

        // Asserting that actual slider position has been changed
        mortgageCalculatorPageAsserter.assertSliderWidgetMovedRight(previousSliderPosition, newSliderPosition);

        // Moving slider to the left up to 0.0%
        previousSliderPosition = purchaseWidget.getSliderPosition();
        purchaseWidget.slideLeft(0.0);
        newSliderPosition = purchaseWidget.getSliderPosition();

        // Asserting that actual slider position has been changed
        mortgageCalculatorPageAsserter.assertSliderWidgetMovedLeft(previousSliderPosition, newSliderPosition);

        // Change the Purchase Price to 500 000 using the + button of the slider
        purchaseWidget.setValueWithButton(500000);

        // Change the Down Payment to 50 000 using the + button of the slider
        SlideInputWidget downPaymentWidget = mortgagePaymentCalculatorPage.getDownPaymentWidget();
        downPaymentWidget.setValueWithButton(50000);

        // Select 15 years for Amortization
        mortgagePaymentCalculatorPage.selectAmortization(15);

        // Select Weekly for Payment Frequency
        mortgagePaymentCalculatorPage.selectPaymentFrequency("weekly");

        // Change the Interest Rate to 5%
        mortgagePaymentCalculatorPage.setInterestRateInput(5.0);

        // Click the Calculate button
        mortgagePaymentCalculatorPage.calculate();

        // Verify that the weekly payments value is 836.75
        mortgageCalculatorPageAsserter.assertCalculatedPaymentValue(836.75);
    }
}
