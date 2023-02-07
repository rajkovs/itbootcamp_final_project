package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.LandingPage;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;

    protected WebDriverWait driverWait;

    protected LandingPage landingPage;

    protected final String baseURL = "https://vue-demo.daniel-avellaneda.com";

    protected final String ADMINEMAIL = "admin@admin.com";

    protected final String ADMINPASSWORD = "12345";

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "D:\\ChromeWebDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        landingPage = new LandingPage(driver, driverWait);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseURL);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
