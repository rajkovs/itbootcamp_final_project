package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    private LandingPage landingPage;
    private LoginPage loginPage;

    private Faker faker;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        landingPage = new LandingPage(driver, driverWait);
        loginPage = new LoginPage(driver, driverWait);
        faker = new Faker();
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        landingPage.openLoginPage();
    }

    @Test
    public void test1LoginPageVisitVerification() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void test2InputTypesCheck() {
        Assert.assertEquals(loginPage.getEmailFieldTypeValue(), "email");
        Assert.assertEquals(loginPage.getPasswordFieldTypeValue(),"password");
    }

    @Test
    public void test3NonExistingUserErrorMessageCheck() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        loginPage.login(email, password);
        String expectedErrorMessage = "User does not exists";
        String actualErrorMessage = loginPage.getLoginErrorMessageText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void test4WrongPasswordErrorMessageCheck() {
        String email = "admin@admin.com";
        String password = faker.internet().password();
        loginPage.login(email, password);

        String expectedErrorMessage = "Wrong password";
        String actualErrorMessage = loginPage.getLoginErrorMessageText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void test5ValidCredentialsLogin() {
        String email = "admin@admin.com";
        String password = "12345";
        loginPage.login(email, password);
        driverWait.until(ExpectedConditions.urlContains("/home"));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/home"));
    }
}
