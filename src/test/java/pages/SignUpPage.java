package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage extends PublicPage {

    @FindBy(id = "name")
    private WebElement nameInputField;

    @FindBy(id = "email")
    private WebElement emailInputField;

    @FindBy(id = "password")
    private WebElement passwordInputField;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordInputField;

    @FindBy(xpath = "//button[text()=\"Sign me up\"]")
    private WebElement signMeUpButton;

    public SignUpPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void signUpUser(String name, String email, String password, String confirmPassword) {
        nameInputField.sendKeys(name);
        emailInputField.sendKeys(email);
        passwordInputField.sendKeys(password);
        confirmPasswordInputField.sendKeys(confirmPassword);

        signMeUpButton.click();
    }

    public String getEmailFieldTypeValue() {
        return emailInputField.getAttribute("type");
    }

    public String getPasswordFieldTypeValue() {
        return passwordInputField.getAttribute("type");
    }

    public String getConfirmPasswordFieldTypeValue() {
        return confirmPasswordInputField.getAttribute("type");
    }
}
