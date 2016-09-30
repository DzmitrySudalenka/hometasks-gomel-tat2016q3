package com.epam.gomel.tat.test.mail;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.gomel.tat.test.mail.pages.ListOfLettersPage;
import com.epam.gomel.tat.test.mail.pages.MainPage;

public class SendAndSearchEmailTest extends BaseMailRuLoginTest {
    private String subject;
    private String textToBeSent;

    private String getRandomStirng() {
        return Long.toString(System.nanoTime());
    }

    @Test
    public void sendEmail() {
        subject = getRandomStirng();
        textToBeSent = getRandomStirng();
        new MainPage(driver).clickCompose().typeTo(EMAIL).typeSubject(subject).typeBody(textToBeSent).send();
    }

    @Test(dependsOnMethods = "sendEmail")
    public void checkSent() {
        ListOfLettersPage outbox = new MainPage(driver).openOutbox();
        Assert.assertEquals(outbox.getSubject(), subject);
    }

    @Test(dependsOnMethods = "sendEmail")
    public void checkInbox() {
        ListOfLettersPage inbox = new MainPage(driver).openInbox();
        Assert.assertEquals(inbox.getSubject(), subject);
        Assert.assertEquals(inbox.getFirstLine(), textToBeSent);
    }

    @Test(dependsOnMethods = "sendEmail")
    public void searchEmailBySubject() {
        ListOfLettersPage searchResult = new MainPage(driver).makeSearch(subject);
        Assert.assertEquals(searchResult.getSubject(), subject);
        Assert.assertEquals(searchResult.getFirstLine(), textToBeSent);
    }
}
