package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AdminCitiesPage;
import pages.HomePage;
import pages.LoginPage;

public class AdminCitiesTests extends BaseTest {

    private LoginPage loginPage;

    private HomePage homePage;

    private AdminCitiesPage adminCitiesPage;

    private Faker faker;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, driverWait);
        homePage = new HomePage(driver, driverWait);
        adminCitiesPage = new AdminCitiesPage(driver, driverWait);
        faker = new Faker();
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        landingPage.openLoginPage();
    }

    @Test
    public void test1VisitAdminCitiesPageAndListCities(){
        loginPage.login(VALIDEMAIL, VALIDPASSWORD);
        homePage.openCities();
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/admin/cities"));
        Assert.assertTrue(adminCitiesPage.logoutToolbarOptionDisplayed());
    }

    @Test
    public void test2CreateNewCity(){
        loginPage.login(VALIDEMAIL, VALIDPASSWORD);
        homePage.openCities();
        String cityName = faker.address().cityName();
        adminCitiesPage.createNewCity(cityName);
        Assert.assertTrue(adminCitiesPage.getSuccessfulMessageText().contains("Saved successfully"));
    }

    @AfterMethod
    public void afterMethod() {
        try {
            if (driver.findElement(By.className("btnLogout")).isDisplayed()) {
                homePage.logoutUser();
            }
        } catch (Exception NoSuchElementException) {
            System.out.println("User not signed in/no logout button found.");
        }
    }
}
