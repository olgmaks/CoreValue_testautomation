package com.corevalue.asserter;

import com.corevalue.pages.MortgagePaymentCalculatorPage;

import org.openqa.selenium.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.yandex.qatools.allure.annotations.Step;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Component
public class MortgageCalculatorPageAsserter {

    private MortgagePaymentCalculatorPage mortgagePaymentCalculatorPage;

    @Autowired
    public MortgageCalculatorPageAsserter(MortgagePaymentCalculatorPage mortgagePaymentCalculatorPage) {
        this.mortgagePaymentCalculatorPage = mortgagePaymentCalculatorPage;
    }

    @Step("Verify that slider has been moved right from [{0}] to [{1}]")
    public void assertSliderWidgetMovedRight(Point beginPoint, Point endPoint) {
        // Asserting that actual slider position has been changed
        assertTrue(beginPoint.getX() < endPoint.getX(), "Slider has not been moved to the right");
        assertEquals(beginPoint.getY(), endPoint.getY(), "Slider has been moved by vertical axis but should not be");
    }

    @Step("Verify that slider has been moved left from [{0}] to [{1}]")
    public void assertSliderWidgetMovedLeft(Point beginPoint, Point endPoint) {
        // Asserting that actual slider position has been changed
        assertTrue(beginPoint.getX() > endPoint.getX(), "Slider has not been moved to the right");
        assertEquals(beginPoint.getY(), endPoint.getY(), "Slider has been moved by vertical axis but should not be");
    }

    @Step("Verify that calculated payment value equals to [{0}]")
    public void assertCalculatedPaymentValue(double value) {
        // Asserting that actual slider position has been changed
        assertEquals(mortgagePaymentCalculatorPage.getCalculationResult(), value,
                "Calculated payment value does not meet expectation");
    }
}
