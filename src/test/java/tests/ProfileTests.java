package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class ProfileTests extends BaseTest {

    private SignUpPage signUpPage;
    private HomePage homePage;
    private MyProfilePage myProfilePage;

    private Faker faker;

    private String name;
    private String email;
    private String password;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        signUpPage = new SignUpPage(driver, driverWait);
        homePage = new HomePage(driver, driverWait);
        myProfilePage = new MyProfilePage(driver, driverWait);
        faker = new Faker();
    }

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        name = faker.name().fullName();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        String confirmPassword = password;

        landingPage.openSignUpPage();
        signUpPage.signUpUser(name, email, password, confirmPassword);
        signUpPage.closeSignUpMessage();
        driverWait.until(ExpectedConditions.urlToBe(baseURL + "/home"));
    }

    @Test
    public void test1EditProfile() {
        homePage.openProfile();
        String name = faker.name().fullName();
        String phone = faker.phoneNumber().phoneNumber();
        String country = faker.country().name();
        String urlTwitter = "https://" + faker.internet().url();
        String urlGitHub = "https://" + faker.internet().url();
        myProfilePage.editProfile(name, phone, 4, country, urlTwitter, urlGitHub);
        String city = myProfilePage.getCurrentCity();
        myProfilePage.saveProfileEdits();
        Assert.assertTrue(myProfilePage.getSuccessfulSaveEditMessage().contains("Profile saved successfuly"));

        driver.navigate().refresh();
        driverWait.until(ExpectedConditions.textToBePresentInElementValue(By.id("email"), email));

        Assert.assertEquals(myProfilePage.getCurrentName(), name);
        Assert.assertEquals(myProfilePage.getCurrentPhone(), phone);
        Assert.assertEquals(myProfilePage.getCurrentCity(), city);
        Assert.assertEquals(myProfilePage.getCurrentCountry(), country);
        Assert.assertEquals(myProfilePage.getCurrentTwitterURL(), urlTwitter);
        Assert.assertEquals(myProfilePage.getCurrentGitHubURL(), urlGitHub);
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
