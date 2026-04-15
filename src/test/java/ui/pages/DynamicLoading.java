package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DynamicLoading extends BasePage {
    @FindBy(css = "button")
    private WebElement btnStart;

    @FindBy(css = "#loading")
    private WebElement txtLoading;

    @FindBy(css = "img[src='/img/ajax-loader.gif']")
    private WebElement loadingBar;

    @FindBy(css = "div#finish>h4")
    private WebElement txtHelloWorld;

    public DynamicLoading(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean getStartButtonVisibility() {
        return btnStart.isDisplayed();
    }

    public boolean getStartButtonEnabled() {
        return btnStart.isEnabled();
    }

    public void clickStartButton() {
        customActions.clickElement(btnStart);
    }

    public boolean getLoadingBarVisibility() {
        return loadingBar.isDisplayed() && txtLoading.isDisplayed();
    }

    public void waitForLoadingBarToBeDisplayed() {
        customActions.waitForElementToBeDisplayed(loadingBar);
        customActions.waitForElementToBeDisplayed(txtLoading);
    }

    public boolean getHelloWorldTextVisibility() {
        return txtHelloWorld.isDisplayed();
    }

    public void waitForHelloWorldToBeDisplayed() {
        customActions.waitForElementToBeDisplayed(txtHelloWorld);
    }

    public boolean getHelloWorldTextExistence() {
        return driver.findElements(By.cssSelector("div#finish>h4")).isEmpty() ? false : true;
    }

    public boolean getLoadingBarExistence() {
        return driver.findElements(By.cssSelector("img[src='/img/ajax-loader.gif']")).isEmpty() ? false : true;
    }
}
