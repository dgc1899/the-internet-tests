package ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ui.pages.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

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

    String baseUrl = "";
    Properties prop = new Properties();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("configuration.properties")) {
            prop.load(input);
            baseUrl = prop.get("baseUrl").toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        initializeDriver();

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

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        driver.close();
        driver.quit();
    }

    private void initializeDriver() {
        String browser = prop.get("browser").toString().toLowerCase().trim();
        switch (browser) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                HashMap<String, Boolean> prefs = new HashMap<>();
                prefs.put( "profile.password_manager_leak_detection", false);
                prefs.put( "credentials_enable_service", false);
                prefs.put( "profile.password_manager_enabled", false);

                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
                return;
            case "firefox":
                driver = new FirefoxDriver();
                return;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }
}
