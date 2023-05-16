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
import static org.testng.Assert.assertEquals;
@Listeners({ TestAllureListener.class })
public class TestAddingAddress extends BaseTest {
    public static final By SUCCESS_MESSAGE = By.xpath("//div[@class = 'message-success success message']");
    public static final String SUCCESS_MESSAGE_TEXT = "You saved the address.";
    @Test(groups = {"Address"}, description = "Adding valid Address")
    public void testAddingAddress() throws InterruptedException {
        WebDriver driver = PageDriver.getDriver();
        driver.get(LINK_TO_MAIN_PAGE);

        HomePageUnauthorizedUser homePageUnauthorizedUser = new HomePageUnauthorizedUser(driver);
        AuthorizationPage authorizationPage = homePageUnauthorizedUser.openAuthorizationPage();
        HomePageAuthorizedUser homePageAuthorizedUser = authorizationPage.openHomePageAuthorizedUser(EMAIL, PASSWORD);
        MyAccountPage myAccountPage = homePageAuthorizedUser.openMyAccountPage();
        AddressBookPage addressBookPage = myAccountPage.openAddressBookPage();
        AddNewAddressPage addNewAddressPage = addressBookPage.openAddNewAddressPage();
        addNewAddressPage.saveNewAddress(ADDRESS, CITY, ZIP, PHONE_NUMBER);

        WebElement successMessage = driver.findElement(SUCCESS_MESSAGE);
        assertEquals(successMessage.getText(), SUCCESS_MESSAGE_TEXT, "Failed to save address");
    }
}
