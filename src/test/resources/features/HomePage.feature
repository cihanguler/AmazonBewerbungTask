@homepage
Feature: HomePage

  Background:
    Given The user is on the homepage
    And The user select the language as "English - EN"
    And The user logs in with valid credentials

  @brokenLinks @all
  Scenario: Verifying if all links on the HomePage valid or not
    Then All links on the HomePage must be valid

  @logout @all
  Scenario: Verifying that user logs out successfully
    When The user click on the SignOut button
    Then The user should be able to logout successfully