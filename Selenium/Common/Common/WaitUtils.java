package Common.Common;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class WaitUtils {

    // Waits
    public static By waitForElementVisible(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return locator;
    }

    public static By waitForElementVisible(By locator) {
        return waitForElementVisible(locator, Constant.FIND_ELEMENT_TIMEOUT);
    }

    public static By waitForElementPresence(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return locator;
    }

    public static By waitForElementPresence(By locator) {
        return waitForElementPresence(locator, Constant.FIND_ELEMENT_TIMEOUT);
    }

    public static By waitForElementClickable(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return locator;
    }

    public static By waitForElementClickable(By locator) {
        return waitForElementClickable(locator, Constant.FIND_ELEMENT_TIMEOUT);
    }

    public static void waitForPageLoad(Duration timeout) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, timeout);
        wait.until(webDriver -> Objects.equals(((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState"), "complete"));
    }

    public static void waitForPageLoad() {
        waitForPageLoad(Constant.PAGE_LOAD_TIMEOUT);
    }
}
