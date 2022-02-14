package com.zerobank.step_definitions;

import com.zerobank.pages.PayBillsPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;


public class PuchaseForeignCurrencyDefs {

    WebDriver driver = Driver.get();

    @Given("the user accesses the Purchase foreign currency cash tab")
    public void the_user_accesses_the_Purchase_foreign_currency_cash_tab() {
        new PayBillsPage().navigateToPayBillsPage();
        new PayBillsPage().purchaseForeignCurrencyTab.click();
    }

    @Then("following currencies should be available")
    public void following_currencies_should_be_available(List<String> expectedCurrencyOptions) {
        Assert.assertTrue(new PayBillsPage().allCurrenciesAvailable(expectedCurrencyOptions));
    }

    @When("the user tries to calculate cost without selecting a currency")
    public void the_user_tries_to_calculate_cost_without_selecting_a_currency() {
        PayBillsPage payBillsPage = new PayBillsPage();
        payBillsPage.foreignCurrencyAmountInput.sendKeys("51");
        payBillsPage.calculateCostBtn.click();
    }

    @Then("error message {string} should be displayed")
    public void error_message_should_be_displayed(String expectedAlertMsg) {
        Alert alert = driver.switchTo().alert();
        String actualMsg = alert.getText();
        System.out.println("actualMsg = " + actualMsg);
        alert.accept();
        Assert.assertEquals(expectedAlertMsg, actualMsg);
    }

    @Given("the user select the currency {string}")
    public void the_user_select_the_currency(String givenCurrency) {
        new PayBillsPage().selectCurrency(givenCurrency);
    }

    @When("the user tries to calculate cost without entering a value")
    public void the_user_tries_to_calculate_cost_without_entering_a_value() {
        new PayBillsPage().calculateCostBtn.click();
    }

    @Given("fill out all input field and select choices using following valid info")
    public void fill_out_all_input_field_and_select_choices_using_following_valid_info(Map<String, String> infoTable) {
        System.out.println("infoTable = " + infoTable);
        BrowserUtils.waitFor(1);
        PayBillsPage payBillsPage = new PayBillsPage();
        new Select(payBillsPage.payeeBankDropdown).selectByVisibleText(infoTable.get("payeeBank"));
        new Select(payBillsPage.accountTypeDropdown).selectByVisibleText(infoTable.get("accountType"));
        payBillsPage.savedPayeeAmountInput.sendKeys(infoTable.get("amount"));
        payBillsPage.savedPayeeDateInput.sendKeys(infoTable.get("date"));
        payBillsPage.savedPayeeDescription.sendKeys(infoTable.get("description"));

    }

    @When("click on pay button")
    public void click_on_pay_button() {
        BrowserUtils.waitFor(1);
        new PayBillsPage().payeeSavedPayBtn.click();

    }

    @Then("Verify that {string} message is shown")
    public void verify_that_message_is_shown(String expectedMsg) {
        BrowserUtils.waitFor(1);
        Assert.assertEquals(expectedMsg, new PayBillsPage().successMsg.getText());
    }

    @When("user try to make a payment without entering {string} or {string}")
    public void user_try_to_make_a_payment_without_entering_or(String amount, String date) {
        BrowserUtils.waitFor(1);
        PayBillsPage payBillsPage = new PayBillsPage();
        payBillsPage.savedPayeeAmountInput.sendKeys(amount);
        payBillsPage.savedPayeeDateInput.sendKeys(date);
    }

    @Then("verify that {string} alert message pups up")
    public void verify_that_alert_message_pups_up(String expectedAlertMsg) {
        BrowserUtils.waitFor(2);
        PayBillsPage payBillsPage = new PayBillsPage();
        String actualAlertMsgForAmount = payBillsPage.savedPayeeAmountInput.getAttribute("validationMessage");
        String actualAlertMsgForDate = payBillsPage.savedPayeeDateInput.getAttribute("validationMessage");
        System.out.println("actualAlertMsg = " + actualAlertMsgForAmount);
        System.out.println("actualAlertMsg = " + actualAlertMsgForDate);
        try{
            if(payBillsPage.savedPayeeAmountInput.getText().isEmpty()){
                System.out.println("Throws exception");
            }
        }catch (Exception e){
            Assert.assertEquals(expectedAlertMsg, actualAlertMsgForAmount);
            Assert.assertEquals(expectedAlertMsg, actualAlertMsgForDate);
        }

    }


}
