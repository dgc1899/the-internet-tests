package ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ui.pages.BasePage;
import ui.pages.Frames;
import ui.pages.Login;
import ui.pages.SecureArea;

import java.util.HashMap;

public class BaseTest {
    WebDriver driver;

    BasePage basePage;
    Login login;
    SecureArea secureArea;
    Frames frames;

    LoginTests loginTests;
    FramesTests framesTests;

    String baseUrl = "https://the-internet.herokuapp.com";

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        HashMap<String, Boolean> prefs = new HashMap<>();
        prefs.put( "profile.password_manager_leak_detection", false);
        prefs.put( "credentials_enable_service", false);
        prefs.put( "profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.get(baseUrl);

        basePage = new BasePage(driver);
        login = new Login(driver);
        secureArea = new SecureArea(driver);
        frames =  new Frames(driver);

        loginTests = new LoginTests();
        framesTests = new FramesTests();
    }

    @AfterMethod
    public void teardown() {
        driver.close();
        driver.quit();
    }
}
