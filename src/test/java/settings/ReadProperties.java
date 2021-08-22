package settings;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;

public class ReadProperties {

    static Properties prop = new Properties();
    public static String configPath = System.getProperty("user.dir");
    public static String browser = null;
    public static int timeout = 10;

    /**
     * Retrieves properties from config.properties file and sets Property values
     * browser - 'chrome' + 'firefox'
     * headless - NOT IMPLEMENTED
     * timeout - int (Seconds)
     */
    public static void getProperties() {
        try {
            InputStream input = new FileInputStream(configPath + "/src/test/java/settings/config.properties");
            prop.load(input);
            browser = prop.getProperty("browser").replaceAll("\\s", "");
            System.out.println("Browser: " + browser);
            timeout = Integer.parseInt(prop.getProperty("timeout"));
            System.out.println("Timeout: " + timeout);

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }
    }

}
