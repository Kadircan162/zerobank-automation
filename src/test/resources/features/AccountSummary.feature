Feature: Functionalities at Account Summary page works properly

  Background:
    Given the user "any account" is on the login page
    And the user enters the credentials "username" and "password" to login


  @setupAndTeardown
  Scenario: Account summary page should have the title "Zero – Account Summary"
    When the user navigates to "Account Summary" page
    Then verify that the page "Account Summary" title is "Zero - Account Summary"


  @setupAndTeardown
  Scenario: Account summary page should have correct account types
    Given verify that "Account Summary" page has following account types
      | Cash Accounts       |
      | Investment Accounts |
      | Credit Accounts     |
      | Loan Accounts       |

  @setupAndTeardown
  Scenario: Credit Accounts table must have correct columns
    Given verify that "Credit Accounts" type has following columns
      | Account     |
      | Credit Card |
      | Balance     |



