package Common.Common;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Utilities {

    // Checks
    public static boolean isElementPresent(By locator) {
        try {
            Constant.WEBDRIVER.findElement(locator);
            return true;
        } catch(NoSuchElementException e) {
            return false;
        }
    }

    // Finds

    public static WebElement findElement(By locator) {
        return Constant.WEBDRIVER.findElement(locator);
    }

    public static List<WebElement> findElements(By locator) {
        return Constant.WEBDRIVER.findElements(locator);
    }

    // Waits
    public static void waitForElementVisibility(WebDriver driver, By locator, Duration time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForElementVisibility(WebDriver driver, By locator) {
        waitForElementVisibility(driver, locator, Constant.TIMEOUT);
    }

    public static void waitForElementClickable(WebDriver driver, By locator, Duration time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementClickable(WebDriver driver, By locator) {
        waitForElementClickable(driver, locator, Constant.TIMEOUT);
    }
}