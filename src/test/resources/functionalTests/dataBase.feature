Feature: Fetch data from database

  @Database
  Scenario: Fetch data from database
    Given I make connection to the database
    When I execute query to fetch details
    Then I print the details