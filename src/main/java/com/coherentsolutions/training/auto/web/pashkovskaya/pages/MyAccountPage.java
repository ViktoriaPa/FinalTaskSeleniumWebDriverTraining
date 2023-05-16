package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
    @FindBy(linkText = "Address Book")
    public WebElement addressBookPage;
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public AddressBookPage openAddressBookPage() {
        addressBookPage.click();

        return new AddressBookPage(driver);
    }
}
