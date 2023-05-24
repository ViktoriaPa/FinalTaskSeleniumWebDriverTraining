package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class AuthorizationPage extends BasePage{
    @FindBy(id = "email")
    private WebElement emailTextField;
    @FindBy(id = "pass")
    private WebElement passwordTextField;
    @FindBy(id = "send2")
    private WebElement signInButton;

    public AuthorizationPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email){
        emailTextField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordTextField.sendKeys(password);
    }

    public void clickSignInButton(){
        signInButton.click();
    }

    public HomePageAuthorizedUser openHomePageAuthorizedUser(String email, String password) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        enterEmail(email);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        enterPassword(password);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        clickSignInButton();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        return new HomePageAuthorizedUser(driver);
    }
}
