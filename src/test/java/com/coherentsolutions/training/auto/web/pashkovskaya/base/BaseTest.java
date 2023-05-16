package com.coherentsolutions.training.auto.web.pashkovskaya.base;

import com.coherentsolutions.training.auto.web.pashkovskaya.util.PageDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.coherentsolutions.training.auto.web.pashkovskaya.util.DriverInit;
import org.testng.annotations.Parameters;

import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;
    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(String browserName) throws IOException {
        DriverInit driverInit = DriverInit.getInstance();

//      wayOfGetDriver - it is a way you want to get driver: locally, grid, sauceLabs
        driver = driverInit.getDriver(browserName, "locally");

        PageDriver.setDriver(driver);
    }

    @AfterMethod (alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }
}
