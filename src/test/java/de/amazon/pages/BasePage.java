package de.amazon.pages;

import de.amazon.utilities.BrowserUtils;
import de.amazon.utilities.ConfigurationReader;
import de.amazon.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

public abstract class BasePage {

    /**
     * PageFactory design pattern, so the page WebElements are assigned automatically, when it opened.
     *
     * @Param Driver.get()
     */
    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    /**
     * create logger to log infos, errors etc.
     */
    Logger logger = LoggerFactory.getLogger(BasePage.class);

    /**
     * Find Page WebElements
     */
    @FindBy(id = "icp-nav-flyout")
    public WebElement languageBar;

    @FindBy(id = "searchDropdownBox")
    public WebElement searchDepartmentBox;

    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchBox;

    @FindBy(id = "nav-cart")
    public WebElement basketButton;

    @FindBy(css = "#nav-link-accountList-nav-line-1")
    public static WebElement userName;

    @FindBy(tagName = "a")
    public List<WebElement> links;

    /**
     * change language
     *
     * @param language
     */
    public void changeLanguage(String language) {
        BrowserUtils.hover(languageBar);
        //Driver.get().findElement(By.cssSelector("#nav-flyout-icp a[href^=\"#switch-lang\"]"))
                //.findElement(By.xpath("//span[text()=\"" + language + "\"]")).click();
        Driver.get().findElement(By.xpath("//div[@id='nav-flyout-icp']//span[.='"+language+"']")).click();

    }

    /**
     * type a product name to search bar and search it
     *
     * @param item
     */
    public void searchItem(String item) {
        searchBox.clear();
        searchBox.sendKeys(item, Keys.ENTER);
    }

    /**
     * accept cookies, when it is shown
     */
    public void acceptCookies() {
        try {
            Driver.get().findElement(By.id("sp-cc-accept")).click();
            logger.info("Cookies were accepted");
        } catch (NoSuchElementException e) {
        }
    }

    /**
     * select search department
     *
     * @param department
     */
    public void selectSearchDepartment(String department) {
        searchDepartmentBox.click();
        BrowserUtils.waitForClickable(Driver.get().findElement(By.xpath("//option[text()=\"" + department + "\"]")), 5).click();
    }

    public static String getUserName(){
        BrowserUtils.waitForVisibility(userName, 5);
        return userName.getText();
    }

    public void BrokenLinks (){

        String homePage = ConfigurationReader.get("url");
        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;

        Iterator<WebElement> it = links.iterator();
        List<String> allLinks= new ArrayList<>();

        while(it.hasNext()){

            url = it.next().getAttribute("href");
            if(url == null || url.isEmpty()){
                //logger.info(url +", URL is either not configured for anchor tag or it is empty");
                continue;
            }
            if(!url.startsWith(homePage)){
                //logger.info(url+", URL belongs to another domain, skipping it.");
                continue;
            }

            try {
                huc = (HttpURLConnection)(new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();

                if(respCode >= 400){
                    if (respCode==405){
                        //logger.info(url+" request rejected by server,"+ " response code is: "+ respCode);
                    } else if (respCode==503) {
                        //logger.info(url+" service unavailable,"+ " response code is: "+ respCode);
                    }else if (respCode==404) {
                        logger.info(url+" is a broken link,"+ " response code is: "+ respCode);
                    }
                }
                else{
                    //logger.info(url+" is a valid link"+ " response code is: "+ respCode);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void PageTitleAssertion(String expectedTitle) {
        //ant time we are verifying page name, or page subtitle, loader mask appears
        BrowserUtils.waitForPageToLoad(5);
        logger.info("expectedTitle : " + expectedTitle);
        String actualTitle= Driver.get().getTitle().trim();
        logger.info("actualTitle : " + actualTitle);
        Assert.assertTrue("Title must be as " + expectedTitle, actualTitle.contains(expectedTitle));
    }

}
