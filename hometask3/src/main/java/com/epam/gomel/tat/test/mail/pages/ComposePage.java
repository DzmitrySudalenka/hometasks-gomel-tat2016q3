package com.epam.gomel.tat.test.mail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ComposePage extends MainPage {

    private static final By FIELD_TO_LOCATOR = By.xpath("//textarea[@data-original-name='To']");
    private static final By FIELD_SUBJECT_LOCATOR = By
            .xpath("//input[@name='Subject']");
    private static final By BODY_LOCATOR = By
            .xpath("//iframe[contains(@id, 'compose')]");
    private static final By SEND_BUTTON_LOCATOR = By.xpath("//div[@data-name='send']");

    public ComposePage(WebDriver browser) {
        super(browser);
        waitForElement(BODY_LOCATOR);
    }

    public ComposePage typeTo(String email) {
        browser.findElement(FIELD_TO_LOCATOR).sendKeys(email);
        return this;
    }

    public ComposePage typeSubject(String subject) {
        browser.findElement(FIELD_SUBJECT_LOCATOR).sendKeys(subject);
        return this;
    }

    public ComposePage typeBody(String body) {
        browser.findElement(BODY_LOCATOR).sendKeys(body);
        return this;
    }

    public MainPage send() {
        browser.findElement(SEND_BUTTON_LOCATOR).click();
        return new MainPage(browser);
    }
}
