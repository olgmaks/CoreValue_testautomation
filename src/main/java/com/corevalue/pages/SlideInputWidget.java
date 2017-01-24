package com.corevalue.pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Point;
import org.springframework.stereotype.Component;

import ru.yandex.qatools.allure.annotations.Step;

@Component
public class SlideInputWidget extends PageObject {

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

    @Step("Moving Slider on SlideInputWidget to the right for [{0}] percents")
    public SlideInputWidget slideRight(double percents) {
        while (getSliderPercents() < percents) {
            actions().clickAndHold(uiMap.getSlider()).moveByOffset(10, 0).release().build().perform();
        }
        return this;
    }

    @Step("Moving Slider on SlideInputWidget to the left for [{0}] percents")
    public SlideInputWidget slideLeft(double percents) {
        while (getSliderPercents() > percents) {
            actions().clickAndHold(uiMap.getSlider()).moveByOffset(-10, 0).release().build().perform();
        }
        return this;
    }

    @Step("Setting slide input widget value [{0}] with '+' button")
    public SlideInputWidget setValueWithButton(double value) {
        while (Double.valueOf(uiMap.getInput().getAttribute("value")) < value) {
            uiMap.getPlusButton().click();
        }
        return this;
    }
}
