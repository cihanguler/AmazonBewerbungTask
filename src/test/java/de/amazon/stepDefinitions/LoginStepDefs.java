package de.amazon.stepDefinitions;

import de.amazon.pages.HomePage;
import de.amazon.pages.LoginPage;
import de.amazon.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefs {

    @When("The user logs in with valid credentials")
    public void the_user_logs_in_with_valid_credentials() {
        LoginPage loginPage =new LoginPage();
        String username = ConfigurationReader.get("username");
        String password = ConfigurationReader.get("password");
        loginPage.login(username,password);
    }

    @Then("The user should be able to login")
    public void the_user_should_be_able_to_login() {
        new HomePage().PageTitleAssertion("Your Amazon.de");
    }

    @When("The user enter valid credentials")
    public void the_user_enter_valid_credentials() {
        String username = ConfigurationReader.get("username");
        String password = ConfigurationReader.get("password");
        new LoginPage().login(username, password);
    }

    @When("The user try to login with {string} and {string}")
    public void the_user_try_to_login_with_and(String string, String string2) {
        LoginPage loginPage =new LoginPage();
        loginPage.login(string,string2);

    }

    @Then("The user should not be able to login and get a warning")
    public void the_user_should_not_be_able_to_login_and_get_a_warning() {
        LoginPage loginPage =new LoginPage();
        loginPage.InvalidLoginTryAssertion();
    }

    @Then("The user should be able to login successfully")
    public void the_user_should_be_able_to_login_successfully() {
        LoginPage loginPage =new LoginPage();
        loginPage.loginAssertion();
    }

    @Then("The username should match the expected {string}")
    public void the_username_should_match_the_expected(String string1) {
        LoginPage loginPage =new LoginPage();
        loginPage.userNameAssertion(string1);

    }



}
