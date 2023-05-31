package com.coherentsolutions.training.auto.web.pashkovskaya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddNewAddressPage extends BasePage{
    @FindBy(id = "street_1")
    private WebElement addressTextField1;
    @FindBy(id = "city")
    private WebElement cityTextField;
    @FindBy(id = "country")
    private WebElement countryDropdown;
    @FindBy(xpath = "//*[@id='country']/option[178]")
    private WebElement country;
    @FindBy(id = "region_id")
    private WebElement stateDropdown;
    @FindBy(xpath = "//*[@id='region_id']/option[2]")
    private WebElement state;
    @FindBy(id = "zip")
    private WebElement zipTextField;
    @FindBy(id = "telephone")
    private WebElement phoneNumberTextField;
    @FindBy(xpath = "//button[@data-action='save-address']")
    private WebElement saveAddressButton;
    @FindBy(xpath = "//div[@data-ui-id='message-success']")
    private WebElement addressSavedSuccessfullyMessage;

    public AddNewAddressPage(WebDriver driver) {
        super(driver);
    }
    public String getAddressSavedSuccessfullyMessage(){
        return addressSavedSuccessfullyMessage.getText();
    }
    public void enterAddress(String address){
        addressTextField1.sendKeys(address);
    }
    public void enterCity(String city){
        cityTextField.sendKeys(city);
    }
    public void openCountryDropdown(){
        countryDropdown.click();
    }
    public void selectCountry(){
        country.click();
    }
    public void openStateDropdown(){
        stateDropdown.click();
    }
    public void selectState(){
        state.click();
    }
    public void enterZip(String zip){
        zipTextField.sendKeys(zip);
    }
    public void enterPhoneNumber(String phoneNumber){
        phoneNumberTextField.sendKeys(phoneNumber);
    }
    public void clickSaveAddressButton(){
        saveAddressButton.click();
    }
    public AddressBookPage saveNewAddress(String address, String city, String zip, String phoneNumber) {
        enterAddress(address);
        enterCity(city);
        openCountryDropdown();
        selectCountry();
        openStateDropdown();
        selectState();
        enterZip(zip);
        enterPhoneNumber(phoneNumber);
        clickSaveAddressButton();

        return new AddressBookPage(driver);
    }
}
