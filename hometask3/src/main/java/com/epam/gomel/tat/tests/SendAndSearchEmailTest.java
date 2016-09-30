package com.epam.gomel.tat.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SendAndSearchEmailTest extends BaseMailRuLoginTest {

    protected static final By FIELD_SUBJECT_LOCATOR =
            By.xpath("//input[@name='Subject']");
    protected static final By SEND_BUTTON_LOCATOR = By.xpath("//div[@data-name='send']");
    protected static final By INBOX_LINK_LOCATOR =
            By.xpath("//a[contains(@class, 'b-nav__link') and contains(@href, 'inbox')]");
    protected static final By SUBJECT_IN_LIST_LOCATOR = By.xpath("//div[contains(@class, 'b-datalist__item__subj')]");
    protected static final By FIRSTLINE_IN_LIST_LOCATOR =
            By.xpath("//span[contains(@class, 'b-datalist__item__subj__snippet')]");
    private static final By COMPOSE_BUTTON_LOCATOR = By.xpath("//a[@data-name='compose']");
    private static final By FIELD_TO_LOCATOR =
            By.xpath("//textarea[@data-original-name='To']");
    private static final By BODY_LOCATOR_FORMATED =
            By.xpath("//iframe[contains(@id, 'compose')]");
    private static final By SENT_LINK_LOCATOR =
            By.xpath("//a[contains(@class, 'b-nav__link') and contains(@href, 'sent')]");
    private static final By SEARCH_INPUT_LOCATOR = By.xpath("//input[contains(@class, 'js-search-input')]");
    private static final By SEARCH_BUTTON_LOCATOR = By.xpath("//button[contains(@class, 'js-search-button')]");
    private static final By FILE_TO_DOWNLOAD_LOCATOR =
            By.xpath("//a[contains(@class, 'mail-ui-Button') and contains(@href,'to_upload.txt')]");

    private String subject;
    private String textToBeSent;

    private String getRandomStirng() {
        return Long.toString(System.nanoTime());
    }

    @Test
    public void sendEmail() throws InterruptedException {
        driver.findElement(COMPOSE_BUTTON_LOCATOR).click();
        driver.findElement(FIELD_TO_LOCATOR).sendKeys(EMAIL);
        subject = getRandomStirng();
        driver.findElement(FIELD_SUBJECT_LOCATOR).sendKeys(subject);
        textToBeSent = getRandomStirng();
        driver.findElement(BODY_LOCATOR_FORMATED).sendKeys(textToBeSent);
        driver.findElement(SEND_BUTTON_LOCATOR).click();
    }

    @Test(dependsOnMethods = "sendEmail")
    public void checkSent() throws InterruptedException {
        driver.findElement(SENT_LINK_LOCATOR).click();

        String subjectLocatorStr = String.format("//a[@data-subject='%s']", subject);
        By subjectLocator = By.xpath(subjectLocatorStr);
        Assert.assertTrue(driver.findElement(subjectLocator).isDisplayed());
    }

    @Test(dependsOnMethods = "sendEmail")
    public void checkInbox() {
        driver.findElement(INBOX_LINK_LOCATOR).click();

        Assert.assertEquals(driver.findElement(SUBJECT_IN_LIST_LOCATOR).getText().trim(), subject);
        Assert.assertEquals(driver.findElement(FIRSTLINE_IN_LIST_LOCATOR).getText().trim(), textToBeSent);
    }

    @Test(dependsOnMethods = "checkInbox")
    public void checkDownloadAttachment() throws InterruptedException {
        driver.findElement(INBOX_LINK_LOCATOR).click();
        driver.findElement(SUBJECT_IN_LIST_LOCATOR).click();
        driver.findElement(FILE_TO_DOWNLOAD_LOCATOR).click();

        // wait for file download
    }

    @Test(dependsOnMethods = "sendEmail")
    public void searchEmailBySubject() {
        driver.findElement(SEARCH_INPUT_LOCATOR).click();
        driver.findElement(SEARCH_INPUT_LOCATOR).sendKeys(subject);
        driver.findElement(SEARCH_BUTTON_LOCATOR).click();

        Assert.assertEquals(driver.findElement(SUBJECT_IN_LIST_LOCATOR).getText().trim(), subject);
        Assert.assertEquals(driver.findElement(FIRSTLINE_IN_LIST_LOCATOR).getText().trim(), textToBeSent);
    }
}
