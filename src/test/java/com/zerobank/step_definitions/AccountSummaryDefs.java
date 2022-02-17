package com.zerobank.step_definitions;

import com.zerobank.pages.AccountSummaryPage;
import io.cucumber.java.en.Given;
import org.junit.Assert;

import java.util.List;

public class AccountSummaryDefs {

    @Given("verify that {string} page has following account types")
    public void verify_that_page_has_following_account_types(String string, List<String> expectedAccountTypesList) {
        List<String> actualAccountTypesList = new AccountSummaryPage().getAccountTypeList();
        Assert.assertEquals(expectedAccountTypesList, actualAccountTypesList);
    }

    @Given("verify that {string} type has following columns")
    public void verify_that_type_has_following_columns(String accountType, List<String> expectedColumns) {
        List<String> actualColumns = new AccountSummaryPage().getColumnAsList(accountType);
        Assert.assertEquals(expectedColumns,actualColumns);
    }
}
