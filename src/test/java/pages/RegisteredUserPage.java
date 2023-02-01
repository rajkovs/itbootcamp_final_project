package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class RegisteredUserPage extends BasePage{

    @FindBy(className = "btnLogout")
    protected WebElement logoutToolbarOption;

    public RegisteredUserPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void logoutUser(){
        logoutToolbarOption.click();
    }
}
