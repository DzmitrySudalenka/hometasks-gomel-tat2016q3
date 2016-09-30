package com.epam.gomel.tat.test.mail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {
    private static final By LOGIN_INPUT_LOCATOR = By.xpath("//input[@id='mailbox__login']");
    private static final By PASSWORD_INPUT_LOCATOR = By.xpath("//input[@id='mailbox__password']");
    private static final By LOGIN_FORM_SUBMIT_BUTTON_LOCATOR = By.xpath("//input[@id='mailbox__auth__button']");

    public LoginPage(WebDriver browser) {
        super(browser);
    }

    public MainPage login(String email, String password) {
        doLogin(email, password);
        return new MainPage(browser);
    }

    public ErrorPage failLogin(String email, String password) {
        doLogin(email, password);
        return PageFactory.initElements(browser, ErrorPage.class);
    }

    private void doLogin(String email, String password) {
        browser.findElement(LOGIN_INPUT_LOCATOR).sendKeys(email);
        browser.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(password);
        browser.findElement(LOGIN_FORM_SUBMIT_BUTTON_LOCATOR).click();
    }
}
