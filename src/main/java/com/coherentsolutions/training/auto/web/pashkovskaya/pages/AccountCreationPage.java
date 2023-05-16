package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreationPage extends BasePage{
    @FindBy(id = "firstname")
    public WebElement firstNameTextField;
    @FindBy(id = "lastname")
    public WebElement lastNameTextField;
    @FindBy(id = "email_address")
    public WebElement emailTextField;
    @FindBy(id = "password")
    public WebElement passwordTextField;
    @FindBy(id = "password-confirmation")
    public WebElement passwordConfirmationTextField;
    @FindBy(xpath = "//button [@class='action submit primary']")
    public WebElement createAccountButton;

    public AccountCreationPage(WebDriver driver) {
        super(driver);
    }

    public HomePageAuthorizedUser openHomePageAuthorizedUser(String firstName, String lastName, String email, String password, String passwordConf) {
        firstNameTextField.sendKeys(firstName);
        lastNameTextField.sendKeys(lastName);
        emailTextField.sendKeys(email);
        passwordTextField.sendKeys(password);
        passwordConfirmationTextField.sendKeys(passwordConf);
        createAccountButton.click();

        return new HomePageAuthorizedUser(driver);
    }
}
