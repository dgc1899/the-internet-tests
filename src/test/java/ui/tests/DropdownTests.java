package ui.tests;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropdownTests extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void testSelectOptions() {
        driver.get(baseUrl + "/dropdown");

        dropdown.setUpSelect();
        Assert.assertEquals(dropdown.getSelectedOption().getText(), "Please select an option");

        dropdown.setSelectedOption("1");
        Assert.assertEquals(dropdown.getSelectedOption().getText(), "Option 1");

        dropdown.setSelectedOption("2");
        Assert.assertEquals(dropdown.getSelectedOption().getText(), "Option 2");
    }
}
