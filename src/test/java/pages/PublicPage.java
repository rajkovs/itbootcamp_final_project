package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PublicPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/a[3]")
    protected WebElement loginToolbarOption;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[4]")
    protected WebElement signUpToolbarOption;

    @FindBy(className = "btnLocaleActivation")
    protected WebElement localeToolbarOption;

    public PublicPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void openLoginPage() {
        loginToolbarOption.click();
    }

    public void openSignUpPage() {
        signUpToolbarOption.click();
    }

    public void setLocaleToolbarOption(LocaleOptions chosenLocale) {
        localeToolbarOption.click();
        driver.findElement(By.className("btn" + chosenLocale)).click();
    }
}
