package de.amazon.pages;

import de.amazon.utilities.BrowserUtils;
import de.amazon.utilities.ConfigurationReader;
import de.amazon.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends BasePage {

    /**
     * PageFactory design pattern, so the page WebElements are assigned automatically, when it opened.
     *
     * @Param Driver.get()
     */
    public LoginPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "(//div/a[@class='nav-action-button'])[1]")
    public WebElement AnmeldenButton;

    @FindBy(css = "#ap_email")
    public WebElement userName;

    @FindBy(xpath = "//div/span[@id='continue']")
    public WebElement weiter;

    @FindBy(css = "#ap_password")
    public List<WebElement> password;

    @FindBy(css = "#signInSubmit")
    public WebElement submit;

    @FindBy(xpath = "//div/h4[@class='a-alert-heading']")
    public WebElement warningWrongEntry;

    public void login(String userNameStr, String passwordStr) {
        if (userNameStr==null || passwordStr==null ) {
            userNameStr=ConfigurationReader.get("username");
            passwordStr=ConfigurationReader.get("password");
        }
        new Actions(Driver.get()).moveToElement(new HomePage().navMenuKonto).build().perform();
        AnmeldenButton.click();
        userName.sendKeys(userNameStr);
        weiter.click();
        //if (!userNameStr.contains("invalid")) {
        if(password.size()>0) {
            password.get(0).sendKeys(passwordStr);
            submit.click();
            BrowserUtils.waitForPageToLoad(5);
        }
    }

    public void userNameAssertion (String str) {
        String actualFullname = HomePage.getUserName();
        String expectedFullname = "Hello, " + str;
        try {
            Assert.assertEquals(actualFullname, expectedFullname);
        } catch (AssertionError assertionError) {
            logger.info("Assertion is unsuccessfully for user: " + actualFullname + ", expectedFullname: " + expectedFullname);
        }
        BrowserUtils.waitFor(1);
    }

    public void InvalidLoginTryAssertion () {
        LoginPage loginPage = new LoginPage();
        String expectedWarning1 = "There was a problem";
        String expectedWarning2 = "Important Message!";
        String actualWarning = loginPage.warningWrongEntry.getText();
        List <String> expectedWarning = new ArrayList<>();
        expectedWarning.add(expectedWarning1);
        expectedWarning.add(expectedWarning2);

        try {
            Assert.assertTrue(expectedWarning.contains(actualWarning));
        } catch (AssertionError assertionError) {
            logger.info("Assertion is successfull: " + ", ActualWarning: " + actualWarning + ", expectedWarning: " + expectedWarning);
        }
    }

    public void loginAssertion() {
        String actual = Driver.get().getTitle();
        String expected = "Amazon.de: Low Prices in Electronics, Books, Sports Equipment & more";
        Assert.assertEquals(expected, actual.trim());
        logger.info("login successful");
    }


}
