package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthRoutesTests extends BaseTest {
    @Test
    public void HomeVisitForbiddenIfNotAuthenticated() {
        driver.get(baseURL + "/home");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void ProfileVisitForbiddenIfNotAuthenticated() {
        driver.get(baseURL + "/profile");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void AdminCitiesURLVisitForbiddenIfNotAuthenticated() {
        driver.get(baseURL + "/admin/cities");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void AdminUsersURLVisitForbiddenIfNotAuthenticated() {
        driver.get(baseURL + "/admin/users");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }
}
