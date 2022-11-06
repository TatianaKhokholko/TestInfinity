package tests.headerblock;

import helper.ConfigProperties;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import tests.BaseTestClass;

public class HeaderLiteCartTests extends BaseTestClass {
    private static final Logger LOG = Logger.getLogger(HeaderLiteCartTests.class);

    @Test(priority = 1)
    public void checkHeaderPageTest() {
        LOG.info("Check the header for page and logo are load");
        Assert.assertTrue(mainPage.getHeaderPage().isDisplayed());
        Assert.assertTrue(mainPage.getLogoType().isDisplayed());
    }

    @Test(dependsOnMethods = "checkHeaderPageTest", alwaysRun= true)
    public void checkOpenCloseRegionSettingsTest() {
        LOG.info("Open region settings modal window");
        mainPage.openRegionSettings();
        Assert.assertTrue(mainPage.getCloseRegionalSettingsModalWin().isDisplayed());

        LOG.info("Close region settings modal window");
        mainPage.closeRegionSettings();
        Assert.assertTrue(mainPage.isDisplayedMainPage());
    }

    @Test(dependsOnMethods = "checkOpenCloseRegionSettingsTest", alwaysRun= true)
    public void checkChangeRegionSettingsTest() {
        LOG.info("Save default value");
        String currency = mainPage.getRegionCurrencyBlock().getText();
        String newCurrency = ConfigProperties.getProperty("currency_value");

        LOG.info("Change and save new currency");
        mainPage.saveCurrencyRegionSettings(newCurrency);
        Assert.assertNotEquals(currency, newCurrency, "Value are not the same");
    }
}
