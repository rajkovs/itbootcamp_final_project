package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminCitiesPage extends RegisteredUserPage {

    @FindBy(id = "search")
    private WebElement searchField;

    @FindBy(className = "btnNewItem")
    private WebElement newCityButton;

    @FindBy(id = "name")
    private WebElement cityNameInputField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[5]/div/div/div[3]/button[2]")
    private WebElement saveCityButton;

    @FindBy(id = "btnCancel")
    private WebElement cancelNewCityButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[5]/div/div/div[2]/button[2]")
    private WebElement deleteCityButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")
    private WebElement successfulMessage;

    @FindBy(xpath = "//*[@id=\"app\"]/div/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")
    private WebElement successfulDeleteMessage;

    public AdminCitiesPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void searchCities(String searchText) {
        searchField.clear();

        searchField.sendKeys(searchText);
        searchField.sendKeys(Keys.ENTER);
    }

    public String getSearchResultsFirstCityName() {
        return driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr[1]/td[2]")).getText();
    }

    public String getSuccessfulMessageText() {
        return successfulMessage.getText();
    }

    public String getSuccessfulDeleteMessage() {
        driverWait.until(ExpectedConditions.textToBePresentInElement(successfulDeleteMessage, "Deleted successfully"));
        return successfulDeleteMessage.getText();
    }

    public void createNewCity(String cityName) {
        newCityButton.click();
        cityNameInputField.clear();
        cityNameInputField.sendKeys(cityName);
        saveCityButton.click();
    }

    public void editCityName(String cityName, String newCityName) {
        searchCities(cityName);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr[1]/td[1]/div/button[1]")).click();
        cityNameInputField.sendKeys(Keys.CONTROL + "a");
        cityNameInputField.sendKeys(newCityName);
        saveCityButton.click();
    }

    public void deleteCity(String cityName) {
        searchCities(cityName);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr[1]/td[1]/div/button[2]")).click();
        deleteCityButton.click();
    }

    public void waitUntilOneSearchResult(){
        driverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr"), 1));
    }
}
