package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends BasePage{
    @FindBy(xpath = "//div[@class='swatch-attribute size'] /div[@class='swatch-attribute-options clearfix'] /div[1]")
    private WebElement sizeLabel;
    @FindBy(xpath = "//div[@class='swatch-option color'][1]")
    private WebElement colorLabel;
    @FindBy(id = "product-addtocart-button")
    private WebElement addToCartLink;
    @FindBy(xpath = "//div[@class='product-social-links'] //a[@data-action='add-to-wishlist']")
    private WebElement addToWishListLink;

    public ItemPage(WebDriver driver) {
        super(driver);
    }
    public void selectFirstAvailableSize(){
        sizeLabel.click();
    }
    public void selectFirstAvailableColor(){
        colorLabel.click();
    }
    public void clickAddToCartLink(){
        addToCartLink.click();
    }
    public void clickWishListLink(){
        addToWishListLink.click();
    }
    public void addProductToCart() {
        selectFirstAvailableSize();
        selectFirstAvailableColor();
        clickAddToCartLink();
    }

    public WishListPage openWishListPage() {
        clickWishListLink();

        return new WishListPage(driver);
    }
}
