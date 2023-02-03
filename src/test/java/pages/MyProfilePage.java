package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MyProfilePage extends RegisteredUserPage {

    @FindBy(id = "name")
    private WebElement nameInputField;

    @FindBy(id = "phone")
    private WebElement phoneInputField;

    @FindBy(id = "city")
    private WebElement cityInputField;

    @FindBy(id = "country")
    private WebElement countryInputField;

    @FindBy(id = "urlTwitter")
    private WebElement twitterInputField;

    @FindBy(id = "urlGitHub")
    private WebElement gitHubInputField;

    @FindBy(className = "btnSave")
    private WebElement saveProfileChangesButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]")
    private WebElement savedSuccessfullyMessage;

    public MyProfilePage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void selectCity(int cityIndex) {
        List<WebElement> citiesList = driver.findElements(By.xpath("//*[@role = \"listbox\"]/div/div/div"));
        citiesList.get(cityIndex).click();
    }

    public void editProfile(String name, String phone, int cityIndex, String country, String twitterURL, String gitHubURL) { //add city edit option
        nameInputField.click();
        nameInputField.sendKeys(Keys.CONTROL + "a");
        nameInputField.sendKeys(Keys.DELETE);
        phoneInputField.click();
        phoneInputField.sendKeys(Keys.CONTROL + "a");
        phoneInputField.sendKeys(Keys.DELETE);
        countryInputField.click();
        countryInputField.sendKeys(Keys.CONTROL + "a");
        countryInputField.sendKeys(Keys.DELETE);
        twitterInputField.click();
        twitterInputField.sendKeys(Keys.CONTROL + "a");
        twitterInputField.sendKeys(Keys.DELETE);
        gitHubInputField.click();
        gitHubInputField.sendKeys(Keys.CONTROL + "a");
        gitHubInputField.sendKeys(Keys.DELETE);

        nameInputField.sendKeys(name);
        phoneInputField.sendKeys(phone);

        cityInputField.click();
        selectCity(cityIndex);

        countryInputField.sendKeys(country);
        twitterInputField.sendKeys(twitterURL);
        gitHubInputField.sendKeys(gitHubURL);
    }

    public void saveProfileEdits(){
        saveProfileChangesButton.click();
    }

    public String getSuccessfulSaveEditMessage(){
        driverWait.until(ExpectedConditions.textToBePresentInElement(savedSuccessfullyMessage, "Profile saved successfuly"));
        return savedSuccessfullyMessage.getText();
    }

    public String getCurrentName(){
        return nameInputField.getAttribute("value");
    }

    public String getCurrentPhone(){

        return phoneInputField.getAttribute("value");
    }

    public String getCurrentCity(){
        return cityInputField.getAttribute("value");
    }

    public String getCurrentCountry(){
        return countryInputField.getAttribute("value");
    }

    public String getCurrentTwitterURL(){
        return twitterInputField.getAttribute("value");
    }

    public String getCurrentGitHubURL(){
        return gitHubInputField.getAttribute("value");
    }

}
