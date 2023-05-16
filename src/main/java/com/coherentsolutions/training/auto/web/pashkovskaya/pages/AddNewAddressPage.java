package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddNewAddressPage extends BasePage{
    @FindBy(id = "street_1")
    public WebElement addressTextField1;
    @FindBy(id = "city")
    public WebElement cityTextField;
    @FindBy(id = "country")
    public WebElement countryDropdown;
    @FindBy(xpath = "//*[@id='country']/option[178]")
    public WebElement country;
    @FindBy(id = "region_id")
    public WebElement stateDropdown;
    @FindBy(xpath = "//*[@id='region_id']/option[2]")
    public WebElement state;
    @FindBy(id = "zip")
    public WebElement zipTextField;
    @FindBy(id = "telephone")
    public WebElement phoneNumberTextField;
    @FindBy(xpath = "//button[@class = 'action save primary']")
    public WebElement saveAddressButton;

    public AddNewAddressPage(WebDriver driver) {
        super(driver);
    }

    public AddressBookPage saveNewAddress(String address, String city, String zip, String phoneNumber) {
        addressTextField1.sendKeys(address);
        cityTextField.sendKeys(city);
        countryDropdown.click();
        country.click();
        stateDropdown.click();
        state.click();
        zipTextField.sendKeys(zip);
        phoneNumberTextField.sendKeys(phoneNumber);
        saveAddressButton.click();

        return new AddressBookPage(driver);
    }
}
