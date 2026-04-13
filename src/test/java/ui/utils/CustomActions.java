package ui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class CustomActions {

    WebDriver driver;
    WebDriverWait wait;

    public CustomActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS));
    }

    public void clickElement(WebElement element) {
        try {

            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
        }
        catch (Exception ex) {
            System.out.println("Error while clicking element");
        }
    }

    public void enterText(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(text);
        }
        catch (Exception ex) {
            System.out.println("Error while entering text");
        }
    }

    public String getTextFromElement(WebElement element) {
        String text = "";
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
             text = element.getText();

        }
        catch (Exception ex) {
            System.out.println("Error while getting text");
        }
        return text;
    }

    public void scrollToElement(WebElement element) {
        new Actions(driver)
                .scrollToElement(element)
                .perform();
    }

    public void goToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}
