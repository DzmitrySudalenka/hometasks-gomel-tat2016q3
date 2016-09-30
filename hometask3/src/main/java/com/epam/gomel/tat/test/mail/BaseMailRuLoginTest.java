package com.epam.gomel.tat.test.mail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.epam.gomel.tat.test.mail.pages.LoginPage;

import static com.epam.gomel.tat.test.DriverTimeouts.IMPLICIT_WAIT;
import static com.epam.gomel.tat.test.DriverTimeouts.PAGE_LOAD;

public class BaseMailRuLoginTest {

    public static final String LOGIN = "tat.gomel2016q3";
    public static final String EMAIL = LOGIN + "@mail.ru";
    public static final String CORRECT_PASS = "1q@W3e$R";
    public static final String MAIL_RU_MAIL = "https://mail.ru/";

    protected WebDriver driver;

    @BeforeClass
    public void setUpDriver() {
        // System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver_lin32v2.21");
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT.getValue(), IMPLICIT_WAIT.getUnit());
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD.getValue(), PAGE_LOAD.getUnit());
        driver.manage().window().maximize();
    }

    @BeforeClass(dependsOnMethods = "setUpDriver")
    public void loginToMail() {
        driver.get(MAIL_RU_MAIL);
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.login(EMAIL, CORRECT_PASS).getUserName(), EMAIL);
    }

    @AfterClass
    public void killDriver() {
        driver.quit();
    }
}
