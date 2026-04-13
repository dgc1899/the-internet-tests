package ui.pages;

import org.openqa.selenium.WebDriver;
import ui.utils.CustomActions;

public class BasePage {

    WebDriver driver;
    CustomActions customActions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        customActions = new CustomActions(driver);
    }
}
