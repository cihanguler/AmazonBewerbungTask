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
      | bewerbungtask@gmail.com   | Bt.20212021 | cihanguler        |
      | computacenter49@gmail.com | Cc.20212021 | computacentergmbh |
      | taskweb.de@gmail.com      | Tw.20212021 | taskwebamazon     |

  @login @all @invalidUser
  Scenario Outline: As a user verify login with invalid "<mail>" and "<password>" credentials
    When The user try to login with "<mail>" and "<password>"
    Then The user should not be able to login and a warning message is displayed
    Examples:
      | mail                       | password            |
      | bewerbungtask@gmail.com    | invalidPassword.123 |
      | invalidUserEmail@gmail.com | Bt.20212021         |
      | invalidUserEmail@gmail.com | invalidPassword.123 |
      |                            | Bt.20212021         |
      | bewerbungtask@gmail.com    |                     |
      |                            |                     |