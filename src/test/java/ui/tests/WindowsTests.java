package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WindowsTests extends BaseTest {

    @Test
    public void testOpenNewTab() {
        driver.get(baseUrl + "/windows");

        windows.clickBtnOpenWindow();
        windows.switchToNewWindowTab();
        Object[] openTabs = windows.getOpenTabs();


        Assert.assertEquals(openTabs.length, 2);
        Assert.assertEquals(driver.getTitle(), "New Window");
        Assert.assertEquals(windows.getNewTabTitle(), "New Window");
    }

    @Test
    public void testOpenAndCloseNewTab() {
        driver.get(baseUrl + "/windows");

        windows.clickBtnOpenWindow();
        windows.switchToNewWindowTab();
        Object[] openTabs = windows.getOpenTabs();


        Assert.assertEquals(openTabs.length, 2);
        Assert.assertEquals(driver.getTitle(), "New Window");
        Assert.assertEquals(windows.getNewTabTitle(), "New Window");

        windows.switchToOriginalTab();
        openTabs = windows.getOpenTabs();

        Assert.assertEquals(openTabs.length, 1);
        Assert.assertEquals(driver.getTitle(), "The Internet");
        Assert.assertEquals(windows.getOpeningNewWindowTitle(), "Opening a new window");
    }
}
