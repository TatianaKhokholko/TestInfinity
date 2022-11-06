package tests;

import helper.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.log4testng.Logger;
import pages.MainPage;
import pages.ShoppingCartPage;

import java.time.Duration;

/**
 * The base class for all tests. It initializes the webDriver before test's run
 * and closes browser at the end of test execution.
 */
public class BaseTestClass {
    private final static Logger LOG = Logger.getLogger(BaseTestClass.class);
    public static WebDriver driver;
    public static MainPage mainPage;
    public static ShoppingCartPage shoppingCartPage;

    @BeforeClass(description = "web driver initialization")
    public static void setUp() {
        LOG.info("Specifying the path to the configuration file");
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getProperty("chromeDriver"));
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);

        LOG.info("Setting driver's timeouts");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        LOG.debug("Driver is ready for work");
        driver.get(ConfigProperties.getProperty("baseUrl"));
    }

    /**
     * Close browser
     */
    @AfterClass(alwaysRun = true)
    public static void closeDriver() {
        LOG.info("Closing browser");
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
        }
        driver = null;
        LOG.info("Browser was closed");
    }
}
