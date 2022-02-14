package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BasePage {

    public BasePage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(xpath = "//*[text()='Account Summary']")
    public WebElement accountSummary;

    @FindBy(xpath = "//*[text()='Account Activity']")
    public WebElement accountActivity;

    @FindBy(xpath = "//*[text()='Transfer Funds']")
    public WebElement transferFunds;

    @FindBy(xpath = "//*[text()='Pay Bills']")
    public WebElement payBills;

    @FindBy(xpath = "//*[text()='My Money Map']")
    public WebElement myMoneyMap;

    @FindBy(xpath = "//*[text()='Online Statements']")
    public WebElement onlineStatements;

    @FindBy(xpath = "//ul[@class='nav nav-tabs']//a")
    public List<WebElement> pageList;

    public void navigateToPayBillsPage(){
        payBills.click();
    }
    public void navigateToAccountActivityPage(){
        accountActivity.click();
    }
    public void navigateToAccountSummaryPage(){
        accountSummary.click();
    }

    public Map<String,WebElement> getPageElement(){
        Map<String,WebElement> pagesTable = new HashMap<>();
        for (WebElement eachElement : pageList){
            String pageName = eachElement.getText();
            pagesTable.put(pageName, eachElement);
        }

        return pagesTable;
    }


}
