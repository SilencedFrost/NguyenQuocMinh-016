package Common.Common;

import Common.Constant.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

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

    public static boolean isElementStale(WebElement webElement) {
        try {
            webElement.isDisplayed();
            return false;
        } catch (StaleElementReferenceException e) {
            return true;
        }
    }

    // Find
    public static WebElement findElement(By locator, Duration pageLoadTimeout, Duration elementTimeout) {
        WaitUtils.waitForPageLoad(pageLoadTimeout);
        // Wait to prevent flaky scenarios
        WaitUtils.waitForElementPresent(locator, elementTimeout);
        return Constant.WEBDRIVER.findElement(locator);
    }

    public static WebElement findElement(By locator, Duration elementTimeout) {
        return findElement(locator, Constant.PAGE_LOAD_TIMEOUT, elementTimeout);
    }

    public static WebElement findElement(By locator) {
        return findElement(locator, Constant.FIND_ELEMENT_TIMEOUT);
    }

    public static List<WebElement> findElements(By locator, Duration pageLoadTimeout, Duration elementTimeout) {
        WaitUtils.waitForPageLoad(pageLoadTimeout);
        // Wait to prevent flaky scenarios
        WaitUtils.waitForElementPresent(locator, elementTimeout);
        return Constant.WEBDRIVER.findElements(locator);
    }

    public static List<WebElement> findElements(By locator, Duration elementTimeout) {
        return findElements(locator, Constant.PAGE_LOAD_TIMEOUT, elementTimeout);
    }

    public static List<WebElement> findElements(By locator) {
        return findElements(locator, Constant.FIND_ELEMENT_TIMEOUT);
    }

    // Actions
    public static WebElement click(By locator, Duration timeout){
        WaitUtils.waitForElementVisible(locator, timeout);
        scrollToElement(locator, timeout);
        WaitUtils.waitForElementClickable(locator, timeout);
        WebElement element = findElement(locator);
        element.click();
        return element;
    }

    public static WebElement click(By locator) {
        return click(locator, Constant.FIND_ELEMENT_TIMEOUT);
    }

    public static By scrollToElement(By locator, Duration timeout) {
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center', inline: 'nearest'});", findElement(locator, timeout));
        return locator;
    }

    public static By scrollToElement(By locator) {
        return scrollToElement(locator, Constant.FIND_ELEMENT_TIMEOUT);
    }

    public static By selectComboboxByVisibleText(By locator, String visibleText, Duration timeout) {
        WaitUtils.waitForElementVisible(locator, timeout);
        scrollToElement(locator, timeout);
        WaitUtils.waitForElementClickable(locator, timeout);
        new Select(findElement(locator)).selectByVisibleText(visibleText);
        return locator;
    }

    public static By selectComboboxByVisibleText(By locator, String visibleText) {
        return selectComboboxByVisibleText(locator, visibleText, Constant.FIND_ELEMENT_TIMEOUT);
    }

    public static void disableGoogleAd() {
        String advertisementFullscreenContainer = "//ins[@class='adsbygoogle adsbygoogle-noablate' and contains(@style, '100vw')]";
        String iframeAdvertisementContainer = "//iframe[contains(@id, 'aswift')]";
        By iframeAdvertisement = By.xpath("//iframe[@id='ad_iframe']");
        By btnCloseAdvertisement = By.xpath("//div[contains(@id, 'dismiss-button') or contains(@id, 'close-button')]");

        WebElement advertisement = WaitUtils.safeWaitForElementVisible(By.xpath(advertisementFullscreenContainer), Duration.ofSeconds(1));
        if(advertisement != null) {
            Constant.WEBDRIVER.switchTo().frame(Utilities.findElement(By.xpath(advertisementFullscreenContainer + iframeAdvertisementContainer)));
            if(isElementPresent(btnCloseAdvertisement)) {
                Utilities.click(btnCloseAdvertisement);
            } else {
                Constant.WEBDRIVER.switchTo().frame(Utilities.findElement(iframeAdvertisement));
                Utilities.click(btnCloseAdvertisement);
            }
            Constant.WEBDRIVER.switchTo().defaultContent();
        }
    }
}