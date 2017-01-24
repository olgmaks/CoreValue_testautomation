package com.corevalue.tests;

import com.corevalue.context.SeleniumTestExecutionListener;
import com.corevalue.pages.*;

import org.openqa.selenium.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@TestExecutionListeners(SeleniumTestExecutionListener.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class IndustrialAllianceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SitePage sitePage;

    @Test
    public void testCalculatePayments() {

        HomePage homePage = sitePage.gotoHomePage().waitForLoad();
        MortgagePage mortgagePage = homePage.openMortgagesPage();
        MortgagePaymentCalculatorPage mortgagePaymentCalculatorPage = mortgagePage.openMortgagePaymentCalculatorPage();
        SlideInputWidget purchaseWidget = mortgagePaymentCalculatorPage.getPurchaseInputWidget();

        // Validate that the Purchase Price Slider movement works
        Point previousSliderPosition = purchaseWidget.getSliderPosition();
        purchaseWidget.slideRight(53.2);
        Point newSliderPosition = purchaseWidget.getSliderPosition();

        assertNotEquals(previousSliderPosition, newSliderPosition);
        assertTrue(previousSliderPosition.getX() < newSliderPosition.getX());
        assertEquals(previousSliderPosition.getY(), newSliderPosition.getY());

        // Validate that the Purchase Price Slider movement works
        previousSliderPosition = purchaseWidget.getSliderPosition();
        purchaseWidget.slideLeft(0.0);
        newSliderPosition = purchaseWidget.getSliderPosition();

        assertNotEquals(previousSliderPosition, newSliderPosition);
        assertTrue(previousSliderPosition.getX() > newSliderPosition.getX());
        assertEquals(previousSliderPosition.getY(), newSliderPosition.getY());

        purchaseWidget.setValueWithButton(500000);

        SlideInputWidget downPaymentWidget = mortgagePaymentCalculatorPage.getDownPaymentWidget();
        downPaymentWidget.setValueWithButton(50000);

        mortgagePaymentCalculatorPage.selectAmortization(15);

        mortgagePaymentCalculatorPage.selectPaymentFrequency("weekly");

        mortgagePaymentCalculatorPage.setInterestRateInput(5.0);

        mortgagePaymentCalculatorPage.calculate();

        assertEquals(mortgagePaymentCalculatorPage.getCalculationResult(), 836.75);

        System.out.println();
    }
}
