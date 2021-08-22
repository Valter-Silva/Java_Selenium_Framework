package settings;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;

public class ReadProperties {

    static Properties prop = new Properties();
    public static String configPath = System.getProperty("user.dir");
    public static String browser = null;
    public static String chromeProfilePath = null;
    public static String firefoxProfilePath = null;
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

            //Load config properties
            browser = prop.getProperty("browser").replaceAll("\\s", "");
            timeout = Integer.parseInt(prop.getProperty("timeout"));
            chromeProfilePath = prop.getProperty("chromeprofilepath");
            firefoxProfilePath = prop.getProperty("firefoxprofilepath");

            //Print loaded config properties in the Console
            System.out.println("Browser: " + browser);
            System.out.println("Timeout: " + timeout);
            System.out.println("Chrome profile path: " + chromeProfilePath);
            System.out.println("Firefox profile path: " + firefoxProfilePath);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }
    }

}
