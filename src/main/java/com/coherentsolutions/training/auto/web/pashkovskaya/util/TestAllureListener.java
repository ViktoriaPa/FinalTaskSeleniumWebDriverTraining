package com.coherentsolutions.training.auto.web.pashkovskaya.util;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Date;

public class TestAllureListener implements ITestListener {
    private static String getTestMethodName (ITestResult iTestResult){
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog (String message){
        return message;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
        Object testClass = iTestResult.getInstance();
        WebDriver driver = PageDriver.getDriver();
        if(driver instanceof WebDriver){
            System.out.println("Screenshot captured for test case: " + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }
        Date d = new Date();
        String[] dateTokens = d.toString().split(" ");
        saveTextLog(dateTokens[1] + ", " + dateTokens[2] + " " + dateTokens[5] + "; " + dateTokens[3] + " " + getTestMethodName(iTestResult) + " failed and screenshot taken");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
