package de.amazon.stepDefinitions;

import de.amazon.pages.HomePage;
import de.amazon.pages.ProductPage;
import de.amazon.pages.SearchPage;
import de.amazon.utilities.BrowserUtils;
import io.cucumber.java.en.When;

public class ArtikelStepDefs {

    @When("The user search the product {string}")
    public void the_user_search_the_product(String item) {
        HomePage homepage = new HomePage();
        homepage.searchItem(item);
        new SearchPage().undoSearchItemTranslate();
    }

    @When("The user sort the products by {string}")
    public void the_user_sort_the_products_by(String sortOption) {
        SearchPage searchRP = new SearchPage();
        searchRP.sortItems(sortOption);
        new SearchPage().undoSearchItemTranslate();
    }


    @When("The user add the {string} {string} to the basket")
    public void the_user_add_the_to_the_basket(String sort, String item) {
        SearchPage searchRP = new SearchPage();
        // click with jsExecutor, because some times products does not be at the page view (window)
        // jsExecutor support us that we can click all elements in DOM, not page view (window)
        BrowserUtils.clickWithJS(searchRP.getCheapestElement(item));
        new ProductPage().addToBasket();
    }

}
