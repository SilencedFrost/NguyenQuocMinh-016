package Common.Common;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.time.Duration;

public class Utilities {

    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomString(String charset, Integer length) {
        if (length == null || length <= 0) {
            throw new IllegalArgumentException("Length must be a positive integer");
        }

        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charset.length());
            result.append(charset.charAt(index));
        }

        return result.toString();
    }

    /**
     * Returns random string with set length, contains only alphanumeric
     * @param length length of generated string
     * @return random string
     */
    public static String generateRandomString(Integer length) {
        return generateRandomString(Constant.LOWERCASE_ALPHA + Constant.UPPERCASE_ALPHA + Constant.NUMERICAL, length);
    }

    /**
     * Returns password containing: uppercase letter, lowercase letter, numbers, and special characters that is 14 characters long
     * @return random password
     */
    public static String generateRandomPassword() {
        return "1Wa$" + generateRandomString(Constant.LOWERCASE_ALPHA + Constant.UPPERCASE_ALPHA + Constant.NUMERICAL + Constant.SPECIAL_CHARACTERS, 10);
    }

    public static String generateRandomEmail() {
        // Generate random username (8-12 characters)
        int usernameLength = 8 + random.nextInt(5); // 8 to 12
        String username = generateRandomString(usernameLength);

        // Common email domains
        String[] domains = {"gmail.com", "yahoo.com", "outlook.com", "example.com"};
        String domain = domains[random.nextInt(domains.length)];

        return username + "@" + domain;
    }

    public static boolean isElementPresent(By locator) {
        return !Constant.WEBDRIVER.findElements(locator).isEmpty();
    }

    public static void waitForElementVisibility(WebDriver driver, By locator, Duration time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForElementVisibility(WebDriver driver, By locator) {
        waitForElementVisibility(driver, locator, Duration.ofSeconds(10));
    }

    public static void waitForElementClickable(WebDriver driver, By locator, Duration time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementClickable(WebDriver driver, By locator) {
        waitForElementClickable(driver, locator, Duration.ofSeconds(10));
    }
}