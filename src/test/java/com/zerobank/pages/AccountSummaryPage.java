package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class AccountSummaryPage extends BasePage{

    @FindBy(xpath = "//a[contains(.,'Savings')][1]")
    public WebElement savingsLink;

    @FindBy(xpath = "//a[contains(.,'Brokerage')]")
    public WebElement brokerageLink;

    @FindBy(xpath = "//a[contains(.,'Checking')]")
    public WebElement checkingLink;

    @FindBy(xpath = "//a[contains(.,'Credit Card')]")
    public WebElement creditCardLink;

    @FindBy(xpath = "//a[contains(.,'Loan')]")
    public WebElement loanLink;

    @FindBy(className = "board-header")
    public List<WebElement> accountTypeList;

    @FindBy(xpath = "(//h2[contains(.,'Credit Accounts')]/..//thead)[3]//th")
    public List<WebElement> creditAccountsColumnList;

    @FindBy(xpath = "(//h2[@class='board-header']/..//thead)[1]//th")
    public List<WebElement> cashLabelColumnList;

    @FindBy(xpath = "(//h2[@class='board-header']/..//thead)[2]//th")
    public List<WebElement> investmentLabelColumnList;

    @FindBy(xpath = "(//h2[@class='board-header']/..//thead)[3]//th")
    public List<WebElement> creditColumnList;

    @FindBy(xpath = "(//h2[@class='board-header']/..//thead)[4]//th")
    public List<WebElement> loanColumnList;

    public void navigateToAccountViaLink(String account){

        switch (account.toLowerCase().replaceAll(" ", "")){
            case "savings":
                savingsLink.click();
                break;
            case "brokerage":
                brokerageLink.click();
                break;
            case "checking":
                checkingLink.click();
                break;
            case "creditcard":
                creditCardLink.click();
                break;
            case "loan":
                loanLink.click();
                break;
            default:
                System.out.println(account + " not available");
                break;

        }
    }

    public List<String> getAccountTypeList(){
        List<String> actualAccountTypes = new ArrayList<>();
        for (WebElement each : accountTypeList){
            actualAccountTypes.add(each.getText());
        }
        System.out.println("accountTypesTable = " + actualAccountTypes);
        return actualAccountTypes;
    }

    public List<String> getColumnAsList (String accountType){
        switch (accountType.toLowerCase()){
            case "cash accounts":
                return getColumnList(cashLabelColumnList);
            case "investment accounts":
                return getColumnList(investmentLabelColumnList);
            case "credit accounts":
                return getColumnList(creditColumnList);
            case "loan accounts":
                return getColumnList(loanColumnList);
            default:
                System.out.println("No such account type available");
                return null;
        }
    }

    public List<String> getColumnList(List<WebElement> list){
        List<String> columns = new ArrayList<>();

        for(WebElement each : list){
            columns.add(each.getText());
        }
        return columns;
    }


}
