package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
    @FindBy(linkText = "Address Book")
    private WebElement addressBookPage;
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }
    public void clickAddressBookLink(){
        addressBookPage.click();
    }
    public AddressBookPage openAddressBookPage() {
        clickAddressBookLink();

        return new AddressBookPage(driver);
    }
}
