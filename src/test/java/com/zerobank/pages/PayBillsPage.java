package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PayBillsPage extends BasePage{

    @FindBy(xpath = "//a[contains(.,'Add New Payee')]")
    public WebElement addNewPayeeTab;

    @FindBy(id = "np_new_payee_name")
    public WebElement payeeNameInput;

    @FindBy(id = "np_new_payee_address")
    public WebElement payeeAddressInput;

    @FindBy(id = "np_new_payee_account")
    public WebElement payeeAccountType;

    @FindBy(id = "np_new_payee_details")
    public WebElement payeeDetails;

    @FindBy(id = "alert_container>#alert_content")
    public WebElement payeeCreatedMessage;

    @FindBy(id = "add_new_payee")
    public WebElement addNewPayeeButton;

    @FindBy(xpath = "//a[contains(.,'Purchase Foreign Currency')]")
    public WebElement purchaseForeignCurrencyTab;

    @FindBy(id = "pc_currency")
    public WebElement currencyDropdown;

    @FindBy(id = "pc_calculate_costs")
    public WebElement calculateCostBtn;

    @FindBy(id = "pc_amount")
    public WebElement foreignCurrencyAmountInput;

    @FindBy(id = "pc_inDollars_true")
    public WebElement dollarChkBx;

    @FindBy(css = "#sp_payee")
    public WebElement payeeBankDropdown;

    @FindBy(css = "#sp_account")
    public WebElement accountTypeDropdown;

    @FindBy(css = "#sp_amount")
    public WebElement savedPayeeAmountInput;

    @FindBy(css = "#sp_date")
    public WebElement savedPayeeDateInput;

    @FindBy(css = "#sp_description")
    public WebElement savedPayeeDescription;

    @FindBy(css = "#pay_saved_payees")
    public WebElement payeeSavedPayBtn;

    @FindBy(xpath = "//*[@id='alert_content'][contains(.,'successfully submitted.')]")
    public WebElement successMsg;

    public boolean allCurrenciesAvailable(List<String> expectedCurrencyOptions){
        List<String> expectedOptionsWithoutWhiteSpaces = new ArrayList<>();
        for (String each : expectedCurrencyOptions){
            expectedOptionsWithoutWhiteSpaces.add(each.trim().replaceAll(" ", ""));
        }

        Select currencyOptions = new Select(new PayBillsPage().currencyDropdown);
        List<String> actualCurrencyOptionsWithoutWhiteSpaces = new ArrayList<>();

        for (WebElement eachOption : currencyOptions.getOptions()){
            actualCurrencyOptionsWithoutWhiteSpaces.add(eachOption.getText().trim().replaceAll(" ", ""));
        }

        if(actualCurrencyOptionsWithoutWhiteSpaces.containsAll(expectedOptionsWithoutWhiteSpaces)){
            return true;
        }
        return false;
    }

    public void selectCurrency(String currency){
        Select selectCurrency = new Select(currencyDropdown);
        selectCurrency.selectByVisibleText(currency);
    }

}
