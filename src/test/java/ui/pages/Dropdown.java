package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Dropdown extends BasePage {
    @FindBy(css = "#dropdown")
    private WebElement selectDropdown;

    Select select;

    public Dropdown(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setUpSelect() {
        select = new Select(selectDropdown);
    }

    public WebElement getSelectedOption() {
        return select.getFirstSelectedOption();
    }

    public void setSelectedOption(String optionValue) {
        select.selectByValue(optionValue);
    }
}
