@all
Feature: Verifying broken links on the Amazon homepage

  Background:
    Given The user is on the homepage
    And The user select the language as "English - EN"
    And The user login with valid credentials

  @brokenLinks
  Scenario: Verifying if all links on the HomePage valid or not
    Then All links on the HomePage must be valid

  @logout
  Scenario: Verifying that user logs out successfully
    When The user click on the SignOut button
    Then The user should be able to logout successfully