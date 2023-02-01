package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SignUpPage;

public class SignUpTests extends BaseTest{

    private SignUpPage signUpPage;

    @BeforeClass
    public void beforeClass(){
        super.beforeClass();
        signUpPage = new SignUpPage(driver, driverWait);
    }

    @Test
    public void test1SignUpPageVisit(){
        landingPage.openSignUpPage();
        Assert.assertTrue(driver.getCurrentUrl().endsWith("signup"));
    }

    @Test
    public void test2CheckInputTypes(){
        landingPage.openSignUpPage();
        Assert.assertEquals(signUpPage.getEmailFieldTypeValue(), "email");
        Assert.assertEquals(signUpPage.getPasswordFieldTypeValue(), "password");
        Assert.assertEquals(signUpPage.getConfirmPasswordFieldTypeValue(), "password");
    }
}
