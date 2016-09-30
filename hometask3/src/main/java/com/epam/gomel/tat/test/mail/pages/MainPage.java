package com.epam.gomel.tat.test.mail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbstractPage {
    private static final By COMPOSE_BUTTON_LOCATOR = By.xpath("//a[@data-name='compose']");
    private static final By SENT_LINK_LOCATOR =
            By.xpath("//a[contains(@class, 'b-nav__link') and contains(@href, 'sent')]");
    private static final By INBOX_LINK_LOCATOR =
            By.xpath("//a[contains(@class, 'b-nav__link') and contains(@href, 'inbox')]");
    private static final By USER_NAME_LABEL_LOCATOR = By.xpath("//i[@id='PH_user-email']");
    private static final By CONTACTS_LINK_LOCATOR = By.xpath("//a[@href='#contacts']");
    private static final By MESSAGE_SENT_LOCATOR = By.xpath("//div[contains(@class, 'message-sent')]");

    public MainPage(WebDriver browser) {
        super(browser);
        waitForElement(USER_NAME_LABEL_LOCATOR);
    }

    public ComposePage clickCompose() {
        browser.findElement(COMPOSE_BUTTON_LOCATOR).click();
        return new ComposePage(browser);
    }

    public ListOfLettersPage openOutbox() {
        waitForElement(MESSAGE_SENT_LOCATOR);
        browser.findElement(SENT_LINK_LOCATOR).click();
        browser.navigate().refresh();
        return new ListOfLettersPage(browser);
    }

    public ContactsPage openContacts() {
        browser.findElement(CONTACTS_LINK_LOCATOR).click();
        return new ContactsPage(browser);
    }

    public ListOfLettersPage openInbox() {
        browser.findElement(INBOX_LINK_LOCATOR).click();
        return new ListOfLettersPage(browser);
    }

    public String getUserName() {
        return browser.findElement(USER_NAME_LABEL_LOCATOR).getText();
    }

    public ListOfLettersPage makeSearch(String text) {
        return new SearchPage(browser).search(text);
    }
}
