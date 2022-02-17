package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "#signin_button")
    public WebElement signInButtonToOnboard;

    @FindBy(css = "#user_login")
    public WebElement usernameInput;

    @FindBy(css = "#user_password")
    public WebElement passwordInput;

    @FindBy(css = "[value='Sign in']")
    public WebElement signInButtonToLogin;

    @FindBy(css = "#account_activity_link")
    public WebElement checkAccountActivity;

    @FindBy(xpath = "//div[@class='alert alert-error'][contains(.,'are wrong')]")
    public WebElement invalidCredentialsAlert;

    public void login(String username, String password){
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signInButtonToLogin.click();
        //Driver.get().get("http://zero.webappsecurity.com/bank/account-summary.html");
    }


}
