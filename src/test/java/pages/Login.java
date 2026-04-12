package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Login {
    @FindBy(css = "h2")
    private WebElement txtLoginHeader;

    @FindBy(css = ".subheader")
    private WebElement txtLoginSubheader;

    @FindBy(css = "label[for='username']")
    private WebElement txtUsername;

    @FindBy(css = "#username")
    private WebElement txbxUsername;

    @FindBy(css = "label[for='password']")
    private WebElement txtPassword;

    @FindBy(css = "#password")
    private WebElement txbxPassword;

    @FindBy(css = "button[type='submit']")
    private WebElement btnLogin;

    WebDriver driver;

    public Login(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void verifyLoginHeader() {
        String expectedValue = "Login Page";
        Assert.assertEquals(txtLoginHeader.getText(), expectedValue);
    }

    public void verifyLoginSubheader() {
        String expectedValue = "This is where you can log into the secure area. " +
                "Enter tomsmith for the username and SuperSecretPassword! for the password. " +
                "If the information is wrong you should see error messages.";
        Assert.assertEquals(txtLoginSubheader.getText(), expectedValue);
    }

    public void verifyTxtUsername() {
        String expectedValue = "Username";
        Assert.assertEquals(txtUsername.getText(), expectedValue);
    }

    public void verifyTxbxUsername(String text) {
        Assert.assertEquals(txbxUsername.getAttribute("value"), text);
    }

    public void verifyTxtPassword() {
        String expectedValue = "Password";
        Assert.assertEquals(txtPassword.getText(), expectedValue);
    }

    public void verifyTxbxPassword(String text) {
        Assert.assertEquals(txbxPassword.getAttribute("value"), text);
        Assert.assertEquals(txbxPassword.getAttribute("type"), "password");
    }

    public void enterUsername(String username) {
        txbxUsername.sendKeys(username);
    }

    public void enterPassword(String password) {
        txbxPassword.sendKeys(password);

    }

    public void clickLogin() {
        btnLogin.click();
    }
}
