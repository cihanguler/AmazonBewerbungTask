package de.amazon.stepDefinitions;

import de.amazon.pages.BasketPage;
import de.amazon.pages.HomePage;
import de.amazon.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class BasketStepDefs {

    @When("The user navigate to basket page")
    public void the_user_navigate_to_basket_page() {
        new HomePage().basketButton.click();
    }

    @Then("The basket calculates the result correctly")
    public void the_basket_calculates_the_result_correctly() {
        BasketPage basketPage = new BasketPage();
        double expectedValue = basketPage.calculateTotalPriceOfProducts();
        double actualValue = basketPage.getSubtotal();
        assertEquals(expectedValue, actualValue, 0D);
    }

    @Then("Amazon gets the user to the {string} page")
    public void amazon_gets_the_user_to_the_page(String pageNameOrder) {
        new BasketPage().checkoutDirectionAssertion(pageNameOrder);
    }

    @When("The user proceed to checkout")
    public void the_user_proceed_to_checkout() {
        new BasketPage().proceedToCheckout.click();
    }

    @And("The user check and deletes all products on the basket page")
    public void theUserCheckAndDeletesAllProductsOnTheBasketPage() {
        new BasketPage().deleteAllProducts();
    }

    @When("The User click on the DeliverToThisAddress button")
    public void theUserClickOnTheDeliverToThisAddressButton() {
        new BasketPage().DeliverToThisAdress ();
    }

    @And("The User click on the delivery options continue button")
    public void theUserClickOnTheDeliveryOptionsContinueButton() {
        new BasketPage().deliveryOptionsContinue();
    }

    @And("The User click on the buy now button")
    public void theUserClickOnTheBuyNowButton() {
        new BasketPage().BuyNow();
    }

    @Then("The message of tester is displayed")
    public void theMessageOfTesterIsDisplayed() {
        String arg0="Soll ich den Test hier beenden, damit ich nicht zahle? :)";
        WebElement element = Driver.get().findElement(By.xpath("//div/h4"));
        new HomePage().HTMLManipulation(arg0, element, 10);
        String arg1="Lass uns den Test fortsetzen...";
        new HomePage().HTMLManipulation(arg1, element, 5);

    }

    @Then("verify if the {string} is selected")
    public void verifyIfTheIsSelected(String arg0) {
        new BasketPage().UseGiftCardBalanceVerification(arg0);
    }

    @Then("The user should be able to see {string} message")
    public void theUserShouldBeAbleToSeeMessage(String arg0) {
        new BasketPage().orderVerification(arg0);

    }

    @Then("The user should be able to see on basket page {string} message")
    public void theUserShouldBeAbleToSeeOnBasketPageMessage(String arg0) {
        new BasketPage().basketEmptyVerification(arg0);

    }

}
