package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignUpPage;

import java.lang.reflect.Method;

public class SignUpTests extends BaseTest {

    private SignUpPage signUpPage;

    private HomePage homePage;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        signUpPage = new SignUpPage(driver, driverWait);
        homePage = new HomePage(driver, driverWait);
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        landingPage.openSignUpPage();
    }

    @Test
    public void SignUpPageVisit() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("signup"));
    }

    @Test
    public void CheckInputTypes() {
        Assert.assertEquals(signUpPage.getEmailFieldTypeValue(), "email");
        Assert.assertEquals(signUpPage.getPasswordFieldTypeValue(), "password");
        Assert.assertEquals(signUpPage.getConfirmPasswordFieldTypeValue(), "password");
    }

    @Test
    public void ExistingUserErrorMessageCheck() {
        String name = "Test Test";
        String password = "123654";
        String confirmPassword = "123654";
        signUpPage.signUpUser(name, ADMINEMAIL, password, confirmPassword);
        Assert.assertEquals(signUpPage.getSignUpErrorMessageText(), "E-mail already exists");
    }

    @Test
    public void ValidInputSignUp() {
        String name = "Sasa Rajkov";
        String email = "testemail@testing.rs";
        String password = "notAPassword_123";
        String confirmPassword = "notAPassword_123";
        signUpPage.signUpUser(name, email, password, confirmPassword);
        Assert.assertEquals(signUpPage.getSuccessfulSignUpMessage(), "IMPORTANT: Verify your account");
    }

    @AfterMethod
    public void afterMethod(Method method) {
        try {
            if (driver.findElement(By.className("btnLogout")).isDisplayed()) {
                homePage.logoutUser();
            }
        } catch (Exception NoSuchElementException) {
            System.out.println(method.getName() + " afterMethod - logout:\nUser not signed in/no logout button found.");
        }
    }
}
