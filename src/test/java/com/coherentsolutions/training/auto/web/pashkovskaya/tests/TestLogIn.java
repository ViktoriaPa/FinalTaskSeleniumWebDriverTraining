package com.coherentsolutions.training.auto.web.pashkovskaya.tests;

import com.coherentsolutions.training.auto.web.pashkovskaya.base.BaseTest;
import com.coherentsolutions.training.auto.web.pashkovskaya.pages.AuthorizationPage;
import com.coherentsolutions.training.auto.web.pashkovskaya.pages.HomePageUnauthorizedUser;
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
public class TestLogIn extends BaseTest {
    public static final By LOGGED_IN_USER_LABEL = By.xpath("//div[@class='panel wrapper'] //span[contains(text(), 'Viktoria Pa')]");
    public static final String LOGGED_IN_USER_LABEL_TEXT = "Welcome, Viktoria Pa!";
    @Test(groups = {"LogIn"}, description = "Login test with valid username and valid password")
    public void testLogInUser() throws InterruptedException {
        WebDriver driver = PageDriver.getDriver();
        driver.get(LINK_TO_MAIN_PAGE);

        HomePageUnauthorizedUser homePageUnauthorizedUser = new HomePageUnauthorizedUser(driver);
        AuthorizationPage authorizationPage = homePageUnauthorizedUser.openAuthorizationPage();
        authorizationPage.openHomePageAuthorizedUser(EMAIL, PASSWORD);

        Thread.sleep(10000);
        WebElement loggedInUserLabel = driver.findElement(LOGGED_IN_USER_LABEL);

        assertEquals(loggedInUserLabel.getText(), LOGGED_IN_USER_LABEL_TEXT, "User is not logged in");
    }
}
