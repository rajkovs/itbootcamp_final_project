package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTests extends BaseTest{

    @Test
    public void test1signUpPageVisit(){
        landingPage.openSignUpPage();
        Assert.assertTrue(driver.getCurrentUrl().endsWith("signup"));
    }
}
