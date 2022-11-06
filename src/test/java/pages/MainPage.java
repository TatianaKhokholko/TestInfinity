package pages;

import helper.WaitBeforeAnyAction;
import jdk.jfr.Name;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainPage {
    private WebDriver driver;
    private WaitBeforeAnyAction waiter;
    private ShoppingCartPage shoppingCartPage;
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = ".//header[@class = 'hidden-print']")
    private WebElement headerPage;

    @FindBy(xpath = ".//a[@class = 'logotype']")
    private WebElement logoType;

    @FindBy(xpath = ".//div[@class = 'currency']/span")
    private WebElement regionCurrencyBlock;

    @Name("The button for change regional settings")
    @FindBy(xpath = ".//a[text()= 'Change']")
    private WebElement changeRegionalSettingsButton;

    @FindBy(xpath = ".//section[@id= 'box-regional-settings']")
    private WebElement regionalSettingsModalWin;

    @FindBy(xpath = ".//div[@aria-label = 'Close']")
    private WebElement closeRegionalSettingsModalWin;

    @Name("Save button for region settings modal window")
    @FindBy(xpath = ".//select[@name = 'currency_code']")
    private WebElement saveRegionSettingsModalButton;

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
    private WebElement emailSubscribeInput;

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

    @FindBy(xpath = ".//input[@type='search']")
    private WebElement searchField;

    @FindBy(xpath = ".//a[@title='Home']")
    private WebElement homeButton;

    @FindBy(xpath = ".//button[@name= 'add_cart_product']")
    private WebElement addCartProductButton;

    @FindBy(xpath = ".//div[@id= 'cart']")
    private WebElement cartProductButton;

    public MainPage() {
    }

    public WebElement getHeaderPage() {
        return headerPage;
    }

    public WebElement getLogoType() {
        return logoType;
    }

    public WebElement getRegionCurrencyBlock() {
        return regionCurrencyBlock;
    }

    public WebElement getChangeRegionalSettingsButton() {
        return changeRegionalSettingsButton;
    }

    public WebElement getRegionalSettingsModalWin() {
        return regionalSettingsModalWin;
    }

    public WebElement getCloseRegionalSettingsModalWin() {
        return closeRegionalSettingsModalWin;
    }

    public WebElement getSaveRegionSettingsModalButton() {
        return saveRegionSettingsModalButton;
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

    public WebElement getEmailSubscribeInput() {
        return emailSubscribeInput;
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

    public WebElement getSearchField() {
        return searchField;
    }

    public WebElement getHomeButton() {
        return homeButton;
    }

    public WebElement getCartProductButton() {
        return cartProductButton;
    }

    /**
     * Click on search product, add to cart and navigate to cart page
     */
    public ShoppingCartPage addProductToCartAndGoToCart(String productItem) {
        WebElement searchProduct = driver.findElement(By.xpath(".//a[@data-name = '%s']".formatted(productItem)));
        searchProduct.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                (By.xpath(".//button[@name= 'add_cart_product']"))));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);

        waiter = new WaitBeforeAnyAction(5, TimeUnit.SECONDS);
        waiter.beforeClickOn(getCartProductButton(), driver);
        getCartProductButton().click();
        return shoppingCartPage;
    }

    /**
     * Check the main page rendering
     */
    public boolean isDisplayedMainPage() {
        try {
            return driver.findElement(By.xpath(".//section[@id= 'box-slides']")).isDisplayed();
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

    /**
     * Login with default login + password
     */
    public void loginWithDefault() {
        getLoginDropdown().click();
        isLoginFormOpen();
        getSignInButton().click();
    }

    public void clickLogoutButton() {
        getLoginDropdown().click();
        getLogoutButton().click();
    }

    /**
     * Login with personal account value
     */
    public void loginApp(String userName, String userPassword) {
        getLoginDropdown().click();
        inputLogin(userName);
        inputPassword(userPassword);
        getSignInButton().click();
    }

    public void inputLogin(String login){
        if(getLoginField() != null){
            getLoginField().clear();
            getLoginField().sendKeys(login);
        } else{
            getLoginField().sendKeys(login);
        }
    }

    public void inputPassword(String userPassword){
        if(getLoginField() != null){
            getPasswdField().clear();
            getPasswdField().sendKeys(userPassword);
        } else{
            getPasswdField().sendKeys(userPassword);
        }
    }

    public void openRegionSettings() {
        getChangeRegionalSettingsButton().click();
    }

    public void closeRegionSettings() {
        getCloseRegionalSettingsModalWin().click();
    }

    /**
     * Select new currency by dropdown and save
     */
    public void saveCurrencyRegionSettings(String newCurrency) {
        openRegionSettings();
        getRegionalSettingsModalWin().isDisplayed();
        Select dropDownCurrency = new Select(driver.findElement(By.name("currency_code")));
        dropDownCurrency.selectByVisibleText(newCurrency);
        getSaveRegionSettingsModalButton().click();
    }

    public String getAlertDangerMessageAfterLogout() {
        return driver.findElement(By.xpath(".//div[contains(@class, 'alert-danger')]")).getText();
    }

    public String getAlertSuccessMessageAfterLogin(){
        return driver.findElement(By.xpath(".//div[contains(@class, 'alert-success')]")).getText();
    }

    public String getSubscribeAlertSuccess(){
        return driver.findElement(By.xpath(".//div[contains(@class, 'alert-success')]")).getText();
    }

    public String searchProduct(String productName) {
        getSearchField().sendKeys(productName);
        getSearchField().click();
        getSearchField().sendKeys(Keys.ENTER);
        return productName;
    }

    public boolean isSearchContentProduct(String productName) {
        return driver.findElement(By.xpath(".//div[@id ='content']//li[text()= '%s']".
                formatted(productName))).isDisplayed();
    }

    public void subscribe(String email) {
        getEmailSubscribeInput().sendKeys(email);
        getSubscribeButton().click();
    }
}
