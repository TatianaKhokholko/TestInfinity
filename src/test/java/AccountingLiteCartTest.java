import helper.ConfigProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import pages.MainPage;

public class AccountingLiteCartTest extends BaseTestClass{
    private static final Logger LOG = Logger.getLogger(AccountingLiteCartTest.class);
    private String ERROR_MESSAGE = "The email does not exist in our database";
    private String SUCCESS_MESSAGE = "You are now logged in as John Doe.";

    @Test(priority = 1)
    public void checkOpenPageTest() {
        LOG.info("Check the Main page open and load");
        Assert.assertTrue(mainPage.isDisplayedMainPage());
    }

    @Test(dependsOnMethods = "checkOpenPageTest", alwaysRun= true)
    public void checkLoginTest() {
        LOG.info("Login as default user");
        mainPage.loginWithDefault();
        Assert.assertEquals(mainPage.getAlertSuccessMessage(), SUCCESS_MESSAGE);
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
    public void checkLoginWithWrongDataTest() {
        LOG.info("Login as custom user with wrong data");
        mainPage.loginApp(ConfigProperties.getProperty("login"), ConfigProperties.getProperty("password"));
        Assert.assertEquals(mainPage.getAlertDangerMessage(), ERROR_MESSAGE);
    }
}
