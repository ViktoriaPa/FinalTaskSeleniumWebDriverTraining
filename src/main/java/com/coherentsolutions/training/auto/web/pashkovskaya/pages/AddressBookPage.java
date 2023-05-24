package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressBookPage extends BasePage{
    @FindBy(xpath = "//button[@role='add-address']")
    private WebElement addNewAddressButton;

    public AddressBookPage(WebDriver driver) {
        super(driver);
    }
    public void clickAddNewAddressButton(){
        addNewAddressButton.click();
    }
    public AddNewAddressPage openAddNewAddressPage() throws InterruptedException {
        clickAddNewAddressButton();

        return new AddNewAddressPage(driver);
    }
}
