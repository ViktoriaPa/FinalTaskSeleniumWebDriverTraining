package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageAuthorizedUser extends BasePage{
    @FindBy(xpath = "//div[@class = 'panel header'] //button[@class = 'action switch']")
    public WebElement loggedInUserLabel;
    @FindBy(linkText = "My Account")
    public WebElement myAccountPage;
    @FindBy(linkText = "Women")
    public WebElement womenLink;

    public HomePageAuthorizedUser(WebDriver driver) {
        super(driver);
    }

    public MyAccountPage openMyAccountPage() throws InterruptedException {
        Thread.sleep(2500);
        loggedInUserLabel.click();
        myAccountPage.click();

        return new MyAccountPage(driver);
    }

    public WomenClothesPage openWomenClothesPage() {
        womenLink.click();

        return new WomenClothesPage(driver);
    }
}
