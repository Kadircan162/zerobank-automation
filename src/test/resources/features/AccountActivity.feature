Feature: Navigating to specific accounts in Accounts Activity

  Background:
    Given the user "any account" is on the login page
    And the user enters the credentials "username" and "password" to login


  @setupAndTeardown
  Scenario Outline: Activity Accounts redirect
    When the user clicks on "<accountLink>" link on the Account Summary page
    Then the Account Activity page should be displayed
    And Account dropdown should have "<accountLink>" selected

    Examples:
      | accountLink |
      | Savings     |
      | Brokerage   |
      | Checking    |
      | Credit Card |
      | Loan        |

  @setupAndTeardown
  Scenario: Account activity page should have the title Zero – Account activity
    When the user navigates to "Account Activity" page
    Then verify that the page "Account Activity" title is "Zero - Account Activity"

  @setupAndTeardown
  Scenario: In the Account drop down default option should be Savings
    When the user navigates to "Account Activity" page
    Then Account dropdown should have "Savings" selected

   @setupAndTeardown
  Scenario: Transactions table should have correct column names
    Given the user navigates to "Account Activity" page
    And the user accesses the "Find Transactions" tab at Account Activity
    And clicks search
    Then verify that "Transactions" subTable has following columns
      | Date        |
      | Description |
      | Deposit     |
      | Withdrawal  |
