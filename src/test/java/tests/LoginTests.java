package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
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
    public void test1loginPageVisitVerification() {
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test
    public void test2inputTypesCheck() {
        Assert.assertTrue(loginPage.getEmailFieldTypeValue().equals("email"));
        Assert.assertTrue(loginPage.getPasswordFieldTypeValue().equals("password"));
    }

    @Test
    public void test3nonExistingUserErrorMessageCheck() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        loginPage.login(email, password);
        String expectedErrorMessage = "User does not exists";
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li")).getText();
        Assert.assertTrue(expectedErrorMessage.equals(actualErrorMessage));
    }
}
