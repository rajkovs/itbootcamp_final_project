package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends PublicPage {

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[1]/h1")
    private WebElement header;

    public LandingPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public String getHeaderText() {
        return header.getText();
    }
}
