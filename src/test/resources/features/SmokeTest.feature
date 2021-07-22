@all
Feature: I want to verify login, product search, chart, checkout and logout processes

  Background:
    Given The user is on the homepage
    And The user select the language as "English - EN"

  @smoke @ExistingUser
  Scenario: As an existing Amazon user verify login, product search, basket, checkout and logout processes
    When The user login with valid credentials
    And The user navigate to basket page
    And The user check and deletes all products on the basket page
    When The user select the search department as "Grocery"
    And The user search the product "Schogetten Alpenvollmilch-Haselnuss"
    And The user sort the products by "Price: Low to High"
    Then The user add the "cheapest" "Schogetten Alpenvollmilch-Haselnuss" to the basket
    And The user navigate to basket page
    Then The basket calculates the result correctly
    When The user proceed to checkout
    Then Amazon gets the user to the "delivery address" page
    When The User click on the DeliverToThisAddress button
    And The User click on the delivery options continue button
    Then The message of tester is displayed
    And verify if the "Use gift card balance" is selected
    When The User click on the delivery options continue button
    And The User click on the buy now button
    Then The user should be able to see "Order placed, thanks!" message
    When The user click on the orders button
    Then The user should be able to cancel the order
    And The user should see message "done" or "cancelled"
    When The user click on the SignOut button
    Then The user should be able to logout successfully

  @smoke @UnregisteredUser
  Scenario: As an unregistered user verify product search, basket and checkout processes
    And The user navigate to basket page
    And The user check and deletes all products on the basket page
    When The user select the search department as "Electronics & Photo"
    And The user search the product "Tripod"
    And The user sort the products by "Avg. Customer Review"
    And The user add the "average customer review" "Tripod" to the basket
    And The user select the search department as "Health & Personal Care"
    And The user search the product "Aspirin"
    And The user sort the products by "Newest Arrivals"
    And The user add the "newest arrivals" "Aspirin" to the basket
    And The user select the search department as "Books"
    And The user search the product "Simon Sinek Start with Why English"
    And The user sort the products by "Price: High to Low"
    And The user add the "most expensive" "Start with Why" to the basket
    And The user navigate to basket page
    Then The basket calculates the result correctly
    When The user proceed to checkout
    Then Amazon gets the user to the "Amazon Sign In" page
