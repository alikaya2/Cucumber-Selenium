package mainActions;

import driverManager.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;


public class BasePageUtil extends Driver {

    private final WebDriver driver;
    WebDriverWait wait;

    private static final Logger logger = LoggerFactory.getLogger(BasePageUtil.class);

    public BasePageUtil(WebDriver driver) throws MalformedURLException {
        this.driver = driver;
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }
    public String getTitle() {
        try {
            logger.info("getTitle method called: getting current page title ");
            return driver.getTitle();
        } catch (Exception ex) {
            logger.error("Can not get current page title!");
            throw ex;
        }
    }

    //If there are different texts and elements connected to a single element, the method of listing and selecting the desired one among them
    public void chooseFromSameKey(By by) {
        List<WebElement> elements = findElements(by);
        for (WebElement chooseElement : elements) {
            if (chooseElement.getText().equalsIgnoreCase("expectedElement")) {
                chooseElement.click();
                break;
            }
        }
    }

    //If there is a dynamic element and it changes when clicked, the following method can be followed.
    public void dynamaticElementClick(By by) {

        if (findElementFromKey(by).getAttribute("style").contains("1")) {//If the element whose attribute is style contains the value 1
            Assert.assertTrue(true);
            findElementFromKey(by).click();

        } else {
            Assert.fail("element bulunamadı!");
        }
    }

    //If there is an alert or frame on a page, to skip it
    public void switchToPopUp(By by) {
        WebElement element = findElementFromKey(by);
        driver.switchTo().alert().accept();

        //driver.switchTo().alert().dismiss(); Alerts are not accepted with this method.

    }

    //If the element is not unique, choosing our request among the values of the element
    public void chooseFromElements(By by) {

        List<WebElement> sampleElement = findElements(by);
        for (int i = 1; i < sampleElement.size(); i++) {
            String expectedElement = sampleElement.get(i).getText();
            if (expectedElement.contains("ActualTextName")) {
                findElements(by).get(i).click();
                break;
            }
        }
    }

    //To extract a String expression from Data and split it
    public void chooseFromDynamicList(String key) {
        String[] itemsNeeded = {"Cucumber", "Brocolli", "Beetroot"};
        addItems(driver, itemsNeeded);

    }

    public static void addItems(WebDriver driver, String[] itemsNeeded) {
        int j = 0;
        List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
        for (int i = 0; i < products.size(); i++) {
            String[] name = products.get(i).getText().split("-");
            String formattedName = name[0].trim();
//format it to get actual vegetable name
//convert array into array list for easy search
//  check whether name you extracted is present in arrayList or not-

            List itemsNeededList = Arrays.asList(itemsNeeded);
            if (itemsNeededList.contains(formattedName)) {
                j++;
//click on Add to cart
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();

                if (j == itemsNeeded.length) {
                    break;
                }
            }
        }
    }
    public WebElement findElementFromKey(By by) {
        try {
            logger.info("findElement method called:  finding " + by);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return driver.findElement(by);
        } catch (Exception e) {
            logger.error(by + " element can not find! " + by.toString());
            throw e;
        }
    }
    public void sendAction(String action, By by) {
        WebElement element = findElementFromKey(by);
        if (element != null) {
            switch (action.toLowerCase()) {
                case "enter":
                    element.sendKeys(Keys.ENTER);
                    break;
                case "tab":
                    element.sendKeys(Keys.TAB);
                    break;
                case "delete":
                    element.sendKeys(Keys.DELETE);
                    break;
                case "clear":
                    element.clear();
                    break;
                case "cancel":
                    element.sendKeys(Keys.CANCEL);
                    break;
                case "esc":
                case "escape":
                    element.sendKeys(Keys.ESCAPE);
                    break;
                default:
                    Assert.fail("Invalid action: " + action);
            }
            logger.info(by + " element " + action + " action sent!");
        } else {
            Assert.fail(by + " element couldn't found!");
        }
    }

    public void waitElementPrecence(By by) {
        findElementFromKey(by);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    public List<WebElement> findElements(By by) {

        List<WebElement> elements;

        try {
            logger.info("findElements method called:  finding " + by);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            elements = driver.findElements(by);
        } catch (Exception e) {
            logger.error(by + " elements can not find! " + by.toString());
            throw e;
        }
        return elements;
    }

    public void hooverElement(By by) {
        WebElement element = findElementFromKey(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).pause(2000).perform();
    }

    public void scrollToElement(By by) {
        WebElement element = findElementFromKey(by);
        if (element != null) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        } else {
            System.out.println("Element couldn't found");
        }
    }

    public void clickElementWithJs(By element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    //If there is a structure such as a table in a page that we need to scroll through,
    public void scrollIntoPage(By by) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('tableSelector').scrollTop=500");
        WebElement key1 = findElementFromKey(by);
        //Let's say there is a string in this key and we want to get it: "This is a string value: 15"
        String sayi = key1.getText().split(":")[1].trim(); //Here we got the value 15 above.
        Assert.assertEquals(sayi, "15");
    }

    public void clickToElement(By by) {
        try {
            scrollToElement(by);
            logger.info("clickElement method called:  clicking " + by);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            findElementFromKey(by).click();
        } catch (
                Exception e) {
            logger.error(by + " element can not clicked!");
            throw e;
        }
    }

    public void sendKeysToElement(By by, String value) {
        try {
            WebElement element = findElementFromKey(by);
            logger.info("sendKeysToElement method called:  sending " + value + " to :" + by);
            element.sendKeys(value);
        } catch (Exception e) {
            logger.error("Error while send text to element.", e);
            throw e;
        }
    }

    public void selectMatchesFromList(By by, int count) {
        List<WebElement> elementList = findElements(by);

        for (int i = 0; i < count; i++) {
            clickToElement((By) elementList.get(i));
        }
    }

    public boolean isSelected(By by) {
        try {
            WebElement element = findElementFromKey(by);
            logger.info("isSelected method called:  checking " + by);
            return element.isSelected();
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isEnabled(By by) {
        try {
            WebElement element = findElementFromKey(by);
            logger.info("isEnabled method called:  checking " + by);
            return element.isEnabled();
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isDisplayed(By by) {
        try {
            WebElement element = findElementFromKey(by);
            logger.info("isDisplayed method called:  checking " + by);
            return element.isDisplayed();
        } catch (NoSuchElementException ex) {
            ex.getMessage();
            return false;
        }
    }

    public boolean isElementPresent(By by) {

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            logger.info("isElementPresent method called:  checking " + by.toString());
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            logger.error(by + " element can not present!");
            return false;
        }
    }

    public void waitByMilliSeconds(long milliseconds) {
        try {
            logger.info("waitByMilliSeconds method called:  waiting " + milliseconds / 1000 + "  seconds.");
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
