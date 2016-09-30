package com.epam.gomel.tat.test.mail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends MainPage {

    private static final By SEARCH_INPUT_LOCATOR = By.xpath("//input[contains(@class, 'js-search-input')]");
    private static final By SEARCH_BUTTON_LOCATOR = By.xpath("//button[contains(@class, 'js-search-button')]");
    private static final By SEARCH_HISTORY_LOCATOR = By.xpath("//*[@data-key='view=history-suggest']");

    public SearchPage(WebDriver browser) {
        super(browser);
        waitForElement(SEARCH_INPUT_LOCATOR);
    }

    public ListOfLettersPage search(String text) {
        browser.findElement(SEARCH_INPUT_LOCATOR).sendKeys(text);
        waitForElement(SEARCH_HISTORY_LOCATOR);
        browser.findElement(SEARCH_BUTTON_LOCATOR).click();
        return new ListOfLettersPage(browser);
    }
}
