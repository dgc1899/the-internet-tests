package ui.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Alerts extends BasePage {
    @FindBy(css = "button[onclick='jsAlert()']")
    private WebElement btnJSAlert;

    @FindBy(css = "button[onclick='jsConfirm()']")
    private WebElement btnJSConfirm;

    @FindBy(css = "button[onclick='jsPrompt()']")
    private WebElement btnJSPrompt;

    @FindBy(css = "h4")
    private WebElement subheaderResult;

    @FindBy(css = "#result")
    private WebElement txtResult;

    Alert alert;

    public Alerts(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickBtnJSAlert() {
        customActions.clickElement(btnJSAlert);
    }

    public void clickBtnJSConfirm() {
        customActions.clickElement(btnJSConfirm);
    }

    public void clickBtnJSPrompt() {
        customActions.clickElement(btnJSPrompt);
    }

    public String getAlertContents() {
        alert =customActions.switchToAlert();
        return alert.getText();
    }

    public void sendTextToAlert(String text) {
        alert.sendKeys(text);
    }

    public void clickOKJSAlert() {
        customActions.acceptAlert();
    }

    public void clickCancelJSAlert() {
        customActions.cancelAlert();
    }

    public String getResult() {
        return subheaderResult.getText() + txtResult.getText();
    }
}
