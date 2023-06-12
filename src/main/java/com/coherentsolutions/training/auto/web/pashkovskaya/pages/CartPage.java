package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

public class CartPage extends BasePage{
    @FindBy(xpath = "//tr[@class='totals sub'] //span[@class='price']")
    private WebElement totalPrice;
    public CartPage(WebDriver driver) {
        super(driver);
    }
    public String getTotalPrice(){
        return totalPrice.getText();
    }
    public BigDecimal getTotalPriceFromCart() {
        String totalProductsPrice = getTotalPrice();
        BigDecimal productsPrice = new BigDecimal(totalProductsPrice.replaceAll("[$]", ""));

        return productsPrice;
    }
}
