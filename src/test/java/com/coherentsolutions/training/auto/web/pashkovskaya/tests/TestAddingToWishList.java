package com.coherentsolutions.training.auto.web.pashkovskaya.tests;

import com.coherentsolutions.training.auto.web.pashkovskaya.base.BaseTest;
import com.coherentsolutions.training.auto.web.pashkovskaya.pages.*;
import com.coherentsolutions.training.auto.web.pashkovskaya.util.PageDriver;
import com.coherentsolutions.training.auto.web.pashkovskaya.util.TestAllureListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.coherentsolutions.training.auto.web.pashkovskaya.util.MainConstants.*;
import static org.testng.Assert.assertTrue;
@Listeners({ TestAllureListener.class })
public class TestAddingToWishList extends BaseTest {
    public static final By SUCCESS_MESSAGE = By.xpath("//div[@class='page messages']");
    @Test(groups = {"WishList"}, description = "Adding existing product to the Wish List")
    public void testAddingToWishList() {
        WebDriver driver = PageDriver.getDriver();
        driver.get(LINK_TO_MAIN_PAGE);

        HomePageUnauthorizedUser homePageUnauthorizedUser = new HomePageUnauthorizedUser(driver);
        AuthorizationPage authorizationPage = homePageUnauthorizedUser.openAuthorizationPage();
        HomePageAuthorizedUser homePageAuthorizedUser = authorizationPage.openHomePageAuthorizedUser(EMAIL, PASSWORD);
        WomenClothesPage womenClothesPage = homePageAuthorizedUser.openWomenClothesPage();
        ItemPage itemPage = womenClothesPage.openItemPage();
        itemPage.openWishListPage();

        WebElement successMessage = driver.findElement(SUCCESS_MESSAGE);
        assertTrue(successMessage.isDisplayed(), "Failed to add product to wish list");
    }
}
