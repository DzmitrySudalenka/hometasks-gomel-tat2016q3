package com.epam.gomel.tat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

import static com.epam.gomel.tat.DriverTimeouts.*;

public class DriverFactory {

    public static final String DOWNLOADS_PATH = "D:\\downloads";
    public static final String PATH_TO_CHROME_DRIVER = "./src/main/resources/drivers/chromedriver.exe";

    public static WebDriver chromeDriver() {
        System.setProperty("webdriver.chrome.driver", PATH_TO_CHROME_DRIVER);

        String downloadFilepath = DOWNLOADS_PATH;
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        WebDriver driver = new ChromeDriver(cap);

        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT.getValue(), IMPLICIT_WAIT.getUnit());
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD.getValue(), PAGE_LOAD.getUnit());
        driver.manage().timeouts().setScriptTimeout(SCRIPT_TIMEOUT.getValue(), SCRIPT_TIMEOUT.getUnit());
        driver.manage().window().maximize();

        return driver;
    }

    public static WebDriver firefoxDriver() {
        FirefoxProfile fxProfile = new FirefoxProfile();

        fxProfile.setPreference("browser.download.folderList", 2);
        fxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        fxProfile.setPreference("browser.download.dir", DOWNLOADS_PATH);
        fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/txt;");

        WebDriver driver = new FirefoxDriver(fxProfile);

        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT.getValue(), IMPLICIT_WAIT.getUnit());
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD.getValue(), PAGE_LOAD.getUnit());
        driver.manage().window().maximize();

        return driver;
    }
}
