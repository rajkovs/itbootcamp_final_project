package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AdminCitiesPage;
import pages.HomePage;
import pages.LoginPage;

import java.lang.reflect.Method;

public class AdminCitiesTests extends BaseTest {

    private LoginPage loginPage;

    private HomePage homePage;

    private AdminCitiesPage adminCitiesPage;

    private Faker faker;

    private String cityName;

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
        loginPage.login(ADMINEMAIL, ADMINPASSWORD);
        homePage.openCities();
    }


    @Test
    public void VisitAdminCitiesPageAndListCities() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/admin/cities"));
        Assert.assertTrue(adminCitiesPage.logoutToolbarOptionDisplayed());
    }

    @Test
    public void CreateNewCity() {
        cityName = faker.address().cityName();

        adminCitiesPage.createNewCity(cityName);
        Assert.assertTrue(adminCitiesPage.getSuccessfulMessageText().contains("Saved successfully"));
    }

    @Test(dependsOnMethods = {"CreateNewCity"})
    public void SearchCities() {
        adminCitiesPage.searchCities(cityName);

        String firstResultCityName = adminCitiesPage.getSearchResultsFirstCityName();
        Assert.assertEquals(firstResultCityName, cityName);
    }

    @Test(dependsOnMethods = {"SearchCities"})
    public void EditCity() {
        adminCitiesPage.editCityName(cityName, cityName + " edited");
    }

    @Test(dependsOnMethods = {"EditCity"})
    public void DeleteCity() {
        adminCitiesPage.searchCities(cityName);
        adminCitiesPage.waitUntilOneSearchResult();

        String firstResultCityName = adminCitiesPage.getSearchResultsFirstCityName();
        Assert.assertTrue(firstResultCityName.contains(cityName));
        adminCitiesPage.deleteCity(cityName);
        Assert.assertTrue(adminCitiesPage.getSuccessfulDeleteMessage().contains("Deleted successfully"));
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
