package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageUnauthorizedUser extends BasePage{
    @FindBy(linkText = "Sign In")
    private WebElement signInLink;
    @FindBy(linkText = "Create an Account")
    private WebElement createAccountLink;

    public HomePageUnauthorizedUser(WebDriver driver) {
        super(driver);
    }
    public void clickSignInLink(){
        signInLink.click();
    }
    public void clickCreateAccountLink(){
        createAccountLink.click();
    }
    public AuthorizationPage openAuthorizationPage() {
        clickSignInLink();

        return new AuthorizationPage(driver);
    }

    public AccountCreationPage openAccountCreationPage() {
        clickCreateAccountLink();

        return new AccountCreationPage(driver);
    }
}
