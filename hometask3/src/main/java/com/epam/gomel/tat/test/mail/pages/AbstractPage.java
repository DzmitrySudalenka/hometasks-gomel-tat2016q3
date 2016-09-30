package com.epam.gomel.tat.test.mail.pages;

import static com.epam.gomel.tat.test.DriverTimeouts.EXPLICIT_SECONDS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    protected WebDriver browser;

    public AbstractPage(WebDriver browser) {
        this.browser = browser;
    }

    protected WebElement waitForElement(By locator) {
        return new WebDriverWait(browser, EXPLICIT_SECONDS.getValue())
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
