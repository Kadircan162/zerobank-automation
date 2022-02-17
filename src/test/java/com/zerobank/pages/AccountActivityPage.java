package com.zerobank.pages;


import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.en_lol.WEN;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class AccountActivityPage extends BasePage {

    @FindBy(css = "#aa_accountId") //select
    public WebElement accountDropdown;

    @FindBy(xpath = "//*[@id='tabs']//a[contains(.,'Find Transactions')]")
    public WebElement findTransactions;

    @FindBy(xpath = "//*[@id='tabs']//a[contains(.,'Show Transactions')]")
    public WebElement showTransactions;

    @FindBy(css = "#aa_fromDate")
    public WebElement datesFrom;

    @FindBy(css = "#aa_toDate")
    public WebElement datesTo;

    @FindBy(xpath = "//*[@id='ui-tabs-2']/h2")
    public WebElement findTransactionsTitle;

    @FindBy(xpath = "//*[@id='find_transactions_form']//button")
    public WebElement findButton;

    @FindBy(css = "#aa_description")
    public WebElement descriptionInputAtFindTransaction;

    @FindBy(xpath = "//*[@id='filtered_transactions_for_account']//tbody//td[1]")
    public static List<WebElement> dateList;

    @FindBy(xpath = "//*[@id='filtered_transactions_for_account']//tbody//td[2]")
    public List<WebElement> descriptionList;

    @FindBy(xpath = "//*[@id='filtered_transactions_for_account']//tbody//td[3]")
    public List<WebElement> depositList;

    @FindBy(xpath = "//*[@id='filtered_transactions_for_account']//tbody//td[4]")
    public List<WebElement> withdrawalList;

    @FindBy(css = "#aa_type")
    public WebElement typeDropdown;

    @FindBy(xpath = "//*[@id='filtered_transactions_for_account']//th")
    public List<WebElement> transactionsColumnList;


    public List<Date> getActualDatesAtDateFormat() throws ParseException {
        List<String> actualDates = new ArrayList<>();
        for (WebElement each : dateList) {
            //String s = each.getText();
            //dates.add(s.substring(0, 4).replace(s.substring(0, 2), "") + s.substring(4));//12-09-01
            actualDates.add(each.getText());
        }

        System.out.println("actual dates as string = " + actualDates);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<Date> dateListAtDateFormat = new ArrayList<>();

        for (String each : actualDates) {
            Date utilDate = dateFormat.parse(each);
            dateListAtDateFormat.add(utilDate);
        }
        System.out.println("dateListAtDateFormat = " + dateListAtDateFormat);
        return dateListAtDateFormat;
    }

    public boolean dateContains(String date) throws ParseException {
        //String newDate = date.substring(0, 4).replace(date.substring(0, 2), "") + date.substring(4);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date givenDate = dateFormat.parse(date);

        List<Date> actualDates = getActualDatesAtDateFormat();

        for(Date eachDate : actualDates){
            if(eachDate.compareTo(givenDate) == 0){
                System.out.println("Given date exists in the actual date list");
                return true;
            }
        }
        return false;
    }

    public boolean areDatesSorted() throws ParseException { //2012-06-09, 2012-06-02, 2012-06-01

        List<Date> dateList = new ArrayList<>(getActualDatesAtDateFormat());

        for (int i = 0; i < dateList.size() - 1; i++) {
            if (dateList.get(i).compareTo(dateList.get(i + 1)) < 0) {
                System.out.println("Dates are not sorted as most recent");
                return false;
            }
        }

        return true;
    }

    public boolean areDatesBetween(String dateFrom, String dateTo) throws ParseException {
//        String dateStartFrom = dateFrom.substring(0, 4).replace(dateFrom.substring(0, 2), "") + dateFrom.substring(4);
//        String dateEndTo = dateTo.substring(0, 4).replace(dateTo.substring(0, 2), "") + dateTo.substring(4);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date givenDateFrom = dateFormat.parse(dateFrom); //2012-06-01
        Date givenDateTo = dateFormat.parse(dateTo); //2012-06-09

        if (areDatesSorted()) {

            List<Date> actualDatelist = new ArrayList<>(getActualDatesAtDateFormat());
            System.out.println("actualDatelist = " + actualDatelist);

            if (actualDatelist.get(0).compareTo(givenDateTo) > 0 || actualDatelist.get(actualDatelist.size()-1).compareTo(givenDateFrom) < 0) {
                System.out.println("The actual dates list has dates out of the given dates range");
                return false;
            }

        }
        return true;
    }

    /* dates comparing logic

    if(d1.compareTo(d2) > 0) {
        System.out.println("Date 1 occurs after Date 2");
    } else if(d1.compareTo(d2) < 0) {
        System.out.println("Date 1 occurs before Date 2");
    } else if(d1.compareTo(d2) == 0) {
        System.out.println("Both dates are equal");
    }

     */



    public List<String> getDescriptionList(){
        List<String> descriptions = new ArrayList<>();
        for (WebElement each : descriptionList){
            descriptions.add(each.getText());
        }
        return descriptions;
    }

    public boolean descriptionListOnlyContains(String givenInput){

        List<String> list = getDescriptionList();
        for (String each : list){

            String firstWord = each.split(" ")[0];

            System.out.println("firstWord = " + firstWord);
            if(!firstWord.equalsIgnoreCase(givenInput)){
                System.out.println("Description list contains other descriptions");
                return false;
            }
        }
        return true;
    }

    public boolean containsAtLeastOneResult(String descriptionTab){

        if(descriptionTab.equalsIgnoreCase("Deposit")){
            for(WebElement each : depositList){
                System.out.println("each.getText()=" + each.getText());
                if(!each.getText().isEmpty()){
                    return true;
                }
            }
        }else if(descriptionTab.equalsIgnoreCase("Withdrawal")){
            for(WebElement each : withdrawalList){
                System.out.println("each.getText()=" + each.getText());
                if(!each.getText().isEmpty()){
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> getSubTableColumnList(){
        List<String> columns = new ArrayList<>();

        for(WebElement each : transactionsColumnList){
            columns.add(each.getText());
            System.out.println("each.getText() = " + each.getText());
        }
        return columns;
    }


}
