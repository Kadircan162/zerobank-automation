Feature: Functionalities at Pay Bills page works properly

  Background:
    Given the user "any user" is on the login page
    And the user enters the credentials "username" and "password" to login

  @wip @setupAndTeardown
  Scenario: Pay Bills page should have the title "Zero - Pay Bills"
    Given the user navigates to "Pay Bills" page
    And verify that the page "Pay Bills" title is "Zero - Pay Bills"

  @setupAndTeardown
  Scenario Outline: When user completes a successful payment, a success message gets displayed
    Given the user navigates to "Pay Bills" page
    And fill out all input field and select choices using following valid info
      | payeeBank   | <payeeBank>   |
      | accountType | <accountType> |
      | amount      | <amount>      |
      | date        | <date>        |
      | description | <description> |
    When click on pay button
    Then Verify that "The payment was successfully submitted." message is shown

    Examples:
      | payeeBank       | accountType | amount | date       | description       |
      | Bank of America | Loan        | 5000   | 2014-09-02 | Money money money |
      | Wells Fargo     | Checking    | 150    | 2017-12-05 | Hey yo!           |
      | Apple           | Brokerage   | 70000  | 2019-09-23 | Money money money |
      | Apple           | Brokerage   | 75400  | 2019-09-23 | Money money money |

   @setupAndTeardown
  Scenario Outline: When user tries to make a payment without entering the amount or date, an aler message pups up
    Given the user navigates to "Pay Bills" page
    And verify that the page "Pay Bills" title is "Zero - Pay Bills"
    When user try to make a payment without entering "<amountInput>" or "<dateInput>"
    And click on pay button
    Then verify that "Please fill out this field." alert message pups up
    Examples:
      | amountInput | dateInput  |
      |             | 2019-09-23 |
      | 75400       |            |








