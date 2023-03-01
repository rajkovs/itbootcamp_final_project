package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;

    private HomePage homePage;

    private Faker faker;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, driverWait);
        homePage = new HomePage(driver, driverWait);
        faker = new Faker();
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        landingPage.openLoginPage();
    }

    @Test
    public void LoginPageVisitVerification() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void InputTypesCheck() {
        Assert.assertEquals(loginPage.getEmailFieldTypeValue(), "email");
        Assert.assertEquals(loginPage.getPasswordFieldTypeValue(), "password");
    }

    @Test
    public void NonExistingUserErrorMessageCheck() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        loginPage.login(email, password);
        String expectedErrorMessage = "User does not exists";
        String actualErrorMessage = loginPage.getLoginErrorMessageText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void WrongPasswordErrorMessageCheck() {
        String password = faker.internet().password();
        loginPage.login(ADMINEMAIL, password);

        String expectedErrorMessage = "Wrong password";
        String actualErrorMessage = loginPage.getLoginErrorMessageText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void ValidCredentialsLogin() {
        loginPage.login(ADMINEMAIL, ADMINPASSWORD);
        driverWait.until(ExpectedConditions.urlContains("/home"));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/home"));
    }

    @Test
    public void Logout() {
        loginPage.login(ADMINEMAIL, ADMINPASSWORD);

        Assert.assertTrue(homePage.logoutOptionDisplayed());
        homePage.logoutUser();
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));

        driver.get(baseURL + "/home");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @AfterMethod
    public void afterMethod(Method method) {
        try {
            if (homePage.logoutOptionDisplayed()) {
                homePage.logoutUser();
            }
        } catch (Exception NoSuchElementException) {
            System.out.println(method.getName() + " afterMethod - logout:\nUser not signed in/no logout button found.");
        }
    }
}
