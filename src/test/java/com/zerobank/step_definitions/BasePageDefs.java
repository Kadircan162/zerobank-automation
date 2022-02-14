package com.zerobank.step_definitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import org.junit.Assert;

public class BasePageDefs {

    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String pageName) {
        new AccountSummaryPage().getPageElement().get(pageName).click();
    }

    @Given("verify that the page {string} title is {string}")
    public void verify_that_the_page_title_is(String pageName, String expectedPageTitle) {
        String actualPageTitle = Driver.get().getTitle();
        System.out.println("actualPageTitle = " + actualPageTitle);
        System.out.println("expectedPageTitle = " + expectedPageTitle);
        Assert.assertEquals(expectedPageTitle,actualPageTitle);
        System.out.println(pageName + " seen correctly");
    }
}
