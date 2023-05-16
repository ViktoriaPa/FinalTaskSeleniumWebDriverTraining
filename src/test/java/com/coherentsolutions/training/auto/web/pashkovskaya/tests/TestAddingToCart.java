package com.coherentsolutions.training.auto.web.pashkovskaya.tests;

import com.coherentsolutions.training.auto.web.pashkovskaya.base.BaseTest;
import com.coherentsolutions.training.auto.web.pashkovskaya.pages.*;
import com.coherentsolutions.training.auto.web.pashkovskaya.util.PageDriver;
import com.coherentsolutions.training.auto.web.pashkovskaya.util.TestAllureListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.coherentsolutions.training.auto.web.pashkovskaya.util.MainConstants.*;
import static org.testng.Assert.assertEquals;
@Listeners({ TestAllureListener.class })
public class TestAddingToCart extends BaseTest {
    @Test(groups = {"Cart"}, description = "Adding existing product to the Cart")
    public void testAddingToCart() throws InterruptedException {
        WebDriver driver = PageDriver.getDriver();
        driver.get(LINK_TO_MAIN_PAGE);

        HomePageUnauthorizedUser homePageUnauthorizedUser = new HomePageUnauthorizedUser(driver);
        AuthorizationPage authorizationPage = homePageUnauthorizedUser.openAuthorizationPage();
        HomePageAuthorizedUser homePageAuthorizedUser = authorizationPage.openHomePageAuthorizedUser(EMAIL, PASSWORD);
        WomenClothesPage womenClothesPage = homePageAuthorizedUser.openWomenClothesPage();
        Float totalPriceAddedToChartProducts = womenClothesPage.addThreeProductsToCart();
        CartPage cartPage = womenClothesPage.openCartPage();

        assertEquals(totalPriceAddedToChartProducts, cartPage.getTotalPriceFromCart(), "Prices mismatch");
    }
}
