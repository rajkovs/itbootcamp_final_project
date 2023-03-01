package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class RegisteredUserPage extends BasePage {

    @FindBy(className = "btnLogout")
    protected WebElement logoutToolbarOption;

    @FindBy(xpath = "//aside//div[contains(.//div/text(),\" Logout \")]")
    protected WebElement logoutMenuOption;

    @FindBy(className = "btnAdmin")
    protected WebElement adminToolbarOption;

    @FindBy(xpath = "//div[text()='Admin']")
//    @FindBy(xpath = "//*[@id=\"app\"]/div/div/aside/div[1]/div/div[2]/div")
    protected WebElement adminMenuOption;

    @FindBy(className = "btnProfile")
    protected WebElement myProfileToolbarOption;

    @FindBy(xpath = "//aside//a[contains(.//div/text(),\"My Profile\")]")
    protected WebElement myProfileMenuOption;

    public RegisteredUserPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void logoutUser() {
        if (hamburgerMenuDisplayed()) {
            logoutToolbarOption.click();
        } else {
            driverWait.until(ExpectedConditions.elementToBeClickable(hamburgerMenu));
            hamburgerMenu.click();
            logoutMenuOption.click();
        }
    }

    public boolean logoutOptionDisplayed() {
        if (hamburgerMenuDisplayed()) {
            return logoutToolbarOption.isDisplayed();
        } else {
            driverWait.until(ExpectedConditions.elementToBeClickable(hamburgerMenu));
            hamburgerMenu.click();
            driverWait.until(ExpectedConditions.or(
                    ExpectedConditions.elementToBeClickable(logoutMenuOption),
                    ExpectedConditions.elementToBeClickable(loginMenuOption)));
            boolean isDisplayed = logoutMenuOption.isDisplayed();
            driverWait.until(ExpectedConditions.elementToBeClickable(hideNavigationDrawer));
            hideNavigationDrawer.click();
            driverWait.until(ExpectedConditions.elementToBeClickable(hamburgerMenu));
            return isDisplayed;
        }
    }

    public void openCities() {
        if (hamburgerMenuDisplayed()) {
            adminToolbarOption.click();
            driver.findElement(By.className("btnAdminCities")).click();
        } else {
            driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"hidden-md-and-up\"]/button")));
            hamburgerMenu.click();
            driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/div/aside/div[1]/div/div[2]/div")));
            adminMenuOption.click();
            By adminCitiesMenuOption = By.xpath("//*[@id=\"app\"]/div[1]/div/aside/div[1]/div/div[2]/div[2]/a[1]");
            driver.findElement(adminCitiesMenuOption).click();
            driverWait.until(ExpectedConditions.elementToBeClickable(hamburgerMenu));
        }
    }

    public void openProfile() {
        if (hamburgerMenuDisplayed()) {
            myProfileToolbarOption.click();
        } else {
            driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"hidden-md-and-up\"]/button")));
            hamburgerMenu.click();
            myProfileMenuOption.click();
            driverWait.until(ExpectedConditions.elementToBeClickable(hamburgerMenu));
        }
    }
}
