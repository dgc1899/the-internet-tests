package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SecureArea {
    @FindBy(css = "#flash")
    private WebElement txtLoggedInBanner;

    @FindBy(css = ".close")
    private WebElement btnCloseLoggedInBanner;

    @FindBy(css = "h2")
    private WebElement txtSecureAreaHeader;

    @FindBy(css = ".subheader")
    private WebElement txtSecureAreaSubheader;

    @FindBy(css = ".button")
    private WebElement btnLogout;

    public SecureArea(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void verifyLoggedInBanner() {
        Assert.assertEquals(txtLoggedInBanner.isDisplayed(), true);
        Assert.assertEquals(txtLoggedInBanner.getText().replace("×",""),
                "You logged into a secure area!\n");
        Assert.assertEquals(btnCloseLoggedInBanner.isDisplayed(), true);
    }

    public void verifySecureAreaHeader() {
        Assert.assertEquals(txtSecureAreaHeader.isDisplayed(), true);
        Assert.assertEquals(txtSecureAreaHeader.getText(), "Secure Area");
    }

    public void verifySecureAreaSubheader() {
        Assert.assertEquals(txtSecureAreaSubheader.isDisplayed(), true);
        Assert.assertEquals(txtSecureAreaSubheader.getText(), "Welcome to the Secure Area." +
                " When you are done click logout below.");
    }

    public void verifyLogoutButton() {
        Assert.assertEquals(btnLogout.isDisplayed(), true);
        Assert.assertEquals(btnLogout.getText(), "Logout");
        Assert.assertEquals(btnLogout.isEnabled(), true);
    }

    public void verifyLogoutScreen() {
        verifyLoggedInBanner();
        verifySecureAreaHeader();
        verifySecureAreaSubheader();
        verifyLogoutButton();
    }
}
