package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigFileReader {
    private static Properties properties;

    private static void initProp() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("./src/test/resources/config.properties");
            properties.load(fileInputStream);
        } catch (Exception e) {
            System.out.println("Unable to read Properties file.");
        }
    }

    public static String getProperty(String key) {
        initProp();
        return properties.getProperty(key);
    }
}