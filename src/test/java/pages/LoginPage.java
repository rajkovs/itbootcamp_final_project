package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/span/form/div/div[3]/button")
    private WebElement loginButton;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li")
    private WebElement loginErrorMessage;

    public LoginPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void login(String email, String password) {
        emailField.clear();
        passwordField.clear();

        emailField.sendKeys(email);
        passwordField.sendKeys(password);

        loginButton.click();
    }

    public String getEmailFieldTypeValue(){
        return emailField.getAttribute("type");
    }

    public String getPasswordFieldTypeValue(){
        return passwordField.getAttribute("type");
    }

    public String getLoginErrorMessageText(){
        return loginErrorMessage.getText();
    }
}
