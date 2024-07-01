package stepDefinitions;

import driverManager.Driver;
import mainActions.BasePageUtil;
import org.openqa.selenium.*;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static locators.ProductDetailPageConstants.*;
import static locators.ProductPageConstants.*;
import static locators.SignInConstants.*;
import static locators.HomePageConstants.*;

public class Steps extends Driver {
    private static final ThreadLocal<String> priceValue = new ThreadLocal<>();
    private final BasePageUtil driverMethods;
    private static final Logger logger = LoggerFactory.getLogger(Steps.class);
    JavascriptExecutor jsExecutor;
    Actions actions;
    WebDriverWait wait;

    public Steps() throws MalformedURLException {
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebDriver driver = Driver.getDriver();
        this.jsExecutor = (JavascriptExecutor) getDriver();
        this.actions = new Actions(getDriver());
        this.driverMethods = new BasePageUtil(driver);
    }

    public void clickSignInElement() {
        Assert.assertTrue(driverMethods.isSelected(HOMEPAGESIGNINBTN), "HomePage Sign In element is not clicked");
        WebElement signInElement = driverMethods.findElementFromKey(HOMEPAGESIGNINBTN);
        driverMethods.scrollToElement(HOMEPAGESIGNINBTN);
        if (signInElement.getText().contains("Sign In")) {
            logger.info(HOMEPAGESIGNINBTN + " element is exist");
        } else {
            logger.info(HOMEPAGESIGNINBTN + " element can not found");
        }
        driverMethods.clickToElement(HOMEPAGESIGNINBTN);
        waitByMilliSeconds(5);
    }

    public void loginWithUser() {
        WebElement element = driverMethods.findElementFromKey(LOGINEMAIL);
        element.sendKeys("name.sirname36@gmail.com");
        WebElement element2 = driverMethods.findElementFromKey(LOGINPASSWORD);
        element2.sendKeys("Namesirname36");
        driverMethods.clickToElement(SIGNINPAGESIGNINBUTTON);
        logger.info("{}" , SIGNINPAGESIGNINBUTTON + "clicked");
        waitByMilliSeconds(3000);
        Assert.assertTrue(driverMethods.isDisplayed(WELCOMETEXTELEMENT), "Can not sign in");
        Assert.assertTrue(driverMethods.findElementFromKey(WELCOMETEXTELEMENT).getText().contains("name sirname"), "Excepted name couldn't sign in");
    }
    public void checkSuccessfulLogin() {
        Assert.assertTrue(driverMethods.getTitle().contains("Home Page"), "Couldn't access Magento websites!");
        logger.info("Accessed Magento site!");
        Assert.assertTrue(driverMethods.isDisplayed(HOMEPAGEMAINIMG), "Couldn't open HomePage!");
    }

    public void waitByMilliSeconds(long milliseconds) {
        try {
            logger.info(milliseconds + " miliseconds waiting!");
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Click the element into a page and check the response
    public void httpResponse(By by) throws IOException {
        String url = driverMethods.findElementFromKey(by).getAttribute("a");
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("HEAD");
        connection.connect();
        int responseCode = connection.getResponseCode();
        logger.info(String.valueOf(responseCode));
        Assert.assertEquals(responseCode, 200, "Response 200 dönmedi!");

    }

    public void sendKeysMainPageSearchBox(String parameter) {
        driverMethods.sendKeysToElement(MAINPAGESEARCHBOX, parameter);
        driverMethods.hooverElement(MAINPAGESEARCHBOXTANKSCOTTON);
        driverMethods.clickToElement(MAINPAGESEARCHBOXTANKSCOTTON);
        Assert.assertTrue(driverMethods.isDisplayed(PRODUCTPAGE_SEARCH_RESULT), "Search asking couldn't sent");
        Assert.assertTrue(driverMethods.findElementFromKey(PRODUCTPAGE_SEARCH_RESULT).getText().contains(parameter + "s cotton"), "is not correct parameter");
    }

    public String findProductFromList(String price) {
        boolean isProductFound = false;
        while (!isProductFound) {
            List<WebElement> pageElements = driverMethods.findElements(PRODUCT_PRICE);
            List<WebElement> streamProduct = pageElements.stream().filter(product -> product.getText().equalsIgnoreCase(price)).collect(Collectors.toList());

            if (!streamProduct.isEmpty()) {
                WebElement firstProduct = streamProduct.get(0);
                int index = pageElements.indexOf(firstProduct);
                logger.info(String.valueOf(index));
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", firstProduct);
                actions.moveToElement(firstProduct).pause(3000).perform();
                String script = "var addToCartButtons = document.querySelectorAll(\"[title='Add to Cart']\");" + "addToCartButtons[" + index + "].click();";
                jsExecutor.executeScript(script);
                waitByMilliSeconds(5000);

                return setPrice(price);
            } else {

                if (!goToNextPage()) {
                    logger.info("Couldn't find that: {} " , price);
                    break;
                }
            }
        }
        return price;
    }

    private boolean goToNextPage() {
        try {
            driverMethods.findElementFromKey(PRODUCT_PAGE_NEXT_BUTTON);
            if (driverMethods.isDisplayed(PRODUCT_PAGE_NEXT_BUTTON)) {
                driverMethods.clickToElement(PRODUCT_PAGE_NEXT_BUTTON);
                waitByMilliSeconds(5000);
                return true;
            } else {
                logger.info("Next page is not found!");
                return false;
            }
        } catch (NoSuchElementException e) {
            logger.info("Next page is not found!");
            return false;
        }
    }

    public String setPrice(String price) {
        priceValue.set(price);
        System.out.println(price + "value\t" + priceValue);
        return price;
    }

    public String getPrice() {
        return priceValue.get();
    }

    public void checkFirstProduct() {
        String actualValue = findProductFromList(getPrice());
        logger.info(actualValue + "\tvalue is got");
        WebElement element = driverMethods.findElementFromKey(PRODUCT_DETAIL_PRODUCT_PRICE);
        String value = element.getText();
        logger.info("value is\t" + value);
        Assert.assertEquals(actualValue, value, "those values are not equals!");
    }
}