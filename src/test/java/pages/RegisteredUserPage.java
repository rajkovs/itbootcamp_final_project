package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class RegisteredUserPage extends BasePage {

    @FindBy(className = "btnLogout")
    protected WebElement logoutToolbarOption;

    @FindBy(className = "btnAdmin")
    protected WebElement adminToolbarOption;

    @FindBy(className = "btnProfile")
    protected WebElement myProfileToolbarOption;

    public RegisteredUserPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void logoutUser() {
        logoutToolbarOption.click();
    }

    public boolean logoutToolbarOptionDisplayed() {
        return logoutToolbarOption.isDisplayed();
    }

    public void openCities(){
        adminToolbarOption.click();
        driver.findElement(By.className("btnAdminCities")).click();
    }

    public void openProfile(){
        myProfileToolbarOption.click();
    }
}
