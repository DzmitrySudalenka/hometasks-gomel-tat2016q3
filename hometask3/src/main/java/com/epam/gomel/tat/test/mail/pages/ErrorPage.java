package com.epam.gomel.tat.test.mail.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ErrorPage {
    @FindBy(xpath = "//div[@class='b-login__errors']")
    private WebElement errorElement;

    public String getMessage() {
        return errorElement.getText();
    }
}
