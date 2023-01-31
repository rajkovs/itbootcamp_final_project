package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {

    private WebDriver driver;

    private WebDriverWait driverWait;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[3]")
    private WebElement loginOption;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[4]")
    private WebElement signUpOption;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button")
    private WebElement locale;

    public LandingPage(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
        PageFactory.initElements(this.driver, this);
    }

    public void openLoginPage(){
        loginOption.click();
    }

    public void openSignUpPage(){
        signUpOption.click();
    }
}
