package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishListPage extends BasePage{
    @FindBy(xpath = "//div[@class='page messages']")
    private WebElement addingToWishListSuccessfullyMessage;
    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public boolean addingToWishListSuccessfullyMessageIsDisplayed() {

        try {
            return addingToWishListSuccessfullyMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
