package com.epam.gomel.tat.test.mail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListOfLettersPage extends MainPage {
    private static final By SUBJECT_IN_LIST_LOCATOR = By.xpath("//div[contains(@class, 'b-datalist__item__subj')]");
    private static final By FIRSTLINE_IN_LIST_LOCATOR =
            By.xpath("//span[contains(@class, 'b-datalist__item__subj__snippet')]");

    public ListOfLettersPage(WebDriver browser) {
        super(browser);
        waitForElement(FIRSTLINE_IN_LIST_LOCATOR);
    }

    public String getSubject() {
        return browser.findElement(SUBJECT_IN_LIST_LOCATOR).getText().trim();
    }

    public String getFirstLine() {
        return browser.findElement(FIRSTLINE_IN_LIST_LOCATOR).getText().trim();
    }
}
