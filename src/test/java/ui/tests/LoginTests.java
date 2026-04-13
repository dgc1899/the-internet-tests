package ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import ui.pages.Login;
import ui.pages.SecureArea;

import java.util.HashMap;

public class LoginTests {

    @Test
    public void testLogin() {
        String username = "tomsmith";
        String password = "SuperSecretPassword!";

        WebDriver driver;
        ChromeOptions options = new ChromeOptions();

        HashMap<String, Boolean> prefs = new HashMap<>();
        prefs.put( "profile.password_manager_leak_detection", false);
        prefs.put( "credentials_enable_service", false);
        prefs.put( "profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);

        driver.get("https://the-internet.herokuapp.com/login");
        Login login = new Login(driver);
        SecureArea secureArea = new SecureArea(driver);

        login.verifyLoginHeader();
        login.verifyLoginSubheader();
        login.verifyTxtUsername();
        login.verifyTxbxUsername("");
        login.verifyTxtPassword();
        login.verifyTxbxPassword("");

        login.enterUsername(username);
        login.enterPassword("SuperSecretPassword!");

        login.verifyTxbxPassword(password);
        login.verifyTxbxUsername(username);

        login.clickLogin();

        secureArea.verifyLogoutScreen();

        driver.close();
        driver.quit();
    }
}
