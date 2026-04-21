package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DynamicLoadingTests extends BaseTest {
    @Test(groups = {"functional"})
    public void testDynamicLoadingHiddenElement() {
        driver.get(baseUrl + "/dynamic_loading/1");

        Assert.assertEquals(dynamicLoading.getStartButtonVisibility(), true);
        Assert.assertEquals(dynamicLoading.getStartButtonEnabled(), true);
        Assert.assertEquals(dynamicLoading.getHelloWorldTextVisibility(), false);

        dynamicLoading.clickStartButton();
        dynamicLoading.waitForLoadingBarToBeDisplayed();
        Assert.assertEquals(dynamicLoading.getLoadingBarVisibility(), true);
        Assert.assertEquals(dynamicLoading.getStartButtonVisibility(), false);
        Assert.assertEquals(dynamicLoading.getHelloWorldTextVisibility(), false);

        dynamicLoading.waitForHelloWorldToBeDisplayed();
        Assert.assertEquals(dynamicLoading.getLoadingBarVisibility(), false);
        Assert.assertEquals(dynamicLoading.getStartButtonVisibility(), false);
        Assert.assertEquals(dynamicLoading.getHelloWorldTextVisibility(), true);
    }

    @Test(groups = {"functional"})
    public void testDynamicLoadingNonExistingElement() {
        driver.get(baseUrl + "/dynamic_loading/2");

        Assert.assertEquals(dynamicLoading.getStartButtonVisibility(), true);
        Assert.assertEquals(dynamicLoading.getStartButtonEnabled(), true);
        Assert.assertEquals(dynamicLoading.getHelloWorldTextExistence(), false);
        Assert.assertEquals(dynamicLoading.getLoadingBarExistence(), false);

        dynamicLoading.clickStartButton();
        dynamicLoading.waitForLoadingBarToBeDisplayed();
        Assert.assertEquals(dynamicLoading.getStartButtonVisibility(), false);
        Assert.assertEquals(dynamicLoading.getHelloWorldTextExistence(), false);
        Assert.assertEquals(dynamicLoading.getLoadingBarExistence(), true);
        Assert.assertEquals(dynamicLoading.getLoadingBarVisibility(), true);

        dynamicLoading.waitForHelloWorldToBeDisplayed();
        Assert.assertEquals(dynamicLoading.getStartButtonVisibility(), false);
        Assert.assertEquals(dynamicLoading.getHelloWorldTextExistence(), true);
        Assert.assertEquals(dynamicLoading.getHelloWorldTextVisibility(), true);
    }
}
