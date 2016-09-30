package com.epam.gomel.tat.test.mail;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.gomel.tat.test.mail.pages.ListOfLettersPage;
import com.epam.gomel.tat.test.mail.pages.MainPage;

import static com.epam.gomel.tat.test.mail.SendAndSearchEmailTest.*;

public class WriteEmailFromContacts extends BaseMailRuLoginTest {

    public static final String CONTACT_NAME = EMAIL.split("@")[0];

    private String subject;
    private String textToBeSent;

    private String getRandomStirng() {
        return Long.toString(System.nanoTime());
    }

    @Test
    public void writeEmailToContact() {
        subject = getRandomStirng();
        textToBeSent = getRandomStirng();
        new MainPage(driver).openContacts().chooseContactByName(CONTACT_NAME).clickCompose().typeSubject(subject)
                .typeBody(textToBeSent).send();
    }

    @Test(dependsOnMethods = "writeEmailToContact")
    public void checkInbox() {
        ListOfLettersPage inbox = new MainPage(driver).openInbox();
        Assert.assertEquals(inbox.getSubject(), subject);
        Assert.assertEquals(inbox.getFirstLine(), textToBeSent);
    }
}
