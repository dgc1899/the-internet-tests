package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Hovers extends BasePage {
    @FindBy(css = "div.figure")
    private List<WebElement> avatars;

    @FindBy(css = "div.figcaption")
    private List<WebElement> captions;


    public Hovers(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void hoverOverPortrait(int index) {
        customActions.hoverOverElement(avatars.get(index));
    }

    public boolean isCaptionVisible(int index) {
        return captions.get(index).isDisplayed();
    }
}
