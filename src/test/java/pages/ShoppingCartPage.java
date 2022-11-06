package pages;

import helper.WaitBeforeAnyAction;
import jdk.jfr.Name;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;

public class ShoppingCartPage extends MainPage{
    private WebDriver driver;
    private WaitBeforeAnyAction waiter;

    public ShoppingCartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = ".//a[text() = 'Back']")
    private WebElement cartBackButton;

    @Name("Remove selected product")
    @FindBy(xpath = ".//button[@title= 'Remove']")
    private WebElement deleteButton;

    public WebElement getCartButton() {
        return cartBackButton;
    }

    public WebElement getDeleteButton() {
        return deleteButton;
    }

    public boolean isDeleteOneProduct(String product){
        waiter = new WaitBeforeAnyAction(5, TimeUnit.SECONDS);
        waiter.beforeNavigateForward(driver);
        WebElement deleteItem = driver.findElement(By.xpath(".//li[@data-name= '%s']".
                formatted(product)));
        deleteItem.click();
        if(deleteItem.isDisplayed()){
            return false;
        }
        return true;
    }
}
