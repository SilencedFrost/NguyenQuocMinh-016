package Common.Common;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

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

    // Find
    public static WebElement findElement(By locator) {
        return Constant.WEBDRIVER.findElement(locator);
    }

    public static List<WebElement> findElements(By locator) {
        return Constant.WEBDRIVER.findElements(locator);
    }

    // Actions
    public static void click(By locator, Duration timeout){
        WaitUtils.waitForElementVisibility(locator, timeout);
        scrollToElement(locator, timeout);
        WaitUtils.waitForElementClickable(locator, timeout);
        findElement(locator).click();
    }

    public static void click(By locator) {
        click(locator, Constant.TIMEOUT);
    }

    public static By scrollToElement(By locator, Duration timeout) {
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});", findElement(locator));
        return locator;
    }

    public static By scrollToElement(By locator) {
        return scrollToElement(locator, Constant.TIMEOUT);
    }
}