package tests.different;

import helper.ConfigProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import tests.BaseTestClass;

public class OtherBlockLiteCardTest extends BaseTestClass {
    private static final Logger LOG = Logger.getLogger(OtherBlockLiteCardTest.class);

    private final String SUCCESS_ALERT = "Thank you for subscribing to our newsletter";

    @Test
    public void checkSearchInputTest() {
        LOG.info("Search any product");
        String product = mainPage.searchProduct(ConfigProperties.getProperty("product1"));
        Assert.assertTrue(mainPage.isSearchContentProduct(product));
    }

    @Test(dependsOnMethods = "checkSearchInputTest")
    public void checkHomePageButtonTest() {
        LOG.info("Click home button");
        mainPage.getHomeButton().click();

        LOG.info("Check to back on main page");
        Assert.assertTrue(mainPage.isDisplayedMainPage());
    }

    @Test(dependsOnMethods = "checkHomePageButtonTest")
    public void checkSubscribeTest() {
        mainPage.subscribe(ConfigProperties.getProperty("wrongLogin"));
        Assert.assertEquals(mainPage.getSubscribeAlertSuccess(), SUCCESS_ALERT);
    }
}
