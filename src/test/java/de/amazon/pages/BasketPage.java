package de.amazon.pages;

import de.amazon.utilities.BrowserUtils;
import de.amazon.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasketPage extends BasePage {

    /**
     * PageFactory design pattern, so the page WebElements are assigned automatically, when it opened.
     *
     * @Param Driver.get()
     */
    public BasketPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    /**
     * Find Page WebElements
     */
    @FindBy(className = "sc-product-price")
    public List<WebElement> productPrices;

    @FindBy(xpath = "//div/span[@id='sc-subtotal-amount-activecart']/span")
    public WebElement subtotal;

    @FindBy(xpath = "//input[@name='proceedToRetailCheckout']")
    public WebElement proceedToCheckout;

    @FindBy(css = "#nav-cart")
    public WebElement navcart;

    @FindBy(xpath = "//span/input[@data-action='delete']")
    public List<WebElement> deleteButtonsOnCart;

    @FindBy(xpath = "//span[@class='a-dropdown-prompt']")
    public List<WebElement> quantity;

    @FindBy(xpath = "(//span/a[@class='a-declarative a-button-text '])[1]")
    public WebElement deliverToAdressButton;

    @FindBy(xpath = "(//span/input[@type='submit'])[1]")
    public WebElement deliveryOptionsContinueButton;

    @FindBy(xpath = "//div/h4")
    public WebElement elementToManipulate;

    @FindBy(xpath = "(//label/input[starts-with(@id, 'pp')] )[1]")
    public WebElement GiftCardBalanceRadioButton;

    @FindBy(xpath = "(//span/input[@title='Buy now'])[1]")
    public WebElement buyButton;

    @FindBy(xpath = "(//div/h4)[1]")
    public WebElement orderVerificationMessage;

    @FindBy(xpath = "//input[@name='forcePlaceOrder']")
    public List<WebElement> DoubleOrderPlaceButton;

    @FindBy(xpath = "//div/h1")
    public List<WebElement> BasketEmptyMessage;

    @FindBy(xpath = "(//div/h2)[1]")
    public List<WebElement> BasketEmptyMessage2;
    /**
     * sum the product prices in the basket
     *
     * @return
     */
    public double calculateTotalPriceOfProducts() {

        double total = 0.00;
        int i=0;
        if (productPrices.size()>0) {
            for (WebElement productPrice : productPrices) {
                total += convert2TwoDecimalsDouble(productPrice.getText()); //* convert2TwoDecimalsDouble(quantity.get(i).getText());
                //System.out.println("quantity.get(i).getText(): " +quantity.get(i).getText());
                i++;
            }
        }
        //return convert2TwoDecimalsDouble(total);
        return total;
    }

    /**
     * convert the String Price value to double with 2 decimals after point
     *
     * @param text
     * @return
     */
    public double convert2TwoDecimalsDouble(String text) {
        double value= Double.parseDouble(text.substring(1));
        return Math.round(value * 100.00) / 100.00;
    }

    /**
     * convert the double Price value to double with 2 decimals after point
     *
     * @param value
     * @return
     */
    /**
     * public double convert2TwoDecimalsDouble(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
     */

    /**
     * get total amount of prices, written at bottom on the page,  in double (2 decimals) format
     *
     * @return
     */
    public double getSubtotal() {
        return convert2TwoDecimalsDouble(subtotal.getText());
    }

    public void deleteAllProducts() {
        if (deleteButtonsOnCart.size()>0) {
            for (int i = deleteButtonsOnCart.size(); i > 0; i--) {
                deleteButtonsOnCart.get(i - 1).click();
            }
        }
    }
    public void checkoutDirectionAssertion(String pageNameOrder) {
        String actualTitle = Driver.get().getTitle();
        String expectedTitle =pageNameOrder;
        Assert.assertTrue("Verify that user is on the Amazon Registration Page", actualTitle.contains(expectedTitle));

    }

    public void DeliverToThisAdress () {
        deliverToAdressButton.click();
    }

    public void deliveryOptionsContinue() {
        BrowserUtils.waitForPageToLoad(3);
        deliveryOptionsContinueButton.click();
        BrowserUtils.waitForPageToLoad(3);
    }

    public void BuyNow() {
        BrowserUtils.waitForPageToLoad(3);
        buyButton.click();
        if (DoubleOrderPlaceButton.size()>0) {
            DoubleOrderPlaceButton.get(0).click();
        }
    }

    public void orderVerification(String str) {
        BrowserUtils.waitForPageToLoad(3);
        Assert.assertEquals(orderVerificationMessage.getText(), str);
    }

    public void basketEmptyVerification(String str) {
        BrowserUtils.waitForPageToLoad(3);
        if(BasketEmptyMessage.size()>0) {
            BrowserUtils.waitForVisibility(BasketEmptyMessage.get(0),3);
            Assert.assertEquals(BasketEmptyMessage.get(0).getText().trim(), str);
        } else if (BasketEmptyMessage2.size()>0) {
            BrowserUtils.waitForVisibility(BasketEmptyMessage2.get(0),3);
            Assert.assertEquals(BasketEmptyMessage2.get(0).getText().trim(), str);
        }

    }

    public void UseGiftCardBalanceVerification(String str) {
        Assert.assertTrue("Gift Card Balance is Selected", GiftCardBalanceRadioButton.isSelected());
    }



}
