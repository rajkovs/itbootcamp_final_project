package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;

    protected WebDriverWait driverWait;

    @FindBy(xpath = "//header//div[@class='v-toolbar__items']")
    protected WebElement headerToolbar;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/span/button")
    protected WebElement hamburgerMenu;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/aside/div[1]/div/div[1]/div[2]/button")
    protected WebElement hideNavigationDrawer;

    @FindBy(xpath = "//div[text()='Login']")
    protected WebElement loginMenuOption;

    public BasePage(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
        PageFactory.initElements(this.driver, this);
    }

    public boolean hamburgerMenuDisplayed() {
        driverWait.until(ExpectedConditions.or(
                ExpectedConditions.elementToBeClickable(hamburgerMenu),
                ExpectedConditions.elementToBeClickable(headerToolbar.findElement(By.className("btnAbout")))));
        return !hamburgerMenu.isDisplayed();
    }
}
