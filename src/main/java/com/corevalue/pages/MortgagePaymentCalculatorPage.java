package com.corevalue.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MortgagePaymentCalculatorPage extends PageObject {

    private static final Logger LOG = Logger.getLogger(HomePage.class);

    @FindBy(id = "Amortissement")
    private WebElement amortizationSelect;

    @FindBy(id = "FrequenceVersement")
    private WebElement paymentFrequencySelect;

    @FindBy(id = "TauxInteret")
    private WebElement interestRateInput;

    @FindBy(id = "btn_calculer")
    private WebElement calculateButton;

    @FindBy(id = "paiement-resultats")
    private WebElement calculationResultLabel;

    public MortgagePaymentCalculatorPage selectAmortization(int amortization) {
        LOG.info(String.format("Select amortization [%s]", amortization));
        new Select(amortizationSelect).selectByValue(String.valueOf(amortization));
        return this;
    }

    public MortgagePaymentCalculatorPage selectPaymentFrequency(String paymentFrequency) {
        LOG.info(String.format("Select payment frequency [%s]", paymentFrequency));
        new Select(paymentFrequencySelect).selectByVisibleText(paymentFrequency);
        return this;
    }

    public MortgagePaymentCalculatorPage setInterestRateInput(double rate) {
        LOG.info(String.format("Set Interest Rate Input [%s]", rate));
        interestRateInput.clear();
        interestRateInput.sendKeys(String.valueOf(rate));
        return this;
    }

    public MortgagePaymentCalculatorPage calculate() {
        LOG.info("Calculating ...");
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", calculateButton);
        newWait().until(ExpectedConditions.textToBePresentInElement(calculationResultLabel, "$"));
        return this;
    }

    public double getCalculationResult() {
        Pattern pattern = Pattern.compile("\\d+[.]?(\\d+)?");
        Matcher matcher = pattern.matcher(calculationResultLabel.getText());
        matcher.find();
        return Double.valueOf(matcher.group());
    }

    public SlideInputWidget getPurchaseInputWidget() {
        return getBean(SlideInputWidget.class).setUiMap(new SlideInputWidgetUIMap(webDriver)
                .sliderLocator(
                        By.xpath("(//*[contains(@class,'inputSlide-ia')])[1]//*[contains(@class,'min-slider-handle')]"))
                .inputLocator(By.id("PrixPropriete")) //
                .plusButtonLocator(By.id("PrixProprietePlus")) //
                .minusButtonLocator(By.id("PrixProprieteMinus")) //
                .areaLeftFromSliderLocator(
                        By.xpath("(//*[contains(@class,'inputSlide-ia')])[1]//*[contains(@class,'slider-selection')]"))
                .areaRightFromSliderLocator(By
                        .xpath("(//*[contains(@class,'inputSlide-ia')])[1]//*[contains(@class,'slider-track-high')]")));

    }

    public SlideInputWidget getDownPaymentWidget() {
        return getBean(SlideInputWidget.class).setUiMap(new SlideInputWidgetUIMap(webDriver)
                .sliderLocator(
                        By.xpath("(//*[contains(@class,'inputSlide-ia')])[2]//*[contains(@class,'min-slider-handle')]"))
                .inputLocator(By.id("MiseDeFond")) //
                .plusButtonLocator(By.id("MiseDeFondPlus")) //
                .minusButtonLocator(By.id("MiseDeFondMinus")));
    }
}
