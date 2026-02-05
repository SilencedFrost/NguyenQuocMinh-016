package Common.Common;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

    // Click
    public static void click(By locator){
        waitForElementClickable(locator);
        findElement(locator).click();
    }
}