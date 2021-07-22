package de.amazon.stepDefinitions;

import de.amazon.pages.HomePage;
import de.amazon.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePageStepDefs {

    @Given("The user select the language as {string}")
    public void the_user_select_the_language_as_(String language) {
        new HomePage().changeLanguage(language);
    }

    @Given("The user is on the homepage")
    public void the_user_is_on_the_homepage() {
        new HomePage().homePageAssertion();
    }


    @Then("All links on the HomePage must be valid")
    public void allLinksOnTheHomePageMustBeValid() {
         new HomePage().BrokenLinks();
    }

    @When("The user click on the SignOut button")
    public void theUserClickOnTheSignOutButton() {
        new HomePage().logout();
    }

    @Then("The user should be able to logout successfully")
    public void theUserShouldBeAbleToLogoutSuccessfully() {
        new HomePage().logoutAssertion();
        String str="Mini Smoke Test für Amazon.de ist hier beendet! Danke für Ihre Aufmerksamkeit!";
        WebElement element =Driver.get().findElement(By.xpath("//div/h1"));
        new HomePage().HTMLManipulation(str, element, 10);
    }


}
