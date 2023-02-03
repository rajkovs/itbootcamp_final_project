package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[2]/span/form/div/div[5]/button")
    private WebElement signMeUpButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/div/div/div/div/div[1]/ul/li")
    private WebElement signUpErrorMessage;

    @FindBy(xpath = "//*[@id=\"app\"]/div[4]/div/div/div[1]")
    private WebElement successfulSignUpMessage;

    @FindBy(className = "btnClose")
    private WebElement closeButtonSuccessfulSignUpMessage;

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

    public String getSignUpErrorMessageText() {
        return signUpErrorMessage.getText();
    }

    public String getSuccessfulSignUpMessage(){
        driverWait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(successfulSignUpMessage, "Please wait...")));
        return successfulSignUpMessage.getText();
    }

    public void closeSignUpMessage(){
        closeButtonSuccessfulSignUpMessage.click();
    }
}
