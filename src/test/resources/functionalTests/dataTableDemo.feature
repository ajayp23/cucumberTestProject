Feature: Send data using data table and populate it on console
  @DataTableDemo
  Scenario: Populate data from data table
    Given I send below data in data table
    |Desk ID|Desk Name |PVF     |Agency ID|
    |CA_JAP |Japan Cash|GCT APAC|FED      |
    And I populate the data in console