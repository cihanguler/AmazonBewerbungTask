package de.amazon.stepDefinitions;

import de.amazon.pages.HomePage;
import io.cucumber.java.en.When;

public class SearchStepDefs {

    @When("The user select the search department as {string}")
    public void the_user_select_the_search_department_as(String department) {
        new HomePage().selectSearchDepartment(department);
    }


}
