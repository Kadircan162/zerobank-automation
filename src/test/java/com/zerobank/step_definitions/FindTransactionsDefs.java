package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.text.ParseException;

public class FindTransactionsDefs {

    @Given("the user accesses the {string} tab at Account Activity")
    public void the_user_accesses_the_subModule_tab(String expectedSubModuleTitle) {
        AccountActivityPage accountActivityPage = new AccountActivityPage();
        accountActivityPage.accountActivity.click();
        if(expectedSubModuleTitle.equalsIgnoreCase("Find Transactions")){
            BrowserUtils.waitFor(1);
            accountActivityPage.findTransactions.click();
            String actualSubmoduleTitle = accountActivityPage.findTransactionsTitle.getText();
            Assert.assertEquals(expectedSubModuleTitle, actualSubmoduleTitle);
        }else if(expectedSubModuleTitle.equalsIgnoreCase("Show Transactions")){
            accountActivityPage.showTransactions.click();
            String actualSubmoduleTitle = accountActivityPage.findTransactionsTitle.getText();
            Assert.assertEquals(expectedSubModuleTitle, actualSubmoduleTitle);
        }

    }

    @When("the user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String dateFrom, String dateTo) {
        AccountActivityPage accountActivityPage = new AccountActivityPage();
        accountActivityPage.datesFrom.clear();
        accountActivityPage.datesFrom.sendKeys(dateFrom);
        accountActivityPage.datesTo.clear();
        accountActivityPage.datesTo.sendKeys(dateTo);
        /*
        String actualDateFrom = accountActivityPage.datesFrom.getAttribute("value");
        String actualDateTo = accountActivityPage.datesTo.getAttribute("value");
        System.out.println("dateFrom = " + dateFrom);
        System.out.println("dateTo = " + dateTo);
        Assert.assertEquals(dateFrom, actualDateFrom);
        Assert.assertEquals(dateTo,actualDateTo);
         */

    }

    @When("clicks search")
    public void clicks_search() {
        new AccountActivityPage().findButton.click();
        BrowserUtils.waitFor(2);
    }

    @Then("results table should only show transactions dates between {string} to {string}")
    public void results_table_should_only_show_transactions_dates_between_to(String rangeFrom, String rangeTo) throws ParseException {
        boolean areDatesBetweenToAndFrom = new AccountActivityPage().areDatesBetween(rangeFrom,rangeTo);
        Assert.assertTrue(areDatesBetweenToAndFrom);
    }


    @Then("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date() throws ParseException {
        boolean isSorted = new AccountActivityPage().areDatesSorted();
        Assert.assertTrue(isSorted);
    }

    @Then("the results table should only not contain transactions dated {string}")
    public void the_results_table_should_only_not_contain_transactions_dated(String givenDate) throws ParseException {
        boolean doesContain = new AccountActivityPage().dateContains(givenDate);
        Assert.assertFalse(doesContain);
    }

    @When("the user enters description {string}")
    public void the_user_enters_description(String input) {
        new AccountActivityPage().descriptionInputAtFindTransaction.clear();
        new AccountActivityPage().descriptionInputAtFindTransaction.sendKeys(input);

    }

    @Then("results table should only show descriptions containing {string}")
    public void results_table_should_only_show_descriptions_containing(String input) {
        BrowserUtils.waitFor(1);
        boolean onlyContains = new AccountActivityPage().descriptionListOnlyContains(input);
        Assert.assertTrue(onlyContains);
    }

    @Then("results table should not show descriptions containing {string}")
    public void results_table_should_not_show_descriptions_containing(String input) {
        boolean doesContain = new AccountActivityPage().descriptionListOnlyContains(input);
        Assert.assertFalse(doesContain);
    }

    @Then("result table should show at least one result under {string}")
    public void result_table_should_show_at_least_one_result_under(String descriptionTab) {
        AccountActivityPage accountActivityPage = new AccountActivityPage();
        accountActivityPage.findButton.click();
        BrowserUtils.waitFor(1);
        Assert.assertTrue(accountActivityPage.containsAtLeastOneResult(descriptionTab));
    }

    @When("user selects type {string}")
    public void user_selects_type(String type) {
        Select select = new Select(new AccountActivityPage().typeDropdown);
        select.selectByVisibleText(type);
    }

    @Then("results table should show no result under {string}")
    public void results_table_should_show_no_result_under(String descriptionTab) { //wip
        BrowserUtils.waitFor(1);
        Assert.assertFalse(new AccountActivityPage().containsAtLeastOneResult(descriptionTab));
    }

}
