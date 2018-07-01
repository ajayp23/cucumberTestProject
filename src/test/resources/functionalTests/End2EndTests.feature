Feature: Automated End2End Test with Cucumber
  @Test
  Scenario: Customer places an order
    Given user is on Home Page
    When he search for 'dress'
    And choose to buy the first item
    And moves to checkout from mini cart
    And enter personal details on checkout page
    And select same delivery address
    And select payment method as 'check' payment
    And place the order