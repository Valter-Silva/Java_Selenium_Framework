package pageModels;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import settings.ReadProperties;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static settings.ReadProperties.configPath;

public class CarConfiguratorPage extends BasePage {

    @FindBy(xpath = "//div[contains(text(),'A 180 Sport Hatchback')]")
    WebElement modelTitle;

    @FindBy(xpath = "//*[@id=\"owcc-cont\"]/div/owcc/cc-app-root/div/cc-app-container/div/div[2]/div/div[2]/div/div[2]/cc-motorization/cc-motorization-filters-section/cc-motorization-filters/form/fieldset[1]/div[2]/div[2]/wb-checkbox-control/label/wb-icon")
    WebElement dieselFilterCheckbox;

    @FindBy(xpath = "//*/div[@class='cc-motorization-comparison__header-row']")
    WebElement modelResultsHeader;

    @FindBy(xpath = "//div[@class='cc-motorization-comparsion-status__info-text']")
    WebElement totalFoundVehiclesText;

    @FindBy(xpath = "//div[@class='cc-motorization-comparison__header-row']//button[@class='cc-slider-buttons__button--right ng-star-inserted']/div/wb-icon")
    WebElement slideRightButton;

    @FindBy(xpath = "//*[contains(@class,'cc-motorization-header__price')]")
    WebElement vehiclePrice;

    @FindBy(xpath = "//*/legend[@class='cc-motorization-filter__label']")
    WebElement fuelType;

    public CarConfiguratorPage(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
        this.je = (JavascriptExecutor) driver;

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    //Get the Model Title being configurator
    public String getModelTitle() {
        waitForElement(modelTitle);
        return modelTitle.getText();
    }

    //Select Diesel filter checkbox
    public void clickDieselCheckboxFilter() throws InterruptedException {
        je.executeScript("arguments[0].scrollIntoView(true);", fuelType); //Scroll page until top of FuelType filters
        Thread.sleep(1000); // TO IMPROVE! - Force thread to wait for scroll before clicking element
        dieselFilterCheckbox.click();
        Thread.sleep(2000);
    }

    //Get total vehicles matching filter settings
    //Parses total string from vehicle search and retrieves int
    public int getTotalVehicles() {
        return Integer.parseInt(totalFoundVehiclesText.getText().replaceAll("\\D+", ""));
    }

    //Get a single vehicle price
    //Parses total string from vehicle price (e.g. £29,510 ) and retrieves int
    public int getVehiclePrice() {
        return Integer.parseInt(vehiclePrice.getText().replaceAll("\\D+", ""));
    }

    //Return Array list with Model prices from the search result
    public ArrayList<Integer> getResultsPriceList() throws Exception {
        ArrayList<Integer> results = new ArrayList<>();
        List<WebElement> listOfElements = driver.findElements(By.xpath("//*[contains(@class,'cc-motorization-header__price')]"));

        for (WebElement element : listOfElements) {
            if (!element.isDisplayed()) break;

            results.add(Integer.parseInt(element.getText().replaceAll("\\D+", "")));
            this.slideRightButton.click();
            Thread.sleep(1000); //TO IMPROVE - Force small wait for slide right action
        }

        //Display Array list of prices
        System.out.println("Prices list: " + results.toString());

        //Take results SnapShot
        this.takeSnapShot(this.driver, ReadProperties.configPath+"/pictures/test.png");

        return results;
    }
}
