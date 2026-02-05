package Common.Common;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

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

    // Click
    public static void click(By locator){
        WaitUtils.waitForElementClickable(locator);
        findElement(locator).click();
    }
}