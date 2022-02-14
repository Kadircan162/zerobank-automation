Feature: Purchase Foreign Currency

  Background:
    Given the user "any user" is on the login page
    Given the user enters the credentials "username" and "password" to login

  @setupAndTeardown
  Scenario: Available currencies
    Given the user accesses the Purchase foreign currency cash tab
    Then following currencies should be available
      | Australia (dollar)    |
      | Canada (dollar)       |
      | Switzerland (franc)   |
      | China (yuan)          |
      | Denmark (krone)       |
      | Hong Kong (dollar)    |
      | Sweden (krona)        |
      | Eurozone (euro)       |
      | Great Britain (pound) |
      | Japan (yen)           |
      | Mexico (peso)         |
      | Norway (krone)        |
      | New Zealand (dollar)  |
      | Singapore (dollar)    |
      | Thailand (baht)       |

  @setupAndTeardown
  Scenario: Error message for not selecting currency
    Given the user accesses the Purchase foreign currency cash tab
    When the user tries to calculate cost without selecting a currency
    Then error message "Please, ensure that you have filled all the required fields with valid values." should be displayed


  @setupAndTeardown
  Scenario: Error message for not entering value
    Given the user accesses the Purchase foreign currency cash tab
    And the user select the currency "Japan (yen)"
    When the user tries to calculate cost without entering a value
    Then error message "Please, ensure that you have filled all the required fields with valid values." should be displayed