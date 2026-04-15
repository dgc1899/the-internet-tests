package ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ui.pages.*;

import java.util.HashMap;

public class BaseTest {
    WebDriver driver;

    BasePage basePage;
    Login login;
    SecureArea secureArea;
    Frames frames;
    Alerts alerts;
    Windows windows;
    Dropdown dropdown;
    DynamicLoading dynamicLoading;
    Hovers hovers;

    LoginTests loginTests;
    FramesTests framesTests;
    AlertsTests alertsTests;
    WindowsTests windowsTests;
    DropdownTests dropdownTests;
    DynamicLoadingTests dynamicLoadingTests;
    HoversTests hoversTests;

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
        alerts = new Alerts(driver);
        windows = new Windows(driver);
        dropdown = new Dropdown(driver);
        dynamicLoading = new DynamicLoading(driver);
        hovers = new Hovers(driver);

        loginTests = new LoginTests();
        framesTests = new FramesTests();
        alertsTests = new AlertsTests();
        windowsTests = new WindowsTests();
        dropdownTests = new DropdownTests();
        dynamicLoadingTests = new DynamicLoadingTests();
        hoversTests = new HoversTests();
    }

    @AfterMethod
    public void teardown() {
        driver.close();
        driver.quit();
    }
}
