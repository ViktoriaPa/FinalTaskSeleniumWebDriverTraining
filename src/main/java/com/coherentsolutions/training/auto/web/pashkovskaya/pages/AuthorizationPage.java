package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthorizationPage extends BasePage{
    @FindBy(id = "email")
    public WebElement emailTextField;
    @FindBy(id = "pass")
    public WebElement passwordTextField;
    @FindBy(xpath = "//button [@class='action login primary']")
    public WebElement signInButton;

    public AuthorizationPage(WebDriver driver) {
        super(driver);
    }

    public HomePageAuthorizedUser openHomePageAuthorizedUser(String email, String password) {
        emailTextField.sendKeys(email);
        passwordTextField.sendKeys(password);
        signInButton.click();

        return new HomePageAuthorizedUser(driver);
    }
}
