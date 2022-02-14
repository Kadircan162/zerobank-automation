package com.zerobank.step_definitions;

import com.zerobank.pages.PayBillsPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.Map;

public class AddNewPayeeDefs {

    @Given("Navigate to {string} tab")
    public void navigate_to_tab(String tabName) {
        new PayBillsPage().navigateToPayBillsPage();
        new PayBillsPage().addNewPayeeTab.click();
    }

    @Given("creates new Payee using following information")
    public void creates_new_Payee_using_following_information(Map<String, String> dataTable) {
        PayBillsPage payBillsPage = new PayBillsPage();
        payBillsPage.payeeNameInput.sendKeys(dataTable.get("Payee Name"));
        payBillsPage.payeeAddressInput.sendKeys(dataTable.get("Payee Address"));
        payBillsPage.payeeAccountType.sendKeys(dataTable.get("Account"));
        payBillsPage.payeeDetails.sendKeys(dataTable.get("Payee details"));
        payBillsPage.addNewPayeeButton.click();
    }

    @Then("message {string} should be displayed")
    public void message_should_be_displayed(String expectedMessage) {
        String actualMessage = new PayBillsPage().payeeCreatedMessage.getText();
        Assert.assertEquals(expectedMessage,actualMessage);
    }

}
