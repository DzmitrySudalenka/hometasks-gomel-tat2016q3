package com.epam.gomel.tat.tests;

import com.epam.gomel.tat.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseMailRuLoginTest {

    public static final String LOGIN = "tat.gomel2016q3";
    public static final String EMAIL = LOGIN + "@mail.ru";
    public static final String CORRECT_PASS = "1q@W3e$R";
    public static final String MAIL_RU_MAIL = "https://mail.ru/";

    public static final By LOGIN_INPUT_LOCATOR = By.xpath("//input[@id='mailbox__login']");
    public static final By PASSWORD_INPUT_LOCATOR = By.xpath("//input[@id='mailbox__password']");
    public static final By LOGIN_FORM_SUBMIT_BUTTON_LOCATOR = By.xpath("//input[@id='mailbox__auth__button']");
    public static final By USER_NAME_LABEL_LOCATOR = By.xpath("//i[@id='PH_user-email']");

    protected WebDriver driver;

    @BeforeClass
    public void setUpDriver() {
        driver = DriverFactory.chromeDriver();
    }

    @BeforeClass(dependsOnMethods = "setUpDriver")
    public void loginToMail() {
        driver.get(MAIL_RU_MAIL);

        driver.findElement(LOGIN_INPUT_LOCATOR).sendKeys(LOGIN);
        driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(CORRECT_PASS);
        driver.findElement(LOGIN_FORM_SUBMIT_BUTTON_LOCATOR).click();

        Assert.assertEquals(driver.findElement(USER_NAME_LABEL_LOCATOR).getText(), EMAIL);
    }

    @AfterClass
    public void killDriver() {
        driver.quit();
    }
}
