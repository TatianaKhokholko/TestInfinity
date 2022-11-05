package pages;

import jdk.jfr.Name;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import java.time.Duration;

public class MainPage {
    private final static Logger LOG = Logger.getLogger(MainPage.class);
    private WebDriver driver;
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = ".//button[contains(text(), 'Accept Cookies')]")
    private WebElement acceptCookiesButton;

    @FindBy(xpath = ".//div[@class = 'hidden-print']")
    private WebElement headerPage;

    @FindBy(xpath = ".//a[@class = 'logotype']")
    private WebElement logoType;

    @FindBy(xpath = ".//div[@id = 'region']")
    private WebElement regionBlock;

    @Name("The button for change regional settings")
    @FindBy(xpath = ".//a[text()= 'Change']")
    private WebElement changeRegionalSettingsButton;

    @FindBy(xpath = ".//a[@class= 'featherlight-content']")
    private WebElement featherLightModalWin; //TO DO

    @FindBy(xpath = ".//div[@aria-label = 'Close']")
    private WebElement closeFeatherLightModalWin; //TO DO

    @FindBy(xpath = ".//i[normalize-space(text() = 'Sign In')]/ancestor::a[@class = 'dropdown-toggle']")
    private WebElement loginDropdown;

    @FindBy(xpath = ".//a[contains(@class, 'dropdown-toggle') and normalize-space(text()) = 'Manufacturers']")
    private WebElement manufacturersDropdown;

    @FindBy(xpath = ".//a[contains(@class, 'dropdown-toggle') and normalize-space(text()) = 'Categories']")
    private WebElement categoriesDropdown;

    @FindBy(xpath = ".//li[@class = 'customer-service']")
    private WebElement customerServiceButton;

    @Name("General container box")
    @FindBy(xpath = ".//div[@id = 'content']")
    private WebElement contentBox;

    @FindBy(xpath = ".//section[@id = 'box-campaign-products']")
    private WebElement campaignProductsBox;

    @FindBy(xpath = ".//section[@id = 'box-popular-products']")
    private WebElement popularProductsBox;

    @FindBy(xpath = ".//section[@id = 'box-latest-products']")
    private WebElement latestProductsBox;

    @Name("Input email for subscribe")
    @FindBy(xpath = ".//form[contains(@name, 'newsletter_subscribe')]//input[@name = 'email']")
    private WebElement emailInput;

    @FindBy(xpath = ".//button[@name = 'subscribe']")
    private WebElement subscribeButton;

    @Name("Input email for login on page")
    @FindBy(xpath = ".//input[@data-type = 'email']")
    private WebElement loginField;

    @Name("Input password for login on page")
    @FindBy(xpath = ".//input[@data-type = 'password']")
    private WebElement passwdField;

    @Name("Sign in button for login on page")
    @FindBy(xpath = ".//button[text() = 'Sign In']")
    private WebElement signInButton;

    @FindBy(xpath = ".//ul[@class ='dropdown-menu']//a[contains(@href, 'litecart.net/logout')]")
    private WebElement logoutButton;


    public WebElement getHeaderPage() {
        return headerPage;
    }

    public WebElement getLogoType() {
        return logoType;
    }

    public WebElement getRegionBlock() {
        return regionBlock;
    }

    public WebElement getChangeRegionalSettingsButton() {
        return changeRegionalSettingsButton;
    }

    public WebElement getFeatherLightModalWin() {
        return featherLightModalWin;
    }

    public WebElement getCloseFeatherLightModalWin() {
        return closeFeatherLightModalWin;
    }

    public WebElement getLoginDropdown() {
        return loginDropdown;
    }

    public WebElement getManufacturersDropdown() {
        return manufacturersDropdown;
    }

    public WebElement getCategoriesDropdown() {
        return categoriesDropdown;
    }

    public WebElement getCustomerServiceButton() {
        return customerServiceButton;
    }

    public WebElement getContentBox() {
        return contentBox;
    }

    public WebElement getCampaignProductsBox() {
        return campaignProductsBox;
    }

    public WebElement getPopularProductsBox() {
        return popularProductsBox;
    }

    public WebElement getLatestProductsBox() {
        return latestProductsBox;
    }

    public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getSubscribeButton() {
        return subscribeButton;
    }

    public WebElement getLoginField() {
        return loginField;
    }

    public WebElement getPasswdField() {
        return passwdField;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    /**
     * Check the main page rendering
     */
    public boolean isDisplayedMainPage() {
        try {
            return driver.findElement(By.xpath(".//div[@class = 'fourteen-forty']")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Check the login modal window is open
     */
    public boolean isLoginFormOpen(){
        try {
            return driver.findElement(By.xpath(".//form[@name= 'login_form']")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void loginWithDefault() {
        getLoginDropdown().click();
        isLoginFormOpen();
        getSignInButton().click();
    }

    public void clickLogoutButton() {
        getLoginDropdown().click();
        getLogoutButton().click();
    }

    public void loginApp(String userName, String userPassword) {
        getLoginDropdown().click();
        getLoginField().sendKeys(userName);
        getPasswdField().sendKeys(userPassword);
        getSignInButton().click();
    }

    public String getAlertDangerMessage() {
        return driver.findElement(By.xpath(".//div[contains(@class, 'alert-danger')]")).getText();
    }

    public String getAlertSuccessMessage(){
        return driver.findElement(By.xpath(".//div[contains(@class, 'alert-success')]")).getText();
    }
}
