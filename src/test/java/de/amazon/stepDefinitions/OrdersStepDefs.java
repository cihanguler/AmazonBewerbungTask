package de.amazon.stepDefinitions;

import de.amazon.pages.OrderPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrdersStepDefs {


    @When("The user click on the orders button")
    public void theUserClickOnTheOrdersButton() {
        new OrderPage().navigateToOrder();
    }

    @Then("The user should be able to cancel the order")
    public void theUserShouldBeAbleToCancelTheOrder() {
        new OrderPage().orderCancellation();
    }


    @And("The user should see message {string} or {string}")
    public void theUserShouldSeeMessageOr(String arg0, String arg1) {
        arg0= "This order has been cancelled.";
        arg1= "Done! I sent your message";
        new OrderPage().orderCancellationVerification(arg1, arg0);
    }
}
