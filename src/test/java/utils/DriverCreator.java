package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import settings.ReadProperties;

import java.util.concurrent.TimeUnit;

public class DriverCreator {

    /**
     * Generates WebDriver according to configuration properties
     * browser
     * timeout
     * @return WebDriver
     */
    public static WebDriver getDriver() {
        WebDriver driver = null;
        String projectPath = System.getProperty("user.dir");
        ReadProperties.getProperties();

        if (ReadProperties.browser.equalsIgnoreCase("chrome")) {
            projectPath += "\\drivers\\chromedriver\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", projectPath);
            System.out.println("projectPath: " + projectPath);

            ChromeOptions options = new ChromeOptions();
            options.addArguments("user-data-dir=C:\\Users\\ValterSilva\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
            options.setCapability("profile.default_content_setting_values.cookies", 2);
            options.setCapability("profile.managed_default_content_settings.javascript", 2);
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(ReadProperties.timeout, TimeUnit.SECONDS);
        } else if (ReadProperties.browser.equalsIgnoreCase("firefox")) {
            projectPath += "\\drivers\\geckodriver\\geckodriver.exe";
            System.out.println("projectPath: " + projectPath);
            System.setProperty("webdriver.gecko.driver", projectPath);

            FirefoxProfile profile = new FirefoxProfile();
            //profile.setPreference("network.cookie.cookieBehavior", 2);
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("user-data-dir=C:\\Users\\ValterSilva\\AppData\\Local\\Mozilla\\Firefox\\Profiles");
            options.setProfile(profile);
            driver = new FirefoxDriver(options);
            driver.manage().timeouts().implicitlyWait(ReadProperties.timeout, TimeUnit.SECONDS);
        }

        return driver;
    }
}
