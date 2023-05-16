package com.coherentsolutions.training.auto.web.pashkovskaya.util;

import org.openqa.selenium.WebDriver;

public class PageDriver {
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return webDriver.get();
    }

    public static void setDriver(WebDriver driver){
        webDriver.set(driver);
    }
}
