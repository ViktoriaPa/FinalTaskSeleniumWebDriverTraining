package com.coherentsolutions.training.auto.web.pashkovskaya.tests;

import com.coherentsolutions.training.auto.web.pashkovskaya.base.BaseTest;
import com.coherentsolutions.training.auto.web.pashkovskaya.pages.AccountCreationPage;
import com.coherentsolutions.training.auto.web.pashkovskaya.pages.HomePageUnauthorizedUser;
import com.coherentsolutions.training.auto.web.pashkovskaya.util.PageDriver;
import com.coherentsolutions.training.auto.web.pashkovskaya.util.TestAllureListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.coherentsolutions.training.auto.web.pashkovskaya.util.MainConstants.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
@Listeners({ TestAllureListener.class })
public class TestAccountCreation extends BaseTest {
    public static final By PASSWORD_MISMATCH_ERROR = By.id("password-confirmation-error");
    public static final String PASSWORD_MISMATCH_MESSAGE = "Please enter the same value again.";
    public static final String WRONG_PASSWORD = "VikaVqwe1234";
    public static final By ACCOUNT_HAS_BEEN_ALREADY_CREATED_ERROR_MESSAGE = By.xpath("//div[@class='messages']");

    @Test(groups = {"AccountCreation"}, description = "Creation a new account. Entered passwords are different")
    public void testPasswordsAreDifferent() {
        WebDriver driver = PageDriver.getDriver();
        driver.get(LINK_TO_MAIN_PAGE);

        HomePageUnauthorizedUser homePageUnauthorizedUser = new HomePageUnauthorizedUser(driver);
        AccountCreationPage accountCreationPage = homePageUnauthorizedUser.openAccountCreationPage();
        accountCreationPage.openHomePageAuthorizedUser(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, WRONG_PASSWORD);

        assertEquals(driver.findElement(PASSWORD_MISMATCH_ERROR).getText(), PASSWORD_MISMATCH_MESSAGE, "Error message mismatch");
    }

    @Test(groups = {"AccountCreation"}, description = "Creation a new account. An account with the same credentials has already been created")
    public void testAccountHasAlreadyBeenCreated() {
        WebDriver driver = PageDriver.getDriver();
        driver.get(LINK_TO_MAIN_PAGE);

        HomePageUnauthorizedUser homePageUnauthorizedUser = new HomePageUnauthorizedUser(driver);
        AccountCreationPage accountCreationPage = homePageUnauthorizedUser.openAccountCreationPage();
        accountCreationPage.openHomePageAuthorizedUser(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PASSWORD);

        assertTrue(driver.findElement(ACCOUNT_HAS_BEEN_ALREADY_CREATED_ERROR_MESSAGE).isDisplayed(), "New account is created");
    }

    @Test(groups = {"AccountCreation"}, description = "Creation a new account")
    public void testAccountCreation() {
        WebDriver driver = PageDriver.getDriver();
        driver.get(LINK_TO_MAIN_PAGE);

        HomePageUnauthorizedUser homePageUnauthorizedUser = new HomePageUnauthorizedUser(driver);
        AccountCreationPage accountCreationPage = homePageUnauthorizedUser.openAccountCreationPage();
        accountCreationPage.openHomePageAuthorizedUser(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PASSWORD);

        assertEquals(driver.getTitle(), "My Account", "Account is not created");
    }
}
