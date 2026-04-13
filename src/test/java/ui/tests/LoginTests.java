package ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import ui.pages.Login;
import ui.pages.SecureArea;

import java.util.HashMap;

public class LoginTests extends BaseTest {

    @Test
    public void testLogin() {
        driver.get(baseUrl + "/login");
        String username = "tomsmith";
        String password = "SuperSecretPassword!";

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
    }
}
