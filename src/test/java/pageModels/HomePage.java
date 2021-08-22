package pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(className = "vmos_3zIAO")
    WebElement ourModelsSectionTitle;

    @FindBy(xpath = "//*/section[1]/div[1]/li[1]")
    WebElement hatchbackFilter;

    @FindBy(xpath = "//*/div[3]/div/div[3]/img[1]")
    WebElement hatchbackImagePreview;

    @FindBy(xpath = "//*/div[contains(text(),'Hatchbacks')]")
    WebElement firstModelTitleResult;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    //Get 'Our Models' section title
    public String getOurModelsTitle() {
        this.waitForElement(ourModelsSectionTitle);
        return ourModelsSectionTitle.getText();
    }

    //Click on login button
    public void clickModelFilter() {
        this.waitForElement(hatchbackFilter);
        hatchbackFilter.click();
    }

    //Click on login button
    public void mouseOverModelPreview() {
        this.waitForElement(hatchbackImagePreview);
        action.moveToElement(hatchbackImagePreview).build().perform();
    }

    //Get first model result Title text
    public String getFirstModelResultTitleText() {
        return this.firstModelTitleResult.getText();
    }
}
