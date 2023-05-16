package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressBookPage extends BasePage{
    @FindBy(xpath = "//button[@class='action primary add']")
    public WebElement addNewAddressButton;

    public AddressBookPage(WebDriver driver) {
        super(driver);
    }

    public AddNewAddressPage openAddNewAddressPage() throws InterruptedException {
        Thread.sleep(10000);
        addNewAddressButton.click();

        return new AddNewAddressPage(driver);
    }
}
