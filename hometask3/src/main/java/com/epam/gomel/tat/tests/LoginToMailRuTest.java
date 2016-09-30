package com.epam.gomel.tat.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.gomel.tat.DriverTimeouts.IMPLICIT_WAIT;
import static com.epam.gomel.tat.DriverTimeouts.PAGE_LOAD;
import static com.epam.gomel.tat.tests.BaseMailRuLoginTest.*;

/**
 * Created by Dzmitry Sudalenka on 9/27/2016.
 */
public class LoginToMailRuTest {

    public static final String INCORRECT_PASS = "incorrect";
    public static final By ERROR_MSG_LOCATOR = By.xpath("//div[@class='error-msg']");
    public static final String INVALID_CREDENTIALS_PASSWORD = "Неправильный логин или пароль.";

    private WebDriver driver;

    @BeforeMethod
    private void setUpDriver() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT.getValue(), IMPLICIT_WAIT.getUnit());
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD.getValue(), PAGE_LOAD.getUnit());
    }

    @Test
    public void loginToMailWithCorrectPassword() {
        final String CORRECT_PASS = "1q@W3e$R";

        driver.get(MAIL_RU_MAIL);

        driver.findElement(LOGIN_INPUT_LOCATOR).sendKeys(LOGIN);
        driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(CORRECT_PASS);
        driver.findElement(LOGIN_FORM_SUBMIT_BUTTON_LOCATOR).click();

        Assert.assertEquals(driver.findElement(USER_NAME_LABEL_LOCATOR).getText(), EMAIL);
    }

    @Test
    public void loginToMailWithIncorrectPassword() {
        driver.get(MAIL_RU_MAIL);

        driver.findElement(LOGIN_INPUT_LOCATOR).sendKeys(EMAIL);
        driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(INCORRECT_PASS);
        driver.findElement(LOGIN_FORM_SUBMIT_BUTTON_LOCATOR).click();

        Assert.assertEquals(driver.findElement(ERROR_MSG_LOCATOR).getText(), INVALID_CREDENTIALS_PASSWORD);
    }

    @Test
    public void sendMail() {
        driver.get(MAIL_RU_MAIL);

        driver.findElement(LOGIN_INPUT_LOCATOR).sendKeys(EMAIL);
        driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(INCORRECT_PASS);
        driver.findElement(LOGIN_FORM_SUBMIT_BUTTON_LOCATOR).click();

        Assert.assertEquals(driver.findElement(ERROR_MSG_LOCATOR).getText(), INVALID_CREDENTIALS_PASSWORD);
    }

    @AfterMethod
    public void killDriver() {
        driver.quit();
    }
}
