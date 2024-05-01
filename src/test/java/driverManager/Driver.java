package driverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.MalformedURLException;
public class Driver {

   private static WebDriver driver;
   private static final Logger logger = LoggerFactory.getLogger(Driver.class);
    static boolean isRemote = Boolean.parseBoolean(PropertyConfiguration.configuration.getProperty("isRemote"));
    static String testUrl = PropertyConfiguration.configuration.getProperty("testUrl");

    public static WebDriver getDriver() throws MalformedURLException {

        if (driver == null) {
            setup();
        }
        return driver;
    }


    @Before
    public static void setup() throws MalformedURLException {
        if (isRemote) {
            DriverManager.getRemoteDriver();
            driver = DriverManager.getDriver();
            logger.info("--Testing Started in Remote Browser--");
        } else {
            DriverManager.getLocalDriver();
            driver = DriverManager.getDriver();
            logger.info("--Testing Started in Local Browser--");
        }
        driver.get(testUrl);
        logger.info(testUrl + (isRemote ? " remote url is opening" : " url is opening"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver=null;
        }
    }
}