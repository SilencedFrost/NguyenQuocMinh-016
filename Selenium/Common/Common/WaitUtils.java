package Common.Common;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    // Waits
    public static By waitForElementVisibility(By locator, Duration time) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return locator;
    }

    public static By waitForElementVisibility(By locator) {
        return waitForElementVisibility(locator, Constant.TIMEOUT);
    }

    public static By waitForElementClickable(By locator, Duration time) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, time);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return locator;
    }

    public static By waitForElementClickable(By locator) {
        return waitForElementClickable(locator, Constant.TIMEOUT);
    }
}
