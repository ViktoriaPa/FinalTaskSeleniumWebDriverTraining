package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WomenClothesPage extends BasePage{
    @FindBy(xpath = "//ol[@class='product-items widget-product-grid'][1]")
    public WebElement productItem;
    @FindBy(xpath = "//a[@class='action showcart']")
    public WebElement cartIcon;
    @FindBy(xpath = "//a[@class='action viewcart']")
    public WebElement cartLink;

    public WomenClothesPage(WebDriver driver) {
        super(driver);
    }

    public float addThreeProductsToCart() throws InterruptedException {
        float totalPrice=0;
        for (int i = 1; i < 4; i++){
            driver.findElement(By.xpath("//ol[@class='product-items widget-product-grid'] //li[" + i + "]"));
            String price = driver.findElement(By.xpath("//ol[@class='product-items widget-product-grid'] //li[" + i + "] //span[@class='price']")).getText();
            float productPrice = Float.parseFloat(price.replaceAll("[$]", ""));
            WebElement size = driver.findElement(By.xpath("//ol[@class='product-items widget-product-grid'] //li[" + i + "] //div[@aria-label='Size']/div[1]"));
            size.click();
            WebElement color = driver.findElement(By.xpath("//ol[@class='product-items widget-product-grid'] //li[" + i + "] //div[@aria-label='Color']/div[1]"));
            color.click();
            WebElement addToCartButton = driver.findElement(By.xpath("//ol[@class='product-items widget-product-grid'] //li[" + i + "] //button[@title='Add to Cart']"));
            addToCartButton.click();
            Thread.sleep(5000);

            totalPrice = totalPrice + productPrice;
        }

        return(totalPrice);
    }

    public ItemPage openItemPage() {
        productItem.click();

        return new ItemPage(driver);
    }

    public CartPage openCartPage() {
        cartIcon.click();
        cartLink.click();

        return new CartPage(driver);
    }
}
