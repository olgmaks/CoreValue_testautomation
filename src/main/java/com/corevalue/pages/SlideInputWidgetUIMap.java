package com.corevalue.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SlideInputWidgetUIMap {

    private By sliderLocator;

    private By inputLocator;

    private By plusButtonLocator;

    private By minusButtonLocator;

    private By areaLeftFromSliderLocator;

    private By areaRightFromSliderLocator;

    private WebDriver webDriver;

    public SlideInputWidgetUIMap(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public SlideInputWidgetUIMap sliderLocator(By sliderLocator) {
        this.sliderLocator = sliderLocator;
        return this;
    }

    public SlideInputWidgetUIMap inputLocator(By inputLocator) {
        this.inputLocator = inputLocator;
        return this;
    }

    public SlideInputWidgetUIMap plusButtonLocator(By plusButtonLocator) {
        this.plusButtonLocator = plusButtonLocator;
        return this;
    }

    public SlideInputWidgetUIMap minusButtonLocator(By minusButtonLocator) {
        this.minusButtonLocator = minusButtonLocator;
        return this;
    }

    public SlideInputWidgetUIMap areaLeftFromSliderLocator(By areaLeftFromSliderLocator) {
        this.areaLeftFromSliderLocator = areaLeftFromSliderLocator;
        return this;
    }

    public SlideInputWidgetUIMap areaRightFromSliderLocator(By areaRightFromSliderLocator) {
        this.areaRightFromSliderLocator = areaRightFromSliderLocator;
        return this;
    }

    public WebElement getSlider() {
        return webDriver.findElement(sliderLocator);
    }

    public WebElement getInput() {
        return webDriver.findElement(inputLocator);
    }

    public WebElement getPlusButton() {
        return webDriver.findElement(plusButtonLocator);
    }

    public WebElement getMinusButton() {
        return webDriver.findElement(minusButtonLocator);
    }

    public WebElement getSliderAreaLeft() {
        return webDriver.findElement(areaLeftFromSliderLocator);
    }

    public WebElement getSliderAreaRight() {
        return webDriver.findElement(areaRightFromSliderLocator);
    }
}
