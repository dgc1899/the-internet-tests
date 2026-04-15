package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FramesTests extends BaseTest {

    String framesUrl = baseUrl + "/nested_frames";

    @Test
    public void testBottomFrameNavigation() {
        driver.get(framesUrl);

        frames.navigateToBottomFrame();
        String actual = frames.getBodyText();
        Assert.assertEquals(actual, "BOTTOM");
    }

    @Test
    public void testNavigateLeftMiddleRightBottom() {
        String actualBodyText;
        driver.get(framesUrl);

        frames.navigateToLeftFrame();
        actualBodyText = frames.getBodyText();
        Assert.assertEquals(actualBodyText, "LEFT");

        frames.navigateToRootPage();
        frames.navigateToMiddleFrame();
        actualBodyText = frames.getBodyText();
        Assert.assertEquals(actualBodyText, "MIDDLE");

        frames.navigateToRootPage();
        frames.navigateToRightFrame();
        actualBodyText = frames.getBodyText();
        Assert.assertEquals(actualBodyText, "RIGHT");

        frames.navigateToRootPage();
        frames.navigateToBottomFrame();
        actualBodyText = frames.getBodyText();
        Assert.assertEquals(actualBodyText, "BOTTOM");

    }
}
