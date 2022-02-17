Feature: Find transactions in Account Activity

  Background:
    Given the user "any user" is on the login page
    And the user enters the credentials "username" and "password" to login
    And the user accesses the "Find Transactions" tab at Account Activity

  @wip @setupAndTeardown
  Scenario: Search date range
    When the user enters date range from "2012-09-01" to "2012-09-06"
    And clicks search
    And the results should be sorted by most recent date
    Then results table should only show transactions dates between "2012-09-01" to "2012-09-06"
    When the user enters date range from "2012-09-02" to "2012-09-06"
    And clicks search
    And the results should be sorted by most recent date
    Then results table should only show transactions dates between "2012-09-01" to "2012-09-06"
    And the results table should only not contain transactions dated "2012-09-01"

  @setupAndTeardown
  Scenario: Search description
    When the user enters description "ONLINE"
    And clicks search
    Then results table should only show descriptions containing "ONLINE"
    When the user enters description "OFFICE"
    And clicks search
    Then results table should only show descriptions containing "OFFICE"
    But results table should not show descriptions containing "ONLINE"

  @setupAndTeardown
  Scenario: Search description case insensitive
    When the user enters description "ONLINE"
    And clicks search
    Then results table should only show descriptions containing "ONLINE"
    When the user enters description "online"
    And clicks search
    Then results table should only show descriptions containing "ONLINE"

  @setupAndTeardown
  Scenario: Type
    And clicks search
    Then result table should show at least one result under "Deposit"
    Then result table should show at least one result under "Withdrawal"
    When user selects type "Deposit"
    Then result table should show at least one result under "Deposit"
    But results table should show no result under "Withdrawal"
    When user selects type "Withdrawal"
    Then result table should show at least one result under "Withdrawal"
    But results table should show no result under "Deposit"

     ################ DDT ################

  @setupAndTeardown
  Scenario Outline: Search date range at Account Activity page
    Given the user enters date range from "<initialDateFrom>" to "<initialDateTo>"
    And clicks search
    Then results table should only show transactions dates between "<initialDateFrom>" to "<initialDateTo>"
    And the results should be sorted by most recent date
    When the user enters date range from "<secondaryDateFrom>" to "<secondaryDateTo>"
    And clicks search
    Then results table should only show transactions dates between "<secondaryDateFrom>" to "<secondaryDateTo>"
    And the results table should only not contain transactions dated "<initialDateFrom>"

    Examples:
      | initialDateFrom | initialDateTo | secondaryDateFrom | secondaryDateTo |
      | 2012-09-01      | 2012-09-06    | 2012-09-02        | 2012-09-06      |


  @setupAndTeardown
  Scenario Outline: Search description
    Given the user enters description "<firstDescription>"
    And clicks search
    Then results table should only show descriptions containing "<firstDescription>"
    When the user enters description "<secondDescription>"
    And clicks search
    Then results table should only show descriptions containing "<secondDescription>"
    But results table should not show descriptions containing "<firstDescription>"

    Examples:
      | firstDescription | secondDescription |
      | ONLINE           | OFFICE            |






  @setupAndTeardown
  Scenario: Search description case insensitive
    Given the user enters description "ONLINE"
    And clicks search
    Then results table should only show descriptions containing "ONLINE"
    When the user enters description "online"
    And clicks search
    Then results table should only show descriptions containing "ONLINE"

  @setupAndTeardown
  Scenario: Type
    Given clicks search
    Then result table should show at least one result under "Deposit"
    Then result table should show at least one result under "Withdrawal"
    When user selects type "Deposit"
    Then result table should show at least one result under "Deposit"
    But results table should show no result under "Withdrawal"
    When user selects type "Withdrawal"
    Then result table should show at least one result under "Withdrawal"
    But results table should show no result under "Deposit"





