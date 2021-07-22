package de.amazon.pages;

import de.amazon.utilities.BrowserUtils;
import de.amazon.utilities.ConfigurationReader;
import de.amazon.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    /**
     * PageFactory design pattern, so the page WebElements are assigned automatically, when it opened.
     *
     * @Param Driver.get()
     */

    @FindBy(css = "#nav-item-signout")
    public WebElement logout;

    @FindBy(xpath = "//span[@class='nav-line-2 ']")
    public WebElement navMenuKonto;


    public void homePageAssertion() {
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
        BrowserUtils.waitForPageToLoad(5);
        Driver.get().manage().deleteAllCookies();
        new HomePage().acceptCookies();
        String actual = Driver.get().getTitle();
        String expected = "Amazon.de: Günstige Preise für Elektronik & Foto, Filme, Musik, Bücher, Games, Spielzeug & mehr";
        Assert.assertEquals(expected, actual);
        logger.info("Landing page Assertion Successful");

    }

    public void logout() {
        new Actions(Driver.get()).moveToElement(navMenuKonto).build().perform();
        BrowserUtils.waitFor(2);
        new Actions(Driver.get()).moveToElement(logout).build().perform();
        BrowserUtils.waitFor(2);
        logout.click();
    }

    public void logoutAssertion() {
        BrowserUtils.waitForPageToLoad(5);
        String actual = Driver.get().getTitle();
        String expected = "Amazon Sign In";
        Assert.assertEquals(expected, actual);
        logger.info("Logout Assertion Successful");
        BrowserUtils.waitFor(3);

    }

    public void HTMLManipulation(String arg, WebElement element, int time) {
        BrowserUtils.waitForPageToLoad(3);
        ((JavascriptExecutor)Driver.get()).executeScript(
                "var ele=arguments[0]; ele.innerHTML = '"+arg+"';", element);
        BrowserUtils.waitFor(time);
    }
}
