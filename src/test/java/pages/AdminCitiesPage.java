package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminCitiesPage extends RegisteredUserPage {

    @FindBy(id = "search")
    private WebElement searchField;

    @FindBy(className = "btnNewItem")
    private WebElement newCityButton;

    @FindBy(id = "name")
    private WebElement newCityNameInputField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[5]/div/div/div[3]/button[2]")
    private WebElement saveNewCityButton;

    @FindBy(id = "btnCancel")
    private WebElement cancelNewCityButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")
    private WebElement successfulMessage;

    public AdminCitiesPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void searchCities(String searchText){
        searchField.clear();

        searchField.sendKeys(searchText);
        searchField.sendKeys(Keys.ENTER);
    }

    public String getSuccessfulMessageText(){
        return successfulMessage.getText();
    }

    public void createNewCity(String cityName){
        newCityButton.click();
        newCityNameInputField.clear();
        newCityNameInputField.sendKeys(cityName);
        saveNewCityButton.click();
    }
}
