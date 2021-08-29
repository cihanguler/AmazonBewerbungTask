@ll
Feature: I want to verify if users can (not) login with (in)valid credentials

  Background:
    Given The user is on the homepage
    And The user select the language as "English - EN"

  @login @all @validUser
  Scenario Outline: As a user verify login with valid "<mail>" and "<password>" credentials
    When The user try to login with "<mail>" and "<password>"
    Then The user should be able to login successfully
    And The username should match the expected "<username>"
    Examples:
      | mail                      | password    | username          |


  @login @all @invalidUser
  Scenario Outline: As a user verify login with invalid "<mail>" and "<password>" credentials
    When The user try to login with "<mail>" and "<password>"
    Then The user should not be able to login and a warning message is displayed
    Examples:
      | mail                       | password            |
