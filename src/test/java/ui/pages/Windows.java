package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;

public class Windows extends BasePage {
    @FindBy(css = "a[href='/windows/new']")
    private WebElement btnOpenWindow;

    @FindBy(css = "h3")
    private WebElement txtNewWindow;

    @FindBy(css = "div.example>h3")
    private WebElement txtOpeningNewWindow;

    public Windows(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickBtnOpenWindow() {
        customActions.clickElement(btnOpenWindow);
    }

    public void switchToNewWindowTab() {
        customActions.switchToTab(1);
    }

    public void switchToOriginalTab() {
        driver.close();
        customActions.switchToTab(0);
        Object[] handles = customActions.getWindowHandles();
        System.out.println(handles);
    }

    public Object[] getOpenTabs() {
        Object[] windowHandles = customActions.getWindowHandles();
        return windowHandles;
    }

    public String getNewTabTitle() {
        return customActions.getTextFromElement(txtNewWindow);
    }

    public String getOpeningNewWindowTitle() {
        return customActions.getTextFromElement(txtOpeningNewWindow);
    }
}
