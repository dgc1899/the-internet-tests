package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import ui.utils.CustomActions;

public class Frames extends BasePage {
    @FindBy(css = "frame[name='frame-top']")
    private WebElement frameTop;

    @FindBy(css = "frame[name='frame-bottom']")
    private WebElement frameBottom;

    @FindBy(css = "frame[name='frame-left']")
    private WebElement frameLeft;

    @FindBy(css = "frame[name='frame-middle']")
    private WebElement frameMiddle;

    @FindBy(css = "frame[name='frame-right']")
    private WebElement frameRight;

    public Frames(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToTopFrame() {
        driver.switchTo().frame(frameTop);
    }

    public void navigateToBottomFrame() {
        driver.switchTo().frame(frameBottom);
    }

    public void navigateToLeftFrame() {
        navigateToTopFrame();
        driver.switchTo().frame(frameLeft);
    }

    public void navigateToMiddleFrame() {
        navigateToTopFrame();
        driver.switchTo().frame(frameMiddle);
    }

    public void navigateToRightFrame() {
        navigateToTopFrame();
        driver.switchTo().frame(frameRight);
    }

    public void navigateToRootPage() {
        customActions.goToDefaultContent();
    }

    public String getBodyText() {
        WebElement currentBody = driver.findElement(By.tagName("body"));
        String text = customActions.getTextFromElement(currentBody);

        return text;
    }

}
