package de.amazon.pages;

import de.amazon.utilities.BrowserUtils;
import de.amazon.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends BasePage {

    /**
     * PageFactory design pattern, so the page WebElements are assigned automatically, when it opened.
     *
     * @Param Driver.get()
     */
    public OrderPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    /**
     * Find Page WebElements
     */


    @FindBy(xpath = "//a/span[@class='nav-line-2']")
    public WebElement OrdersButton;

    @FindBy(css = "#Request-Cancellation_2")
    public List <WebElement> RequestCancelButton;

    @FindBy(xpath = "//div[@class='card-footer']")
    public List<WebElement> select;

    @FindBy(xpath = "//*[.='Cancel this']")
    public List<WebElement> cancelThis;

    @FindBy(xpath = "//*[.='No longer needed']")
    public List<WebElement> noLongerNeeded;

    @FindBy(xpath = "//div/textarea[@class='textarea-input']")
    public List<WebElement> textArea;

    @FindBy(xpath = "//*[.='Send message']")
    public List<WebElement> sendMessageButton;

    @FindBy(xpath = "//*[contains(text(),'Done! I sent your message')]")
    public List<WebElement> orderCancellationVerificationMessage;

    @FindBy(xpath = "//span[@id='cancelButton']")
    public WebElement CancelselectedItemButton;

    @FindBy(css = "#Cancel-items_2")
    public List<WebElement> CancelButton;

    @FindBy(xpath = "//div/h4")
    public List<WebElement> OrderCancelledMessage;


    public void navigateToOrder() {
        BrowserUtils.waitForPageToLoad(3);
        OrdersButton.click();
    }

    public void orderCancellation() {
        BrowserUtils.waitForPageToLoad(3);
        if(CancelButton.size()>0) {
            CancelButton.get(0).click();
            BrowserUtils.waitForPageToLoad(3);
            BrowserUtils.waitForClickable(CancelselectedItemButton,5);
            new Actions(Driver.get()).moveToElement(CancelselectedItemButton).doubleClick().build().perform();
        } else if (RequestCancelButton.size()>0) {
            RequestCancelButton.get(0).click();
            if(select.size()>0) {
                BrowserUtils.waitForClickable(select.get(0),3).click();
            }
            if (cancelThis.size()>0) {
                BrowserUtils.waitForClickable(cancelThis.get(0),3).click();
            }
            if (noLongerNeeded.size()>0) {
                BrowserUtils.waitForClickable(noLongerNeeded.get(0), 3).click();
            }
            if (textArea.size()>0) {
                textArea.get(0).sendKeys("I need this item no longer. Please cancel the order");
                BrowserUtils.waitForClickable(sendMessageButton.get(0),3).click();
                BrowserUtils.waitFor(5);
            }
        }

    }

    public void orderCancellationVerification(String str1, String str2) {
        BrowserUtils.waitForPageToLoad(3);
        if(OrderCancelledMessage.size()>0) {
            Assert.assertTrue("Order cancelled",OrderCancelledMessage.get(0).getText().contains(str2));
            logger.info(OrderCancelledMessage.get(0).getText());
            BrowserUtils.waitFor(5);
        } else if (orderCancellationVerificationMessage.size()>0) {
            Assert.assertTrue("Order Cancellation Request has been sent",orderCancellationVerificationMessage.get(0).getText().contains(str1));
            logger.info(orderCancellationVerificationMessage.get(0).getText());
            BrowserUtils.waitFor(5);
        }
    }

}
