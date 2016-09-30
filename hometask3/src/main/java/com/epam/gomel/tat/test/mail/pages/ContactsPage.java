package com.epam.gomel.tat.test.mail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactsPage extends MainPage {

    private static final String CONTACT_PATTERN_LOCATOR = 
            "//span[contains(text(), '%s')]//preceding::label[contains(@class, 'checkbox')]";

    public ContactsPage(WebDriver browser) {
        super(browser);
    }

    public ContactsPage chooseContactByName(String name) {
        browser.findElement(By.xpath(String.format(CONTACT_PATTERN_LOCATOR, name))).click();
        return this;
    }
}
