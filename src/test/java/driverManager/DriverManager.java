package driverManager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
public class DriverManager{
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }
    private static final ChromeOptions chromeOptions = new ChromeOptions();
    private static final FirefoxOptions firefoxOptions = new FirefoxOptions();
    private static final EdgeOptions edgeOptions = new EdgeOptions();
    static String browserName = PropertyConfiguration.configuration.getProperty("browserName");
    static Capabilities capabilities;
    static final String gridURL = "http://localhost:4444/wd/hub";


    public static void getRemoteDriver() throws MalformedURLException {
        switch (browserName) {
            case "chrome":
                capabilities = new ChromeOptions();
                driver = new RemoteWebDriver(new URL(gridURL), capabilities);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().window().maximize();
                break;
            case "firefox":
                capabilities = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL(gridURL), firefoxOptions);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                break;
            case "edge":
                capabilities = new EdgeOptions();
                driver = new RemoteWebDriver(new URL(gridURL), edgeOptions);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browserName);
        }
    }
    public static void getLocalDriver(){
        if ("chrome".equalsIgnoreCase(browserName)) {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("disable-popup-blocking");
            chromeOptions.addArguments("ignore-certificate-errors");
            chromeOptions.addArguments("disable-translate");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--disable-notifications");

        } else if ("firefox".equalsIgnoreCase(browserName)) {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().window().maximize();
            firefoxOptions.addArguments("--no-sandbox");
            firefoxOptions.addArguments("--start-fullscreen");
            firefoxOptions.addArguments("--allow-running-insecure-content");
            firefoxOptions.addArguments("--ignore-certificate-errors");
            firefoxOptions.addArguments("--disable-infobars");
            firefoxOptions.addArguments("--disable-notifications");

        } else if ("edge".equalsIgnoreCase(browserName)) {
            driver = new EdgeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().window().maximize();
            edgeOptions.addArguments("--start-maximized");
            edgeOptions.addArguments("--disable-infobars");
            edgeOptions.addArguments("--ignore-certificate-errors");
            edgeOptions.addArguments("--disable-popup-blocking");
            edgeOptions.addArguments("--disable-notifications");
        }
    }

}
