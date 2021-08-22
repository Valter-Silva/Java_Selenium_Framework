package testScripts;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.runner.RunWith;
import utils.DriverCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import pageModels.CarConfiguratorPage;
import pageModels.HomePage;
import utils.SavePrices;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class ValidateModelsPrices {

    WebDriver driver = DriverCreator.getDriver();
    HomePage homePage;
    CarConfiguratorPage carConfigPage;

    @Before
    public void setUpTest() {
        this.driver.manage().window().maximize();
        //this.driver.get("https://www.mercedes-benz.co.uk/");
        this.driver.get("https://www.mercedes-benz.co.uk/passengercars/mercedes-benz-cars/car-configurator.html/motorization/CCci/GB/en/A-KLASSE/KOMPAKT-LIMOUSINE");
    }

    @Test
    @DisplayName("Validate A Class models prices are between £15,000 and £60,000")
    @Parameters({"15000,60000,7,A 180 Sport Hatchback"})
    public void validateAClassModelsPrice(int minValue, int maxValue, int totalVehicles, String model)
            throws Exception {
        //TO IMPLEMENT - missing first part of the test
        //driver could not detect first element - clickModelFilter()
         /**
         this.homePage = new HomePage(this.driver);
         assertTrue(this.homePage.getOurModelsTitle().contains("Our models"));

         this.homePage.clickModelFilter();
         this.homePage.mouseOverModelPreview();
         **/

        //Create Car Configuration page object model and verify the Model Title its correct
        this.carConfigPage = new CarConfiguratorPage(this.driver);
        String text = this.carConfigPage.getModelTitle();
        assertTrue(text.equals(model), "Model title displayed does not correspond to the expected one");

        //Press Diesel checkbox filter
        this.carConfigPage.clickDieselCheckboxFilter();
        assertTrue(this.carConfigPage.getTotalVehicles() == totalVehicles, "Number of total vehicles retrieved is different than expected");

        //Verify models prices and verify its range
        ArrayList<Integer> prices = this.carConfigPage.getResultsPriceList();
        int minFoundValue = Collections.min(prices);
        int maxFoundValue = Collections.max(prices);
        assertTrue(minFoundValue >= minValue, "Minimum price is smaller than expected");
        assertTrue(maxFoundValue <= maxValue, "Maximum price is bigger than expected");

        //Save prices in prices.txt file
        SavePrices.addPricesToTextFile(minFoundValue, maxFoundValue);
    }

    @After
    public void cleanUpTest() {
        if (this.driver != null)
            this.driver.quit();
    }
}
