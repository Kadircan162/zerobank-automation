package com.zerobank.step_definitions;

import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.Map;

public class LoginStepDefs {

    @Given("the user {string} is on the login page")
    public void the_user_is_on_the_login_page(String userType) {
        Driver.get().get(ConfigurationReader.get("url"));
        new LoginPage().signInButtonToOnboard.click();
        System.out.println(userType + " got on the login page");
    }

    @Given("the user enters the credentials {string} and {string} to login")
    public void the_user_enters_the_credentials_and_login(String username, String password) {
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        new LoginPage().login(username, password);
    }

    @Given("Account summary page should be displayed to verify if the user logged in")
    public void account_summary_page_should_be_displayed_to_verify_if_the_user_logged_in() {
        String actualPageTitle = Driver.get().getTitle();
        String expectedPageTitle = "Zero - Account Summary";
        Assert.assertEquals(expectedPageTitle, actualPageTitle);
    }

    @Then("verify only authorized users should be logged in")
    public void verify_only_authorized_users_should_be_logged_in(Map<String,String> credentialsTable) {
        String validUsername = ConfigurationReader.get("username");
        String validPassword = ConfigurationReader.get("password");
        String givenUsername = credentialsTable.get("userName");
        System.out.println("givenUsername = " + givenUsername);
        String givenPassword = credentialsTable.get("passWord");
        System.out.println("givenPassword = " + givenPassword);
        String expectedPageTitleForValidUser = "Zero - Account Summary";
        System.out.println("expectedPageTitleForValidUser = " + expectedPageTitleForValidUser);
        String actualPageTitle = Driver.get().getTitle();
        System.out.println("actualPageTitle = " + actualPageTitle);

        try {
            if(givenUsername.equals(validUsername) && givenPassword.equals(validPassword)){//null pointer exception
                Assert.assertEquals(expectedPageTitleForValidUser, actualPageTitle); //Alert msg not just enough to verify if the user actually logged in or not
                System.out.println(credentialsTable.get("userType") + " account logged in");
            }else{
                Assert.assertNotEquals(expectedPageTitleForValidUser, actualPageTitle);//checking page title to verify if the user actually not logged in
                System.out.println(credentialsTable.get("userType") + " account could not login");
            }
        }catch (Exception e){ //just for null username and password when throws NullPointerException
            Assert.assertNotEquals(expectedPageTitleForValidUser, actualPageTitle);//null (space) username or password
            System.out.println(credentialsTable.get("userType") + " account could not login");
        }


    }

    @Given("the user tries to invalid credentials {string} {string} and login")
    public void the_user_tries_to_invalid_credentials_and_and_login() {

    }

    @Given("verify the error message is displayed when <\"userType\"> tries to login with invalid credentials {string} {string}")
    public void verify_the_error_message_is_displayed_when_user_tries_to_login_with_invalid_credentials(String userName, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.usernameInput.sendKeys(userName);
        loginPage.passwordInput.sendKeys(password);
        loginPage.signInButtonToLogin.click();
        Assert.assertTrue(loginPage.invalidCredentialsAlert.isDisplayed());
    }





    //Assert.assertFalse(new LoginPage().invalidCredentialsAlert.isDisplayed());//double check via error msg to verify if the user actually not logged in



}
