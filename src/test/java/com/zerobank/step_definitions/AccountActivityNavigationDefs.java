package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AccountActivityNavigationDefs {

    @When("the user clicks on {string} link on the Account Summary page")
    public void the_user_clicks_on_link_on_the_Account_Summary_page(String accountLink) {
        new AccountSummaryPage().navigateToAccountViaLink(accountLink);
    }

    @Then("the Account Activity page should be displayed")
    public void the_Account_Activity_page_should_be_displayed() {
        String actualTitle = Driver.get().getTitle();
        String expectedTitle = "Zero - Account Activity";
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Then("Account dropdown should have {string} selected")
    public void account_drop_down_should_have_Savings_selected(String expectedDefaultOption) {
        BrowserUtils.waitFor(1);
        Select select = new Select(new AccountActivityPage().accountDropdown);
        String actualDefaultOption = select.getFirstSelectedOption().getText();
        System.out.println("actualDefaultOption = " + actualDefaultOption);
        Assert.assertEquals(expectedDefaultOption, actualDefaultOption);
    }

    @Then("verify that {string} subTable has following columns")
    public void verify_that_subTable_has_following_columns(String subTab, List<String> expectedSubTabColumns) {
        List<String> actualSubTabColumns = new AccountActivityPage().getSubTableColumnList();
        Assert.assertEquals(expectedSubTabColumns, actualSubTabColumns);
    }


}
