package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends BasePage{
    @FindBy(xpath = "//div[@class='swatch-attribute size'] /div[@class='swatch-attribute-options clearfix'] /div[1]")
    public WebElement sizeLabel;
    @FindBy(xpath = "//div[@class='swatch-option color'][1]")
    public WebElement colorLabel;
    @FindBy(id = "product-addtocart-button")
    public WebElement addToCartLink;
    @FindBy(xpath = "//*[@id='maincontent']/div[2]/div/div[1]/div[5]/div/a[1]")
    public WebElement addToWishListLink;

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToCart() {
        sizeLabel.click();
        colorLabel.click();
        addToCartLink.click();
    }

    public WishListPage openWishListPage() {
        addToWishListLink.click();

        return new WishListPage(driver);
    }
}
