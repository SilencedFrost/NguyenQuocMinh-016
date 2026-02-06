package Common.Common;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
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
        WaitUtils.waitForPageLoad();
        // Wait to prevent flaky scenarios
        WaitUtils.waitForElementVisibility(locator);
        return Constant.WEBDRIVER.findElement(locator);
    }

    public static List<WebElement> findElements(By locator) {
        WaitUtils.waitForPageLoad();
        // Wait to prevent flaky detections
        WaitUtils.waitForElementVisibility(locator);
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
        click(locator, Constant.FIND_ELEMENT_TIMEOUT);
    }

    public static By scrollToElement(By locator, Duration timeout) {
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center', inline: 'nearest'});", findElement(locator));
        return locator;
    }

    public static By scrollToElement(By locator) {
        return scrollToElement(locator, Constant.FIND_ELEMENT_TIMEOUT);
    }

    private static ArrayList<String> getTabs() {
        return new ArrayList<>(Constant.WEBDRIVER.getWindowHandles());
    }

    public static void switchToLatestWindow() {
        Constant.WEBDRIVER.switchTo().window(getTabs().getLast());
    }

    public static void switchToWindowIndex(int index) {
        Constant.WEBDRIVER.switchTo().window(getTabs().get(index));
    }

    public static void switchToFirstWindow() {
        switchToWindowIndex(0);
    }

    public static void closeLatestWindow() {
        String currentHandle = Constant.WEBDRIVER.getWindowHandle();
        String latestHandle = getTabs().getLast();

        if (!latestHandle.equals(currentHandle)) {
            Constant.WEBDRIVER.switchTo().window(latestHandle);
            Constant.WEBDRIVER.close();
            Constant.WEBDRIVER.switchTo().window(currentHandle);
        } else {
            Constant.WEBDRIVER.close();
            switchToFirstWindow();
        }
    }

    public static void closeWindowIndex(int index) {
        String currentHandle = Constant.WEBDRIVER.getWindowHandle();
        String targetHandle = getTabs().get(index);

        if (!targetHandle.equals(currentHandle)) {
            Constant.WEBDRIVER.switchTo().window(targetHandle);
            Constant.WEBDRIVER.close();
            Constant.WEBDRIVER.switchTo().window(currentHandle);
        } else {
            Constant.WEBDRIVER.close();
            switchToFirstWindow();
        }
    }

    public static void closeFirstWindow() {
        String currentHandle = Constant.WEBDRIVER.getWindowHandle();
        String firstHandle = getTabs().get(0);

        if (!firstHandle.equals(currentHandle)) {
            Constant.WEBDRIVER.switchTo().window(firstHandle);
            Constant.WEBDRIVER.close();
            Constant.WEBDRIVER.switchTo().window(currentHandle);
        } else {
            Constant.WEBDRIVER.close();
            switchToWindowIndex(1); // Switch to second window if first was current
        }
    }

    public static By selectComboboxByVisibleText(By locator, String visibleText, Duration timeout) {
        WaitUtils.waitForElementVisibility(locator, timeout);
        scrollToElement(locator, timeout);
        WaitUtils.waitForElementClickable(locator, timeout);
        new Select(findElement(locator)).selectByVisibleText(visibleText);
        return locator;
    }

    public static By selectComboboxByVisibleText(By locator, String visibleText) {
        return selectComboboxByVisibleText(locator, visibleText, Constant.FIND_ELEMENT_TIMEOUT);
    }
}