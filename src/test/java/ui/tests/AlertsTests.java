package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertsTests extends BaseTest {

    @Test
    public void testJSAlert() {
        driver.get(baseUrl + "/javascript_alerts");

        alerts.clickBtnJSAlert();
        String contents = alerts.getAlertContents();
        Assert.assertEquals(contents, "I am a JS Alert");

        alerts.clickOKJSAlert();
        Assert.assertEquals(alerts.getResult(), "Result:You successfully clicked an alert");
    }

    @Test
    public void testJSConfirmAlertAccept() {
        driver.get(baseUrl + "/javascript_alerts");

        alerts.clickBtnJSConfirm();
        String contents = alerts.getAlertContents();
        Assert.assertEquals(contents, "I am a JS Confirm");

        alerts.clickOKJSAlert();
        Assert.assertEquals(alerts.getResult(), "Result:You clicked: Ok");
    }

    @Test
    public void testJSConfirmAlertCancel() {
        driver.get(baseUrl + "/javascript_alerts");

        alerts.clickBtnJSConfirm();
        String contents = alerts.getAlertContents();
        Assert.assertEquals(contents, "I am a JS Confirm");

        alerts.clickCancelJSAlert();
        Assert.assertEquals(alerts.getResult(), "Result:You clicked: Cancel");
    }

    @Test
    public void testJSPromptAlert() {
        driver.get(baseUrl + "/javascript_alerts");

        alerts.clickBtnJSPrompt();
        String contents = alerts.getAlertContents();
        Assert.assertEquals(contents, "I am a JS prompt");

        alerts.sendTextToAlert("Hello");
        alerts.clickOKJSAlert();

        Assert.assertEquals(alerts.getResult(), "Result:You entered: Hello");
    }
}
