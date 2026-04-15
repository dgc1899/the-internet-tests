package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HoversTests extends BaseTest {

    @Test
    public void testHover() {
        driver.get(baseUrl + "/hovers");

        hovers.hoverOverPortrait(0);

        Assert.assertEquals(hovers.isCaptionVisible(0), true);
        Assert.assertEquals(hovers.isCaptionVisible(1), false);
        Assert.assertEquals(hovers.isCaptionVisible(2), false);
    }
}
