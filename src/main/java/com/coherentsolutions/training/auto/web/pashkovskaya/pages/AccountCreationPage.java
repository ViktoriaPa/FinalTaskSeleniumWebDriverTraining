package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreationPage extends BasePage{
    @FindBy(id = "firstname")
    private WebElement firstNameTextField;
    @FindBy(id = "lastname")
    private WebElement lastNameTextField;
    @FindBy(id = "email_address")
    private WebElement emailTextField;
    @FindBy(id = "password")
    private WebElement passwordTextField;
    @FindBy(id = "password-confirmation")
    private WebElement passwordConfirmationTextField;
    @FindBy(xpath = "//button[@title='Create an Account']")
    private WebElement createAccountButton;
    @FindBy(id = "password-confirmation-error")
    private WebElement passwordMismatchError;
    @FindBy(xpath = "//div[@class='messages']")
    private WebElement accountHasBeenAlreadyCreatedError;

    public AccountCreationPage(WebDriver driver) {
        super(driver);
    }

    public String getPasswordMismatchError(){
        return passwordMismatchError.getText();
    }
    public String getAccountHasBeenAlreadyCreatedError(){
        return accountHasBeenAlreadyCreatedError.getText();
    }
    public boolean accountHasBeenAlreadyCreatedErrorIsDisplayed() {

        try {
            return accountHasBeenAlreadyCreatedError.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterFirstName(String firstName){
        firstNameTextField.sendKeys(firstName);
    }
    public void enterLastName(String lastName){
        lastNameTextField.sendKeys(lastName);
    }
    public void enterEmail(String email){
        emailTextField.sendKeys(email);
    }
    public void enterPassword(String password){
        passwordTextField.sendKeys(password);
    }
    public void enterPasswordConf(String passwordConf){
        passwordConfirmationTextField.sendKeys(passwordConf);
    }
    public void clickCreateAccountButton(){
        createAccountButton.click();
    }
    public HomePageAuthorizedUser openHomePageAuthorizedUser(String firstName, String lastName, String email, String password, String passwordConf) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPassword(password);
        enterPasswordConf(passwordConf);
        clickCreateAccountButton();

        return new HomePageAuthorizedUser(driver);
    }
}
