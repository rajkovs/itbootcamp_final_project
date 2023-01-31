package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    private LandingPage landingPage;
    private LoginPage loginPage;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        landingPage = new LandingPage(driver, driverWait);
        loginPage = new LoginPage(driver, driverWait);
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        landingPage.openLoginPage();
    }

    @Test
    public void loginPageVisitVerification() {
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}
