package driverManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyConfiguration {

    public static Properties configuration = loadProperties("src/configuration.properties");
    //public static Properties allureProperties = loadProperties("src/allure.properties");

    private static Properties loadProperties(String filePath) {
        Properties pro = new Properties();
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            pro.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return pro;
    }
}
