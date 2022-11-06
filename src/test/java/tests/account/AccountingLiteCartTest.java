package tests.account;

import helper.ConfigProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import tests.BaseTestClass;

public class AccountingLiteCartTest extends BaseTestClass {
    private static final Logger LOG = Logger.getLogger(AccountingLiteCartTest.class);
    private final String ERROR_MESSAGE = "The email does not exist in our database";
    private final String SUCCESS_MESSAGE = "You are now logged in as John Doe.";

    @Test(priority = 1)
    public void checkOpenPageTest() {
        LOG.info("Check the Main page open and load");
        Assert.assertTrue(mainPage.isDisplayedMainPage());
    }

    @Test(dependsOnMethods = "checkOpenPageTest", alwaysRun= true)
    public void checkLoginTest() {
        LOG.info("Login as default user");
        mainPage.loginWithDefault();
        Assert.assertEquals(mainPage.getAlertSuccessMessageAfterLogin(), SUCCESS_MESSAGE);
    }

    @Test(dependsOnMethods = "checkLoginTest")
    public void checkLogoutTest() {
        LOG.info("Click on Logout button");
        mainPage.clickLogoutButton();
        Assert.assertTrue(mainPage.getSignInButton().isEnabled());
    }

    /**
     * The negative scenario
     */
    @Test(dependsOnMethods = "checkLogoutTest", alwaysRun= true)
    public void checkLoginWithWrongDefaultTest() {
        LOG.info("Login as custom user with wrong data");
        mainPage.loginApp(ConfigProperties.getProperty("wrongLogin"), ConfigProperties.getProperty("wrongPassword"));
        Assert.assertEquals(mainPage.getAlertDangerMessageAfterLogout(), ERROR_MESSAGE);
    }
}
