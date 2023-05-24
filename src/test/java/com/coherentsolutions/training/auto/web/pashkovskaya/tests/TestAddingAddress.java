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
public class TestAddingAddress extends BaseTest {
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

        assertEquals(addNewAddressPage.getAddressSavedSuccessfullyMessage(), SUCCESS_MESSAGE_TEXT, "Failed to save address");
    }
}
