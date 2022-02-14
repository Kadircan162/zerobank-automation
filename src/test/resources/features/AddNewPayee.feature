Feature: Add new payee under pay bills

  Background:
    Given the user "any account" is on the login page
    And the user enters the credentials "username" and "password" to login

  @setupAndTeardown
  Scenario: Add a new payee
    Given Navigate to "Add New Payee" tab
    And creates new Payee using following information
      | Payee Name    | The Law Offices of Hyde, Price & Scharks |
      | Payee Address | 100 Same st, Anytown, USA, 10001         |
      | Account       | Checking                                 |
      | Payee details | XYZ account                              |
    Then message "The new payee The Law Offices of Hyde, Price & Scharks was successfully created." should be displayed
