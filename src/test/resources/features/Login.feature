Feature: Login functionality works as expected


   @setupAndTeardown
  Scenario Outline: only authorized user <userType> should be able to login (positive and negative testing)
    Given the user "<userType>" is on the login page
    And the user enters the credentials "<userName>" and "<passWord>" to login
    Then verify only authorized users should be logged in
      | userType | <userType> |
      | userName | <userName> |
      | passWord | <passWord> |

    Examples:
      | userType           | userName  | passWord  |
      | Savings            | username  |           |
      | Brokerage          |           | password  |
      | Checking           | username  | cvgfdsfd  |
      | Credit Card        | user_name | password  |
      | Loan               | username  | password  |
      | Some other account | username  | pass_word |
      | Some other account | user_name | password  |


  @setupAndTeardown
  Scenario: When user logs in with valid credentials, Account summary page should be displayed
    Given the user "any account" is on the login page
    Given the user enters the credentials "username" and "password" to login
    And Account summary page should be displayed to verify if the user logged in

  @setupAndTeardown
  Scenario Outline: When user tries to login with invalid information, error message "Login and/or password are wrong" should be displayed
    Given the user "<userType>" is on the login page
    And verify the error message is displayed when <"userType"> tries to login with invalid credentials "<userName>" "<passWord>"

    Examples:
      | userType    | userName  | passWord  |
      | Savings     | username  |           |
      | Brokerage   |           | password  |
      | Checking    | username  | xyz123322 |
      | Credit Card | user_name | password  |
      | Brokerage   |           |           |







