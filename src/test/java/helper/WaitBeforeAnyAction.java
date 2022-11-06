package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class WaitBeforeAnyAction{
    private final long timeout;

    public WaitBeforeAnyAction(long timeout, TimeUnit unit) {
        this.timeout = TimeUnit.MILLISECONDS.convert(Math.max(0, timeout), unit);
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        sleep();
    }

    public void beforeNavigateForward(WebDriver driver) {
        sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
