package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePageAuthorizedUser extends BasePage{
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
    @FindBy(xpath = "//ul[@id='ui-id-2'] //span[text()='Women']")
    private WebElement womenCategory;
    @FindBy(xpath = "//a[@id='ui-id-9'] //span[text()='Tops']")
    private WebElement topsSubCategoryOfWomenCategory;
    @FindBy(xpath = "//a[@id='ui-id-10'] //span[text()='Bottoms']")
    private WebElement bottomsSubCategoryOfWomenCategory;
    @FindBy(xpath = "//div[@class='columns']//div[contains(@class,'products-grid')]//li")
    private List<WebElement> listOfProducts;

    private By nextButton = By.xpath("//div[@class='column main'] //div[4] //a[@title='Next']");
    private By firstAvailableProductSize = By.xpath(".//div[@attribute-code='size']//div//div[1]");
    private By firstAvailableProductColor = By.xpath(".//div[@attribute-code='color']/div/div[1]");
    private By productName = By.xpath(".//a[@class='product-item-link']");
    private By productPrice = By.xpath(".//span[@data-price-type='finalPrice']");
    private By addToCartButton = By.xpath(".//button[@title='Add to Cart']");

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
    public void navigateToWomenCategory(){
        Actions action = new Actions(driver);
        action.moveToElement(womenCategory).perform();
    }
    public void navigateToTopsSubCategoryOfWomenCategory(){
        topsSubCategoryOfWomenCategory.click();
    }
    public void navigateToBottomsSubCategoryOfWomenCategory(){
        bottomsSubCategoryOfWomenCategory.click();
    }
    public List<WebElement> getListOfProducts(){
        return listOfProducts;
    }
    public String getProductName(WebElement productElement){
        return productElement.findElement(productName).getText();
    }
    public void selectFirstAvailableProductSize(WebElement productElement){
        productElement.findElement(firstAvailableProductSize).click();
    }
    public void selectFirstAvailableProductColor(WebElement productElement){
        productElement.findElement(firstAvailableProductColor).click();
    }
    public BigDecimal getProductPrice(WebElement productElement){
        String price = productElement.findElement(productPrice).getText();

        BigDecimal productPrice = new BigDecimal(price.replaceAll("[$]", ""));
        return productPrice;
    }
    public void addProductToCart(WebElement productElement){
        productElement.findElement(addToCartButton).click();
    }
    public void clickCartIcon(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(counterOfAddedToCartItems));

        cartIcon.click();
    }
    public void clickCartLink(){
        cartLink.click();
    }
    public BigDecimal addProductToCartFromWomenCategory(String product, String subCategory) {
        navigateToWomenCategory();

        if(subCategory.equals("Tops")){
            navigateToTopsSubCategoryOfWomenCategory();
        } else if (subCategory.equals("Bottoms")) {
            navigateToBottomsSubCategoryOfWomenCategory();
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        BigDecimal totalPrice = BigDecimal.ZERO;

        while (true) {
            try {
                List<WebElement> productList = getListOfProducts();
                for (WebElement productElement : productList) {
                    if (getProductName(productElement).equals(product)) {
                        selectFirstAvailableProductSize(productElement);
                        selectFirstAvailableProductColor(productElement);

                        totalPrice = totalPrice.add(getProductPrice(productElement));

                        addProductToCart(productElement);
                    }
                }
                WebElement nextButtonForTable = wait.until(ExpectedConditions.presenceOfElementLocated(nextButton));
                nextButtonForTable.click();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                break;
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
