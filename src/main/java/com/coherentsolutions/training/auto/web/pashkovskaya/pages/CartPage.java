package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{
    @FindBy(xpath = "//tr[@class='totals sub'] //span[@class='price']")
    public WebElement totalPrice;
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public float getTotalPriceFromCart() {
        String totalProductsPrice = totalPrice.getText();
        float productsPrice = Float.parseFloat(totalProductsPrice.replaceAll("[$]", ""));

        return productsPrice;
    }
}
