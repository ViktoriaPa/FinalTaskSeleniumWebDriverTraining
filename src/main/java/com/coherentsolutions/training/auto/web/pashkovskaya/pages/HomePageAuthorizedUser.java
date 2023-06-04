package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePageAuthorizedUser extends BasePage{
    private static final String CATEGORY_XPATH_TEMPLATE = "//span[text()='%s']";
    private static final String SUB_CATEGORY_XPATH_TEMPLATE = "//ul[@id='ui-id-2']//a[span[contains(text(),'%s')]]/following-sibling::ul//a[span[contains(text(),'%s')]]";
    private static final String PRODUCT_PRICE_XPATH_TEMPLATE = "/ancestor::div[contains(@class, 'product-item-details')]//span[@data-price-type='finalPrice']";
    private static final String ADD_TO_CART_BUTTON_XPATH_TEMPLATE = "/ancestor::div[contains(@class, 'product-item-details')]//button[@title='Add to Cart']";
    private static final String PRODUCT_XPATH_TEMPLATE = "//a[contains(text(), '%s')]";
    private static final String PRODUCT_FIRST_AVAILABLE_COLOR_XPATH_TEMPLATE = "/ancestor::div[contains(@class, 'product-item-details')]//div[@attribute-code='color']/div/div[1]";
    private static final String PRODUCT_FIRST_AVAILABLE_SIZE_XPATH_TEMPLATE = "/ancestor::div[contains(@class, 'product-item-details')]//div[@attribute-code='size']//div//div[1]";
    private By nextButton = By.xpath("//div[@class='column main'] //div[4] //a[@title='Next']");

    @FindBy(xpath = "//div[@class='panel header'] //button[@data-action='customer-menu-toggle']")
    private WebElement loggedInUserMenu;
    @FindBy(linkText = "My Account")
    private WebElement myAccountPage;
    @FindBy(xpath = "//div[@class='panel header'] //span[contains(text(), 'Viktoria Pa')]")
    private WebElement loggedInUserLabel;
    @FindBy(xpath = "//div[@class='block-content'] //li[@class='product-item'][1]")
    private WebElement firstProductItemFromHotSellersSection;
    @FindBy(xpath = "//a[@class='action showcart']")
    private WebElement cartIcon;
    @FindBy(xpath = "//a[@class='action viewcart']")
    private WebElement cartLink;
    @FindBy(xpath = "//a[@class='action showcart'] /span[2]")
    private WebElement counterOfAddedToCartItems;
    @FindBy(xpath = "//a[@data-action='add-to-wishlist'][1]")
    private WebElement addToWishListIcon;
    @FindBy(xpath = "//div[@class='columns']//div[contains(@class,'products-grid')]//li")
    private List<WebElement> listOfProducts;

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
        firstProductItemFromHotSellersSection.click();
    }
    public void navigateToSubCategory(String categoryName, String subcategoryName){
        String subCategoryXPath = String.format(SUB_CATEGORY_XPATH_TEMPLATE, categoryName, subcategoryName);
        WebElement subCategoryElement = driver.findElement(By.xpath(subCategoryXPath));
        subCategoryElement.click();
    }
    public void navigateToCategory(String categoryName){
        String categoryXPath = String.format(CATEGORY_XPATH_TEMPLATE, categoryName);
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath(categoryXPath))).perform();
    }
    public List<WebElement> getListOfProducts(){
        return listOfProducts;
    }
    public void selectFirstAvailableProductSize(String productName){
        String productXPath = String.format(PRODUCT_XPATH_TEMPLATE, productName);
        WebElement productSize = driver.findElement(By.xpath(productXPath + PRODUCT_FIRST_AVAILABLE_SIZE_XPATH_TEMPLATE));
        productSize.click();
    }
    public void selectFirstAvailableProductColor(String productName){
        String productXPath = String.format(PRODUCT_XPATH_TEMPLATE, productName);
        WebElement productColor = driver.findElement(By.xpath(productXPath + PRODUCT_FIRST_AVAILABLE_COLOR_XPATH_TEMPLATE));
        productColor.click();
    }
    public BigDecimal getProductPrice(String productName){
        String productXPath = String.format(PRODUCT_XPATH_TEMPLATE, productName);
        WebElement productSize = driver.findElement(By.xpath(productXPath + PRODUCT_PRICE_XPATH_TEMPLATE));

        String price = productSize.getText();
        System.out.println(price);

        BigDecimal productPrice = new BigDecimal(price.replaceAll("[$]", ""));
        return productPrice;
    }
    public void addProductToCart(String productName){
        String productXPath = String.format(PRODUCT_XPATH_TEMPLATE, productName);
        WebElement addProductToCartButton = driver.findElement(By.xpath(productXPath + ADD_TO_CART_BUTTON_XPATH_TEMPLATE));
        addProductToCartButton.click();
    }
    public void clickCartIcon(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(counterOfAddedToCartItems));

        cartIcon.click();
    }
    public void clickCartLink(){
        cartLink.click();
    }
    public BigDecimal addProductToCartFromWomenCategory(String productName, String categoryName, String subcategoryName) {
        navigateToCategory(categoryName);
        navigateToSubCategory(categoryName, subcategoryName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        BigDecimal totalPrice = BigDecimal.ZERO;

        while (true) {
            try {
                if (driver.findElement(By.xpath(String.format(PRODUCT_XPATH_TEMPLATE, productName))).isDisplayed()){
                    selectFirstAvailableProductSize(productName);
                    selectFirstAvailableProductColor(productName);

                    totalPrice = totalPrice.add(getProductPrice(productName));

                    addProductToCart(productName);
                }
            } catch (NoSuchElementException f) {
                try {
                    WebElement nextButtonForTable = wait.until(ExpectedConditions.presenceOfElementLocated(nextButton));
                    nextButtonForTable.click();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                } catch (TimeoutException q) {
                    break;
                }
            }
        }

        return totalPrice;
    }
    public void addToWishList(){
        addToWishListIcon.click();
    }
    public WishListPage openWishListPage(){
        Actions action = new Actions(driver);
        action.moveToElement(firstProductItemFromHotSellersSection).perform();

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
        clickLoggedInButton();
        clickMyAccountLink();

        return new MyAccountPage(driver);
    }
}