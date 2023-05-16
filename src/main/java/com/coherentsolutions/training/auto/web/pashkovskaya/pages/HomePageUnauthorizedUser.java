package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageUnauthorizedUser extends BasePage{
    @FindBy(linkText = "Sign In")
    public WebElement signInLink;
    @FindBy(linkText = "Create an Account")
    public WebElement createAccountLink;

    public HomePageUnauthorizedUser(WebDriver driver) {
        super(driver);
    }

    public AuthorizationPage openAuthorizationPage() {
        signInLink.click();

        return new AuthorizationPage(driver);
    }

    public AccountCreationPage openAccountCreationPage() {
        createAccountLink.click();

        return new AccountCreationPage(driver);
    }
}
