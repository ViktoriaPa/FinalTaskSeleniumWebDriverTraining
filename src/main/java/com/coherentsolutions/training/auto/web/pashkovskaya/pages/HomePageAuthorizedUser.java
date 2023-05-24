package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HomePageAuthorizedUser extends BasePage{
    @FindBy(xpath = "//div[@class='panel header'] //button[@data-action='customer-menu-toggle']")
    private WebElement loggedInUserMenu;
    @FindBy(linkText = "My Account")
    private WebElement myAccountPage;
    @FindBy(xpath = "//div[@class='panel header'] //span[contains(text(), 'Viktoria Pa')]")
    private WebElement loggedInUserLabel;
    @FindBy(xpath = "//*[@id='maincontent']/div[3]/div/div[2]/div[3]/div/div/ol/li[1]")
    private WebElement productItem;
    @FindBy(xpath = "//a[@class='action showcart']")
    private WebElement cartIcon;
    @FindBy(xpath = "//a[@class='action viewcart']")
    private WebElement cartLink;
    @FindBy(xpath = "//a[@class='action showcart'] /span[2]")
    private WebElement counterOfAddedToCartItems;
    @FindBy(xpath = "//a[@data-action='add-to-wishlist'][1]")
    private WebElement addToWishListIcon;

    public HomePageAuthorizedUser(WebDriver driver) {
        super(driver);
    }
    public void clickLoggedInButton(){
        loggedInUserMenu.click();
    }
    public String getLoggedInUserText(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return loggedInUserLabel.getText();
    }
    public void clickMyAccountLink(){
        myAccountPage.click();
    }
    public void selectItem(){
        productItem.click();
    }
    public void clickCartIcon(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(counterOfAddedToCartItems));

        cartIcon.click();
    }
    public void clickCartLink(){
        cartLink.click();
    }
    public float addThreeProductsToCart() {
        float totalPrice=0;
        for (int i = 1; i < 4; i++){
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//ol[@class='product-items widget-product-grid'] //li[" + i + "]"));

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ol[@class='product-items widget-product-grid'] //li[" + i + "] //span[@class='price']")));

            String price = driver.findElement(By.xpath("//ol[@class='product-items widget-product-grid'] //li[" + i + "] //span[@class='price']")).getText();
            float productPrice = Float.parseFloat(price.replaceAll("[$]", ""));
            WebElement size = driver.findElement(By.xpath("//ol[@class='product-items widget-product-grid'] //li[" + i + "] //div[@aria-label='Size']/div[1]"));
            size.click();
            WebElement color = driver.findElement(By.xpath("//ol[@class='product-items widget-product-grid'] //li[" + i + "] //div[@aria-label='Color']/div[1]"));
            color.click();
            WebElement addToCartButton = driver.findElement(By.xpath("//ol[@class='product-items widget-product-grid'] //li[" + i + "] //button[@title='Add to Cart']"));
            addToCartButton.click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            totalPrice = totalPrice + productPrice;
        }

        return(totalPrice);
    }
    public void addToWishList(){
        addToWishListIcon.click();
    }
    public WishListPage openWishListPage(){
        Actions action = new Actions(driver);
        action.moveToElement(productItem).perform();

        addToWishList();

        return new WishListPage(driver);
    }
    public ItemPage openItemPage() {
        selectItem();

        return new ItemPage(driver);
    }
    public CartPage openCartPage() {
        clickCartIcon();
        clickCartLink();

        return new CartPage(driver);
    }

    public MyAccountPage openMyAccountPage() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        clickLoggedInButton();
        clickMyAccountLink();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return new MyAccountPage(driver);
    }
}
