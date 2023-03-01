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

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/aside/div[1]/div/a[4]/div[2]")
    protected WebElement signUpMenuOption;

    @FindBy(className = "btnLocaleActivation")
    protected WebElement localeToolbarOption;

    public PublicPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void openLoginPage() {
        if (hamburgerMenuDisplayed()) {
            loginToolbarOption.click();
        } else {
            hamburgerMenu.click();
            loginMenuOption.click();
        }
    }

    public void openSignUpPage() {
        if (hamburgerMenuDisplayed()) {
            signUpToolbarOption.click();
        } else {
            hamburgerMenu.click();
            signUpMenuOption.click();
        }
    }

    public void setLocaleToolbarOption(LocaleOptions chosenLocale) {
        localeToolbarOption.click();
        driver.findElement(By.className("btn" + chosenLocale)).click();
    }
}
