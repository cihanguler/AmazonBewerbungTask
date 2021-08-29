package de.amazon.pages;

import de.amazon.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static de.amazon.utilities.BrowserUtils.waitForClickable;

public class ProductPage extends BasePage {

    /**
     * PageFactory design pattern, so the page WebElements are assigned automatically, when it opened.
     *
     * @Param Driver.get()
     */
    public ProductPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    /**
     * create logger to log infos, errors etc.
     */
    Logger logger = LoggerFactory.getLogger(ProductPage.class);

    /**
     * Find Page WebElements
     */
    /**
     * On some product pages, "add to basket" button names are different
     * if any of them is visible, assign it to WebElement
     */

    @FindBy(id = "add-to-cart-button")
    public List<WebElement> addToBasketMainButton1;

    @FindBy(id = "add-to-cart-button-ubb")
    public List<WebElement> addToBasketMainButton2;

    @FindBy(id = "mbc-buybutton-addtocart-1-announce")
    public List<WebElement> addToBasketMainButton3;

    @FindBy(id = "buybox-see-all-buying-choices-announce")
    public List<WebElement> seeAllBuyingOptionsButton;

    @FindBy(xpath = "//input[@name=\"submit.addToCart\"]")
    public List<WebElement> addToBasketButtons;

    @FindBy(xpath = "//div[@id='attach-warranty-display']")
    public List<WebElement> Garanty;

    @FindBy(xpath = "(//span/input[@class='a-button-input'])[6]")
    public WebElement buttonNoThanks;


    /**
     * click "add to basket" button, but on Amazon product page,as far as I saw, there are 4 options, because of that all of them is needed to try
     */
    public void addToBasket() {

        /**
         * Check first which "add to basket" button is available,
         * and then click it to add product to basket
         */

        if (addToBasketMainButton1.size() != 0) {
            waitForClickable(addToBasketMainButton1.get(0),3).click();
        } else if (seeAllBuyingOptionsButton.size() != 0) {
           waitForClickable(seeAllBuyingOptionsButton.get(0),3).click();
           waitForClickable(addToBasketButtons.get(0),3).click();
        } else if (addToBasketMainButton2.size() != 0) {
            waitForClickable(addToBasketMainButton2.get(0),3).click();
        } else if (addToBasketMainButton3.size() != 0) {
            waitForClickable(addToBasketMainButton3.get(0),3).click();
        } else if(Garanty.size() != 0) {
            waitForClickable(buttonNoThanks,3).click();
        }
    }

}
