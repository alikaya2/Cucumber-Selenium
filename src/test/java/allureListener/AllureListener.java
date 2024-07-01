package allureListener;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;

public class AllureListener{
    private static final Logger logger = LoggerFactory.getLogger(AllureListener.class);

    public static void captureScreenshot(WebDriver driver, Scenario scenario) {
        if (driver == null) {
            logger.error("WebDriver instance is null. Cannot capture screenshot.");
            return;
        }
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            String screenshotName = scenario.getName() + "_" + UUID.randomUUID(); // Unique screenshot name
            scenario.attach(screenshot, "image/png", screenshotName);
            logger.info(screenshotName + "\tnamed screenshot captured and added to Allure report.");
        } catch (Exception e) {
            logger.error("Failed to capture screenshot:\t" + e.getMessage());
        }
    }
}
