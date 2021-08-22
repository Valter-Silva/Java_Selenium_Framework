package pageModels;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import settings.ReadProperties;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;

public abstract class BasePage {
    protected JavascriptExecutor je;
    protected WebDriver driver;
    protected Actions action;

    /**
     * @param element
     */
    protected void waitForElement(WebElement element) {
        new WebDriverWait(driver, ReadProperties.timeout)
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * This function will take screenshot
     *
     * @param webdriver
     * @param fileWithPath
     * @throws Exception
     */
    protected void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile = new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }
}