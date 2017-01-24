package com.corevalue.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Point;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SlideInputWidget extends PageObject {

    private static final Logger LOG = Logger.getLogger(HomePage.class);

    private SlideInputWidgetUIMap uiMap;

    public SlideInputWidget setUiMap(SlideInputWidgetUIMap uiMap) {
        this.uiMap = uiMap;
        return this;
    }

    private double getSliderPercents() {
        String sliderStyleAttribute = uiMap.getSlider().getAttribute("style");
        Pattern pattern = Pattern.compile("(?<=[left: ])\\d+[.]?(\\d+)?");
        Matcher matcher = pattern.matcher(sliderStyleAttribute);
        matcher.find();
        return Double.valueOf(matcher.group());
    }

    public Point getSliderPosition() {
        return uiMap.getSlider().getLocation();
    }

    public SlideInputWidget slideRight(double percents) {
        LOG.info(String.format("Sliding right to [%s] percents", percents));
        while (getSliderPercents() < percents) {
            actions().clickAndHold(uiMap.getSlider()).moveByOffset(10, 0).release().build().perform();
        }
        return this;
    }

    public SlideInputWidget slideLeft(double percents) {
        LOG.info(String.format("Sliding left to [%s] percents", percents));
        while (getSliderPercents() > percents) {
            actions().clickAndHold(uiMap.getSlider()).moveByOffset(-10, 0).release().build().perform();
        }
        return this;
    }

    public SlideInputWidget setValueWithButton(double value) {
        LOG.info(String.format("Setting slide input widget value [%s]", value));
        while (Double.valueOf(uiMap.getInput().getAttribute("value")) < value) {
            uiMap.getPlusButton().click();
        }
        return this;
    }
}
