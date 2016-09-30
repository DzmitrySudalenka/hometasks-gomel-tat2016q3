package com.epam.gomel.tat.test.mail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.gomel.tat.test.mail.pages.ErrorPage;
import com.epam.gomel.tat.test.mail.pages.LoginPage;
import com.epam.gomel.tat.test.mail.pages.MainPage;

import static com.epam.gomel.tat.test.DriverTimeouts.IMPLICIT_WAIT;
import static com.epam.gomel.tat.test.DriverTimeouts.PAGE_LOAD;
import static com.epam.gomel.tat.test.mail.BaseMailRuLoginTest.*;

public class LoginToMailRuMailTest {

    public static final String INCORRECT_PASS = "incorrect";
    public static final String INVALID_CREDENTIALS_PASSWORD = "Неверное имя пользователя или пароль. "
            + "Проверьте правильность введенных данных.";

    private WebDriver driver;

    @BeforeMethod
    private void setUpDriver() {
        // System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver_lin32v2.21");
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT.getValue(), IMPLICIT_WAIT.getUnit());
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD.getValue(), PAGE_LOAD.getUnit());
        driver.manage().window().maximize();
    }

    @Test
    public void loginToMailWithCorrectPassword() {
        driver.get(MAIL_RU_MAIL);
        MainPage home = new LoginPage(driver).login(EMAIL, CORRECT_PASS);
        Assert.assertEquals(home.getUserName(), EMAIL);
    }

    @Test
    public void loginToMailWithIncorrectPassword() {
        driver.get(MAIL_RU_MAIL);
        ErrorPage errorPage = new LoginPage(driver).failLogin(EMAIL, INCORRECT_PASS);
        Assert.assertEquals(errorPage.getMessage(), INVALID_CREDENTIALS_PASSWORD);
    }

    @AfterMethod
    public void killDriver() {
        driver.quit();
    }
}
